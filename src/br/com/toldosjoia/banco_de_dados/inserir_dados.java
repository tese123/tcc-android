package br.com.toldosjoia.banco_de_dados;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Pedidos;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.dados.material;
import br.com.toldosjoia.dados.modelo;

public class inserir_dados extends Operacoes_no_banco_local{

	public inserir_dados(Context contexto) {
		super(contexto);
		// TODO Auto-generated constructor stub
	}

	
	public void inserir_atualizacao()
	{
         	bd = database.getReadableDatabase();
		    ContentValues values = new ContentValues();
		    values.put("ULTIMA", new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss").format((new Date())));   
		    bd.insert("ULTIMA_ATUALIZACAO ", null, values);
		    bd.close();
		    database.close();
		    Log.i("uptade", new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss").format((new Date()))+"");
	}
	
	public void inserirDadosCliente(ArrayList<Cliente> cliente){
		bd = database.getWritableDatabase();
		for(int x = 0; x < cliente.size();x++)
   	     {
		    ContentValues values = new ContentValues();
		    values.put("nome", cliente.get(x).getNome());
		    values.put("bairro",cliente.get(x).getBairro());
		    values.put("celular", cliente.get(x).getCelular());
		    values.put("contato", cliente.get(x).getContato());
		    values.put("complemento",cliente.get(x).getComplemento());
		    values.put("cep", cliente.get(x).getCep());
		    values.put("estado", cliente.get(x).getEstado());
		    values.put("numero", cliente.get(x).getNumero());
		    values.put("referencia", cliente.get(x).getReferencia());
		    values.put("rua",cliente.get(x).getrua());
		    values.put("telefone", cliente.get(x).getTelefone());
		    values.put("codcliente",cliente.get(x).getCodcliente());
		    values.put("cidade", cliente.get(x).getCidade());
		    bd.insert("Cliente", null, values);
   	 }
		bd.close();
    	database.close();
	}
	public void inserirDadosUsuario(ArrayList<Usuario> usuario){
	bd = database.getWritableDatabase();
   	 for(int x = 0; x < usuario.size();x++)
   	 {
		    ContentValues values = new ContentValues();
		    values.put("nome", usuario.get(x).getUsuario());
		    values.put("senha",usuario.get(x).getSenha());
		    values.put("COD_NOME", usuario.get(x).getCod_usuario());
		    bd.insert("Usuario", null, values);
		   Log.i("insirido no banco local", "usuario:"+usuario.get(x).getUsuario()+" " +"autoentrar:"+usuario.get(x).getAutoEntra()+ " "+usuario.get(x).getCod_usuario());
   	 }
     bd.close();
	 database.close();
	}
	public void inserirDadosModelo(ArrayList<modelo> modelo) {
		// TODO Auto-generated method stub
		bd = database.getWritableDatabase();
	   	 for(int x = 0; x < modelo.size();x++)
	   	 {
			    ContentValues values = new ContentValues();
			    values.put("nome", modelo.get(x).getNome());
			    values.put("codmaterial",modelo.get(x).getCodmaterial());
			    values.put("codmodelo", modelo.get(x).getCodmodelo());
			    values.put("h1", modelo.get(x).getH1());
			    values.put("largura", modelo.get(x).getLargura());
			    values.put("tamanho", modelo.get(x).getTamanho());
			    bd.insert("modelo", null, values);
			   
	   	 }
	     	bd.close();
		    database.close();
	}
	public void inserirDadosPedidos(ArrayList<Pedidos> Pedidos) {
		// TODO Auto-generated method stub
		Log.i("inserirp", "ok");
		bd = database.getWritableDatabase();
	   	 for(int x = 0; x < Pedidos.size();x++)
	   	 {
			    ContentValues values = new ContentValues();
			    values.put("cliente", Pedidos.get(x).getCliente());
			    values.put("data_venda", Pedidos.get(x).getData_venda());
			    values.put("Ir_dia", Pedidos.get(x).getIr_dia());
			    values.put("Ir_hora", Pedidos.get(x).getIr_hora());
			    values.put("ligou_dia", Pedidos.get(x).getLigou_dia());
			    values.put("ligou_hora", Pedidos.get(x).getLigou_hora());
			    values.put("tipo_de_servico", Pedidos.get(x).getTipo_de_servico());
			    values.put("codpedido", Pedidos.get(x).getCodpedido());
			    values.put("prazo_de_entrega", Pedidos.get(x).getPrazo_de_entrega());
			    
			    bd.insert("pedidos", null, values);
	   	 }
	     bd.close();
		 database.close();
	}
	public void inserirDadosMaterial(ArrayList<material> Material) {
		// TODO Auto-generated method stub
		bd = database.getWritableDatabase();
	   	 for(int x = 0; x < Material.size();x++)
	   	 {
			    ContentValues values = new ContentValues();
			    
			    values.put("babado", Material.get(x).getBabado());
			    values.put("cor", Material.get(x).getCor());
			    values.put("estrutura", Material.get(x).getEstrutura());
			    values.put("codmaterial", Material.get(x).getCodmaterial());
			    values.put("codpedido", Material.get(x).getCodpedido());
			  
			    bd.insert("material", null, values);
	   	 }
	     bd.close();
		 database.close();
	}
}
