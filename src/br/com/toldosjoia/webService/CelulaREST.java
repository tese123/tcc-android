package br.com.toldosjoia.webService;

import java.util.ArrayList;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.banco_de_dados.inserir_dados;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Pedidos;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.dados.material;
import br.com.toldosjoia.dados.modelo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CelulaREST {
        private static final String URL_WS = "http://192.168.170.1:8084/WebServiceToldosJoia/informacoes_para_o_app/";
        private Operacoes_no_banco_local banco_local;
        Context mContexto;
        public CelulaREST(Context contexto)
        {
           mContexto = contexto;
           banco_local = new Operacoes_no_banco_local(contexto);
	       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	       StrictMode.setThreadPolicy(policy);
         }
        
	    public int getListaCliente()  {
	    String[] resposta = conexao_com_webservice(URL_WS+"buscarCliente");
	    if(resposta.equals(null))return 0;
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
	         
        if(resposta[0].equals("200")) {
		    Gson gson = new Gson();
		    JsonParser parser = new JsonParser();
		        try{
		    try{
		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("cliente");
		         for(int i = 0; i < array.size(); i++)
		        	 listaCliente.add(gson.fromJson(array.get(i), Cliente.class));
		       }catch(Exception e){
					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("cliente");
					listaCliente.add(gson.fromJson(json, Cliente.class)); }}
		        catch(IllegalStateException e){}
		    if(listaCliente.size() > 0) {
 		    	addCliente(listaCliente);
 		    	return 2;
 		    }
 		    else
 		    	return 1;
 		   }
 		else return 0;
	    }
	    public int getListaUsuario()
	    {
		    String[] resposta = conexao_com_webservice(URL_WS+"buscarUsuario");
		    if(resposta.equals(null))return 0;
		    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
			if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         JsonParser parser = new JsonParser();
		         try{
		        try{
		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("usuario");
		       

		        for (int i = 0; i < array.size(); i++) {
		             listaUsuario.add(gson.fromJson(array.get(i), Usuario.class));
		         }
		        }catch(Exception e){
		        	 Log.i("erro", "um elemento no lista" );
					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("usuario");
					listaUsuario.add(gson.fromJson(json, Usuario.class));
					Log.i("listaUsuario", listaUsuario.get(0).getUsuario());
		          }}
		         catch(IllegalStateException e){}
		        if(listaUsuario.size() > 0) {
	 		    	addUsuario(listaUsuario);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
	    public int getListClienteAtualizada(String dataEhora)
	    {
	    	 String[] resposta = conexao_com_webservice(URL_WS+"buscarClienteAtualizar/"+dataEhora);
	    	 if(resposta.equals(null))return 0;
	    	 Log.i("teste", dataEhora);
	         ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
	 	         
	         if(resposta[0].equals("200")) {
	 		    Gson gson = new Gson();
	 		    JsonParser parser = new JsonParser();
	 		        try{
	 		    try{
	 		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("cliente");
	 		         for(int i = 0; i < array.size(); i++)
	 		        	 listaCliente.add(gson.fromJson(array.get(i), Cliente.class));
	 		       }catch(Exception e){
	 					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("cliente");
	 					listaCliente.add(gson.fromJson(json, Cliente.class)); }}
	 		       catch(IllegalStateException e){}
	 		   if(listaCliente.size() > 0) {
	 		    	addCliente(listaCliente);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
	    
	    public int getListaModeloAtualizada(String dataEhora)
	    {
		    String[] resposta = conexao_com_webservice(URL_WS+"buscarModeloAtualizar/"+dataEhora);
		    if(resposta.equals(null))return 0;
		    ArrayList<modelo> listamodelo = new ArrayList<modelo>();
			if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         JsonParser parser = new JsonParser();
		         try{
		        try{
		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("modelo");
		       

		        for (int i = 0; i < array.size(); i++) {
		             listamodelo.add(gson.fromJson(array.get(i), modelo.class));
		         }
		        }catch(Exception e){
		        	 Log.i("erro", "um elemento no lista" );
					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("modelo");
					listamodelo.add(gson.fromJson(json, modelo.class));
		          }}
		         catch(IllegalStateException e){}
		        if(listamodelo.size() > 0) {
	 		    	addModelo(listamodelo);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
        
	    public int getListaModelo()
	    {
		    String[] resposta = conexao_com_webservice(URL_WS+"buscarModelo");
		    if(resposta.equals(null))return 0;
		    ArrayList<modelo> listamodelo = new ArrayList<modelo>();
			if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         JsonParser parser = new JsonParser();
		         try{
		        try{
		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("modelo");
		       

		        for (int i = 0; i < array.size(); i++) {
		             listamodelo.add(gson.fromJson(array.get(i), modelo.class));
		         }
		        }catch(Exception e){
		        	 Log.i("erro", "um elemento no lista" );
					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("modelo");
					listamodelo.add(gson.fromJson(json, modelo.class));
		          }}
		         catch(IllegalStateException e){}
		        if(listamodelo.size() > 0) {
	 		    	addModelo(listamodelo);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
        
	    public int getListaPedidos()
	    {
		    String[] resposta = conexao_com_webservice(URL_WS+"buscarPedidos");
		    if(resposta.equals(null))return 0;
		    ArrayList<Pedidos> listaPedidos = new ArrayList<Pedidos>();
			if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         JsonParser parser = new JsonParser();
		         try{
		        try{
		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("pedidos");
		       

		        for (int i = 0; i < array.size(); i++) {
		        	Log.i("pedidos", resposta[1] );
		        	listaPedidos.add(gson.fromJson(array.get(i), Pedidos.class));
		         }
		        }catch(Exception e){
		        	 Log.i("erro", "um elemento no lista" );
					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("pedidos");
					listaPedidos.add(gson.fromJson(json, Pedidos.class));
		          }}
		         catch(IllegalStateException e){
		        	 Log.i("ins", "o");
		         }
		        if(listaPedidos.size() > 0) {
	 		    	addPedidos(listaPedidos);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
	    public int getListaPedidosAtualizada(String dataEhora)
	    {
		    String[] resposta = conexao_com_webservice(URL_WS+"buscarPedidosAtualizar/"+dataEhora);
		    if(resposta.equals(null))return 0;
		    ArrayList<Pedidos> listaPedidos = new ArrayList<Pedidos>();
			if (resposta[0].equals("200")) {
		         Gson gson = new Gson();
		         JsonParser parser = new JsonParser();
		         try{
		        try{
		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("pedidos");
		       

		        for (int i = 0; i < array.size(); i++) {
		        	listaPedidos.add(gson.fromJson(array.get(i), Pedidos.class));
		         }
		        }catch(Exception e){
		        	 Log.i("erro", "um elemento no lista" );
					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("pedidos");
					listaPedidos.add(gson.fromJson(json, Pedidos.class));
		          }}
		         catch(IllegalStateException e){}
		        if(listaPedidos.size() > 0) {
	 		    	addPedidos(listaPedidos);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
		public int getListUsuarioAtualizada(String dataEhora)
	    {
	    	 String[] resposta = conexao_com_webservice(URL_WS+"buscarUsuarioAtualizar/"+dataEhora);
	    	 if(resposta.equals(null))return 0;
	    	 Log.i("teste", dataEhora);
	         ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	 	         
	         if(resposta[0].equals("200")) {
	 		    Gson gson = new Gson();
	 		    JsonParser parser = new JsonParser();
	 		     try{   
	 		    try{
	 		         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("usuario");
	 		         for(int i = 0; i < array.size(); i++)
	 		        	 listaUsuario.add(gson.fromJson(array.get(i), Usuario.class));
	 		       }catch(Exception e){
	 					JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("usuario");
	 					listaUsuario.add(gson.fromJson(json, Usuario.class)); }}
	 		     catch(IllegalStateException e){}
	 		    if(listaUsuario.size() > 0) {
	 		    	addUsuario(listaUsuario);
	 		    	return 2;
	 		    }
	 		    else
	 		    	return 1;
	 		   }
	 		else return 0;
	    }
		  public int getListaMaterial()
		    {
			    String[] resposta = conexao_com_webservice(URL_WS+"buscarMaterial");
			    if(resposta.equals(null))return 0;
			    ArrayList<material> listaMaterial = new ArrayList<material>();
				if (resposta[0].equals("200")) {
			         Gson gson = new Gson();
			         JsonParser parser = new JsonParser();
			         try{
			        try{
			         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("material");
			       

			        for (int i = 0; i < array.size(); i++) {
			        	listaMaterial.add(gson.fromJson(array.get(i), material.class));
			         }
			        }catch(Exception e){
			        	 Log.i("erro", "um elemento no lista" );
						JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("material");
						listaMaterial.add(gson.fromJson(json, material.class));
			          }}
			         catch(IllegalStateException e){}
			        if(listaMaterial.size() > 0) {
		 		    	addMaterial(listaMaterial);
		 		    	return 2;
		 		    }
		 		    else
		 		    	return 1;
		 		   }
		 		else return 0;
		    }
	
		  public int getListaMaterialAtualizada(String dataEhora)
		    {
			    String[] resposta = conexao_com_webservice(URL_WS+"buscarMaterialAtualizar/"+dataEhora);
			    if(resposta.equals(null))return 0;
			    ArrayList<material> listaMaterial = new ArrayList<material>();
				if (resposta[0].equals("200")) {
			         Gson gson = new Gson();
			         JsonParser parser = new JsonParser();
			         try{
			        try{
			         JsonArray array = parser.parse(resposta[1]).getAsJsonObject().getAsJsonArray("material");
			       

			        for (int i = 0; i < array.size(); i++) {
			        	listaMaterial.add(gson.fromJson(array.get(i), material.class));
			         }
			        }catch(Exception e){
			        	 Log.i("erro", "um elemento no lista" );
						JsonObject json = parser.parse(resposta[1]).getAsJsonObject().getAsJsonObject("material");
						listaMaterial.add(gson.fromJson(json, material.class));
			          }}
			         catch(IllegalStateException e){}
			        if(listaMaterial.size() > 0) {
		 		    	addMaterial(listaMaterial);
		 		    	return 2;
		 		    }
		 		    else
		 		    	return 1;
		 		   }
		 		else return 0;
		    }
		private String[] conexao_com_webservice(String URL)
	    {
	    	try{
	    	return new WebService().get(URL);
	    	}
	    	catch(Exception e){}
	    	return null;
	    }
	    
	    private void addUsuario(ArrayList<Usuario> usuario)
	    {
	       new inserir_dados(mContexto).inserirDadosUsuario(usuario);	
	    }
	    private void addCliente(ArrayList<Cliente> cliente)
	    {
	    	new inserir_dados(mContexto).inserirDadosCliente(cliente);
	    }
	    private void addModelo(ArrayList<modelo> modelo) {
	    	new inserir_dados(mContexto).inserirDadosModelo(modelo);
		}
		private void addPedidos(ArrayList<Pedidos> listaPedidos) {
			// TODO Auto-generated method stub
			new inserir_dados(mContexto).inserirDadosPedidos(listaPedidos);
		}
	    private void addMaterial(ArrayList<material> listaMaterial) {
				// TODO Auto-generated method stub
	    	new inserir_dados(mContexto).inserirDadosMaterial(listaMaterial);
	    }
}
