package br.com.toldosjoia.webService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import android.util.Log;

public class WebService {

    public final String[] get(String url) {

         String[] result = new String[2];
         HttpGet httpget = new HttpGet(url);
         HttpResponse response;

         try {

             response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                 InputStream instream = entity.getContent();
                 result[1] = toString(instream);
                 instream.close();

                 Log.i("get", "Result from post JsonPost : " + result[0] + " : " + result[1]);
             }
         } catch (Exception e) {
             Log.e("NGVL", "Falha ao acessar Web service", e);
             result[0] = "0";
             result[1] = "Falha na rede!";
         }
         return result;
        }
    private String toString(InputStream is) throws IOException {

         byte[] bytes = new byte[1024];
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         int lidos;
         while ((lidos = is.read(bytes)) > 0) {
             baos.write(bytes, 0, lidos);
         }
         return new String(baos.toByteArray());
        }
}
