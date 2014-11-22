package br.com.toldosjoia.Service;

import java.io.File;
import java.util.ArrayList;

import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.banco_de_dados.atualizar_dados;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.main.Principal;
import br.com.toldosjoia.webService.CelulaREST;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

public class ServicoAtualizacao extends Service {

	private Operacoes_no_banco_local banco_local;
	public static boolean servico_aberto; 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
        banco_local = new Operacoes_no_banco_local(this.getApplicationContext());
       
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	
		File data = Environment.getDataDirectory();
		  String currentDBPath = "//data//br.com.toldosjoia//databases//Toldos";
          File currentDB = new File(data, currentDBPath);
		servico_aberto = true;
		if(isOnline() && currentDB.exists())iniciar_Atualizacao();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		servico_aberto = false;
		
	}
	private boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting() && netInfo.isAvailable()) {
	        return true;
	    }
	    return false;
	}
   private void iniciar_atualizacao()
   {
	try{
	  String dataEhoraAtual;
		dataEhoraAtual = banco_local.getUltima_Atualizacao();
    if(dataEhoraAtual.equals(null))return;
       
   	CelulaREST rest = new CelulaREST(this);
   	
   	int NovosDadosCliente = rest.getListClienteAtualizada(dataEhoraAtual);
   	int NovosDadosUsuario = rest.getListUsuarioAtualizada(dataEhoraAtual);
   	int NovosDadosPedidos = rest.getListaPedidosAtualizada(dataEhoraAtual);
   	int NovosDadosMaterial = rest.getListaMaterialAtualizada(dataEhoraAtual);
   	int NovosDadosModelo = rest.getListaModeloAtualizada(dataEhoraAtual);
   	if(NovosDadosCliente == 2 || NovosDadosUsuario == 2 ||  NovosDadosPedidos == 2 || NovosDadosMaterial == 2 || NovosDadosModelo == 2){
   		     
             new atualizar_dados(this).Atualizar_ULTIMA_ATUALIZACAO();
            dataEhoraAtual = banco_local.getUltima_Atualizacao();
            if(NovosDadosCliente == 2)criarNotificacao();
            if(NovosDadosPedidos == 2)criarNotificacao();
		}
	   }catch(Exception e){}
   }
   @SuppressWarnings("deprecation")
protected void criarNotificacao() {

		String mensagemBarraStatus = "cliente novo adicionado.";
		String titulo = "Toldos Joia.";
		String mensagem = "Adicionado cliente ";
	
		
		Class<?> activity = Principal.class;

	
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Notification notificacao = new Notification(android.R.drawable.ic_dialog_info, mensagemBarraStatus, System.currentTimeMillis());


		Intent intentMensagem = new Intent(this, activity);
	
		PendingIntent p = PendingIntent.getActivity(this, 0, intentMensagem, 0);

		notificacao.setLatestEventInfo(this, titulo, mensagem, p);


		notificacao.vibrate = new long[] { 100, 250, 100, 500 };

	
		nm.notify(br.com.toldosjoia.R.string.app_name, notificacao);
	}
   private void iniciar_Atualizacao()
   {
	   new Thread() {
		   
	      @Override
		   public void run(){
		   iniciar_atualizacao();
	       }
	   }.start();
   }
}
 
	   
	   
