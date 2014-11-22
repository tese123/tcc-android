package br.com.toldosjoia.banco_de_dados;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;

public class atualizar_dados extends Operacoes_no_banco_local{

    public atualizar_dados(Context contexto) {
		super(contexto);
		// TODO Auto-generated constructor stub
	}

	public void Servico_Abrir()
    {
     	bd = database.getReadableDatabase();
    	ContentValues values = new ContentValues();
    	values.put("aberto", "S");
        bd.update("SERVICE_ATIVO", values, "cod = ?", new String[]{0+""});
   	    bd.close();
   	    database.close();
    }  
	
    public void Servico_Fechar()
    {
     	bd = database.getReadableDatabase();
    	ContentValues values = new ContentValues();
    	values.put("aberto", "N");
        bd.update("SERVICE_ATIVO", values, "cod = ?", new String[]{0+""});
   	    bd.close();
   	    database.close();
    }
    
    public void Atualizar_ULTIMA_ATUALIZACAO()
    {
    	bd = database.getWritableDatabase();
   	    ContentValues values = new ContentValues();
    	values.put("ULTIMA", new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss").format((new Date())));
        bd.update("ULTIMA_ATUALIZACAO", values, "ID = ?", new String[]{0+""});
		bd.close();
		database.close();
    }
}
