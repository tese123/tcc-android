package br.com.toldosjoia.main;

import java.util.ArrayList;

import br.com.toldosjoia.R;
import br.com.toldosjoia.Activity.Configuracao_Activity;
import br.com.toldosjoia.Activity.Login_Activity;
import br.com.toldosjoia.Activity.menu_Activity;
import br.com.toldosjoia.ActivityDialog.carregar_backup_dialog;
import br.com.toldosjoia.Service.ServicoAtualizacao;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.banco_de_dados.atualizar_dados;
import br.com.toldosjoia.banco_de_dados.inserir_dados;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.webService.CelulaREST;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Principal extends Activity implements Runnable{
	
	
	private ArrayList<Usuario> usuario = new ArrayList<Usuario>();
	private ArrayList<Cliente> cliente = new ArrayList<Cliente>(); 
	private boolean sem_base_dados;
	private int id;
	private Operacoes_no_banco_local banco_local;
	private ProgressBar progressBar;
 

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);	
		banco_local = new Operacoes_no_banco_local(this);
	
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);	
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(R.string.app_name);
		
		new Handler().postDelayed(Principal.this, 500);
		
	}

	public void chamar_intent()
	{
		if(!sem_base_dados){
			if(!ServicoAtualizacao.servico_aberto)sendBroadcast(new Intent("Servico"));
			if(Verificar_entrada_automatica()){
				Intent intent = new Intent(this,menu_Activity.class);
				intent.putExtra("usuario", usuario.get(id));
				startActivity(intent);
				this.finish();
			}
			else{
				
				Intent intent = new Intent(this,Login_Activity.class);
				intent.putExtra("Usuario", usuario);
				startActivity(intent);
				this.finish();
				
			   }
			}
		else{
			
			progressBar.setVisibility(View.GONE);
			((TextView) findViewById(R.id.textView1)).setVisibility(View.VISIBLE);
			((Button) findViewById(R.id.buttonCarregar)).setVisibility(View.VISIBLE);
			((Button) findViewById(R.id.buttonTentar)).setVisibility(View.VISIBLE);
		}
	}

	public void banco_remoto()
	{
		
		if(!banco_local.usuario(usuario))
		{
		    try{
			CelulaREST rest = new CelulaREST(this);
	    	
	    	int NovosDadosCliente = rest.getListaCliente();
	    	int NovosDadosUsuario = rest.getListaUsuario();
	    	rest.getListaMaterial();
	    	rest.getListaModelo();
	    	rest.getListaPedidos();
	    	if(NovosDadosCliente == 2 || NovosDadosUsuario == 2){
	    
	        new inserir_dados(this).inserir_atualizacao();
	        banco_local.usuario(usuario);
	        Toast.makeText(this, "conexao com o banco foi um sucesso", Toast.LENGTH_SHORT).show();
			}
	    	else {
            	sem_base_dados = true;
            	Toast.makeText(this, "Erro na conexao com o banco", Toast.LENGTH_SHORT).show();
            }
		    }catch(Exception e){
		    	 Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		    }
		}
	}

    private boolean Verificar_entrada_automatica()
    {
    	boolean retorno = false;
    	for(int x = 0; x < usuario.size();x++)
    	{
    		if(usuario.get(x).getAutoEntra() == 'S'){
    			id = x;
    			retorno = true;
    			break;
    		}
    	}
    		return retorno;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		banco_remoto();
		chamar_intent();
	}
   public void backup(View view){
	   startActivity(new Intent(this,carregar_backup_dialog.class));
	 
	   
   }
   public void tentar(View view){
	   tentar_novamente();
   }
   private void tentar_novamente()
   {
	   banco_local = new Operacoes_no_banco_local(this);
	   sem_base_dados = false;
		((TextView) findViewById(R.id.textView1)).setVisibility(View.GONE);
		((Button) findViewById(R.id.buttonCarregar)).setVisibility(View.GONE);
		((Button) findViewById(R.id.buttonTentar)).setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
		  new Handler().postDelayed(Principal.this, 500);
   }
}
