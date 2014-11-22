package br.com.toldosjoia.customView;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.toldosjoia.R;
import br.com.toldosjoia.Activity.TascaramAMaoNaGoiaba;
import br.com.toldosjoia.ActivityDialog.carregar_backup_dialog;
import br.com.toldosjoia.banco_de_dados.Base_de_Dados;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class fileListcarregar  extends ListView implements android.widget.AdapterView.OnItemClickListener{

	
	public List<iconeTexto> vLista = new ArrayList<iconeTexto>();
	private Context mContexto;
    private Base_de_Dados database;
    public ProgressDialog progressBar;
    public boolean fimDathread = false;
    public iconeTextoListAdapter itla;
    public static String caminho_banco;
    
	public fileListcarregar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		mContexto = context;
		 database = new Base_de_Dados(context);
		 itla = new iconeTextoListAdapter(this.mContexto);
		listar();
   	     itla.setListaItens(vLista);		
 		 setAdapter(itla);
 		 setOnItemClickListener(fileListcarregar.this);
	     
 		

	}
    
   public void listarArquivo(File vDir, List<iconeTexto> vLista) {

    
        if (vDir != null) {
            File f = new File(vDir.toString());

     
            File[] files = f.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".backup");
                }
            });
            if (files != null) {
                for (int i = 0; i < files.length; ++i) {
                    String vArq = files[i].toString();
                    String[] vLimpa = vArq.split("/");

                	Drawable currentIcon = getResources().getDrawable(R.drawable.iconebackup);
					vLista.add(new iconeTexto(vArq, currentIcon));
                   
                }
            }
        }
    }

    /**
     * Metodo que lista as pasta do diretorio que é Passado Como parametro
     * @param vDir
     * @param vLista
     */
    public void ListarPasta(File vDir, List<iconeTexto> vLista) {
        FileFilter filterDirectoriesOnly = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        File[] sdDiretorio = vDir.listFiles(filterDirectoriesOnly);
        if (sdDiretorio != null) {
            for (int i = 0; i < sdDiretorio.length; i++) {
                
                // **********************************
                String[] ultPasta = sdDiretorio[i].toString().split("/");
                

                File ds = new File(vDir + "/" + ultPasta[ultPasta.length - 1]);

                listarArquivo(ds, vLista);
                ListarPasta(ds, vLista);
            
            }
        }

    }

    /**
     * Metodo Que inicia a listagem dos Arquivos 
     * iniciado pela pasta /mnt/sdcard
     * @return
     */
    public void listar() {
        File sd = Environment.getExternalStorageDirectory();
        listarArquivo(sd, vLista);
        ListarPasta(sd, vLista);
 
		fimDathread = true;
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		clearBackground(view);
		TascaramAMaoNaGoiaba.caminho_backup = vLista.get(position).getTexto().toString(); 
		view.setBackgroundColor(Color.rgb(220, 220, 220));
		
	}

	private void clearBackground(View view)
	{
		
		//for (int i = 0; i <  vLista.size(); i++)
		//  ((View)((LinearLayout)vLista.get(i).getChildAt(i))).setTextColor(getResources().getColor(R.color.white));
    }
	
	private void iniciar_Theread()
	   {
		   new Thread() {
			   
		      @Override
			   public void run(){
		    	  
		    	
		    	try {
					this.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	listar();
		    	try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       }
		      
		       public void destroy() {
                  
		       };
		   }.start();
	   }
}
