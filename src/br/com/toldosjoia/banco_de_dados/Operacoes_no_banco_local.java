package br.com.toldosjoia.banco_de_dados;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Pedidos;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.dados.material;
import br.com.toldosjoia.dados.modelo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class Operacoes_no_banco_local {

	protected SQLiteDatabase bd;
	protected Base_de_Dados database;
	private Context Contexto;


	public Operacoes_no_banco_local(Context contexto)
	{
		    this.Contexto = contexto;
			database = new Base_de_Dados(this.Contexto);
	}
	

	
	

	/*public void inserirDados(ArrayList<Usuario> usuario, ArrayList<Cliente> cliente)
	 {
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
	 } */
	
	public Boolean usuario(ArrayList<Usuario> usuario)
	{
    	bd = database.getReadableDatabase();
        
    	Cursor cursor = bd.rawQuery("SELECT * FROM usuario ", null);
    	cursor.moveToFirst();
    	if(cursor.getCount() == 0){
        	bd.close();
        	database.close();
        	Log.i("erro", "sem banco de dados" );
        	return false;
        }
    	getUsuarioCursor(usuario, cursor);
        
    	bd.close();
    	database.close();
    	return true;
	}
	private void getUsuarioCursor(ArrayList<Usuario> usuario,Cursor cursor)
	{
		for(int x = 0; x < cursor.getCount();x++,cursor.moveToNext())
    	{
    		usuario.add( new Usuario());
    		usuario.get(x).setUsuario(cursor.getString(0));
    		usuario.get(x).setSenha(cursor.getString(1));
    		usuario.get(x).setAutoEntra(cursor.getString(3).charAt(0));
    		usuario.get(x).setCod_usuario(cursor.getInt(2));
    	}
	}
	private void getClienteCursor(ArrayList<Cliente> cliente,Cursor cursor)
	{
		for(int x = 0; x < cursor.getCount();x++,cursor.moveToNext()){
		cliente.add(new Cliente());
		cliente.get(cliente.size()-1).setCodcliente(cursor.getInt(0));
		cliente.get(cliente.size()-1).setNome(cursor.getString(1));
		cliente.get(cliente.size()-1).setContato(cursor.getString(2));
		cliente.get(cliente.size()-1).setComplemento(cursor.getString(3));
		cliente.get(cliente.size()-1).setrua(cursor.getString(4));
		cliente.get(cliente.size()-1).setBairro(cursor.getString(5));
		cliente.get(cliente.size()-1).setNumero(cursor.getInt(6));
		cliente.get(cliente.size()-1).setTelefone(cursor.getString(7));
		cliente.get(cliente.size()-1).setEstado(cursor.getString(8));
		cliente.get(cliente.size()-1).setCep(cursor.getString(9));
		cliente.get(cliente.size()-1).setReferencia(cursor.getString(10));
		cliente.get(cliente.size()-1).setCelular(cursor.getString(11));
		cliente.get(cliente.size()-1).setCidade(cursor.getString(12));
		}
	}
    
	private void getMaterialCursor(ArrayList<material> material,Cursor cursor){
		for(int x = 0; x < cursor.getCount();x++,cursor.moveToNext()){
			material.add(new material());
			material.get(material.size()-1).setCodmaterial(cursor.getInt(0));
			material.get(material.size()-1).setBabado(cursor.getString(1));
			material.get(material.size()-1).setEstrutura(cursor.getString(2));
			material.get(material.size()-1).setCor(cursor.getString(3));
			material.get(material.size()-1).setCodpedido(cursor.getInt(4));
		}
	}
	private void getModeloCursor(ArrayList<modelo> modelo,Cursor cursor){
		for(int x = 0; x < cursor.getCount();x++,cursor.moveToNext()){
			modelo.add(new modelo());
			modelo.get(modelo.size()-1).setCodmodelo(cursor.getInt(0));
			modelo.get(modelo.size()-1).setNome(cursor.getString(1));
			modelo.get(modelo.size()-1).setLargura(cursor.getFloat(2));
			modelo.get(modelo.size()-1).setTamanho(cursor.getFloat(3));
			modelo.get(modelo.size()-1).setH1(cursor.getFloat(4));
			modelo.get(modelo.size()-1).setCodmaterial(cursor.getInt(5));
		}
	}
    private void getpedidosCursor(ArrayList<Pedidos> pedidos,Cursor cursor){
		for(int x = 0; x < cursor.getCount();x++,cursor.moveToNext()){
			pedidos.add(new Pedidos());
			pedidos.get(pedidos.size()-1).setData_venda(cursor.getString(0));
			pedidos.get(pedidos.size()-1).setPrazo_de_entrega(cursor.getInt(1));
			pedidos.get(pedidos.size()-1).setCodpedido(cursor.getInt(2));
			pedidos.get(pedidos.size()-1).setTipo_de_servico(cursor.getString(3));
			pedidos.get(pedidos.size()-1).setLigou_dia(cursor.getString(4));
			pedidos.get(pedidos.size()-1).setLigou_hora(cursor.getString(5));
			pedidos.get(pedidos.size()-1).setIr_dia(cursor.getString(6));
			pedidos.get(pedidos.size()-1).setIr_hora(cursor.getString(7));
			pedidos.get(pedidos.size()-1).setCliente(cursor.getString(8));
		}
    }
	public void delete_usuario()
	{
	   bd = database.getWritableDatabase();
	   bd.delete("usuario", "1", null);
	   bd.close();
	   database.close();
	}

    public ArrayList<Usuario> retorna_lista()
    {
    	ArrayList<Usuario> usuario_passar = new ArrayList<Usuario>();
    	bd = database.getReadableDatabase();
    	Cursor cursor = bd.rawQuery("SELECT * FROM usuario ", null);
    	cursor.moveToFirst();
        this.getUsuarioCursor(usuario_passar, cursor);
        bd.close();
    	database.close();
    	return usuario_passar;
    }
	public String getUltima_Atualizacao()
	{
		String ultima_atualizacao;
    	bd = database.getReadableDatabase();
    	Cursor cursor = bd.rawQuery("SELECT * FROM ULTIMA_ATUALIZACAO", null);
    	cursor.moveToFirst();
    	if(cursor.getCount() == 0){
    	   	bd.close();
        	database.close();
    		return null;
    	}
    	else ultima_atualizacao = cursor.getString(0);
    	bd.close();
    	database.close();
    	return ultima_atualizacao;
        
	}
	
    public void Desconectar(Usuario usuario)
    {
     	bd = database.getReadableDatabase();
    	ContentValues values = new ContentValues();
    	values.put("autoEntrar", "N");
        bd.update("Usuario", values, "COD_NOME = ?", new String[]{usuario.getCod_usuario()+""});
   	    bd.close();
   	    database.close();
    }
    
    public void entrar_automaticamente(Usuario usuario)
    {
     bd = database.getReadableDatabase();
	 ContentValues values = new ContentValues();
	 values.put("autoEntrar", "S");
		
     bd.update("Usuario", values, "COD_NOME = ?", new String[]{usuario.getCod_usuario()+""});
	 bd.close();
	 database.close();
    }
    public void getClientePorData(ArrayList<Pedidos> pedidos,ArrayList<Cliente> cliente ,Date data)
    {
    	
      bd = database.getReadableDatabase();
      Cursor cursor = bd.rawQuery("SELECT * FROM pedidos inner join cliente on pedidos.cliente = cliente.nome WHERE IR_DIA = ?", new String[]{new SimpleDateFormat("yyyy-MM-dd").format(data)});
      cursor.moveToFirst();
      getPedidosClienteCursor(pedidos,cliente, cursor);
	  bd.close();
	  database.close();
    }
    private void getPedidosClienteCursor(ArrayList<Pedidos> pedidos, ArrayList<Cliente> cliente, Cursor cursor){
    	
   
    	for(int x = 0;x < cursor.getCount();x++,cursor.moveToNext()){
    		pedidos.add(new Pedidos());
    		cliente.add(new Cliente());
    		
			pedidos.get(pedidos.size()-1).setData_venda(cursor.getString(0));
			pedidos.get(pedidos.size()-1).setPrazo_de_entrega(cursor.getInt(1));
			pedidos.get(pedidos.size()-1).setCodpedido(cursor.getInt(2));
			pedidos.get(pedidos.size()-1).setTipo_de_servico(cursor.getString(3));
			pedidos.get(pedidos.size()-1).setLigou_dia(cursor.getString(4));
			pedidos.get(pedidos.size()-1).setLigou_hora(cursor.getString(5));
			pedidos.get(pedidos.size()-1).setIr_dia(cursor.getString(6));
			pedidos.get(pedidos.size()-1).setIr_hora(cursor.getString(7));
			pedidos.get(pedidos.size()-1).setCliente(cursor.getString(8));
			
			cliente.get(cliente.size()-1).setCodcliente(cursor.getInt(9));
			cliente.get(cliente.size()-1).setNome(cursor.getString(10));
			cliente.get(cliente.size()-1).setContato(cursor.getString(11));
			cliente.get(cliente.size()-1).setComplemento(cursor.getString(12));
			cliente.get(cliente.size()-1).setrua(cursor.getString(13));
			cliente.get(cliente.size()-1).setBairro(cursor.getString(14));
			cliente.get(cliente.size()-1).setNumero(cursor.getInt(15));
			cliente.get(cliente.size()-1).setTelefone(cursor.getString(16));
			cliente.get(cliente.size()-1).setEstado(cursor.getString(17));
			cliente.get(cliente.size()-1).setCep(cursor.getString(18));
			cliente.get(cliente.size()-1).setReferencia(cursor.getString(19));
			cliente.get(cliente.size()-1).setCelular(cursor.getString(20));
			cliente.get(cliente.size()-1).setCidade(cursor.getString(21));
    	}
    }
	public void getCliente(ArrayList<Cliente> cliente) {
		// TODO Auto-generated method stub
		 bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM cliente ", null);
	      cursor.moveToFirst();
	      getClienteCursor(cliente, cursor);
		  bd.close();
		  database.close();
	}
	public void getPedido(ArrayList<Pedidos> pedidos) {
		// TODO Auto-generated method stub
		 bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM pedidos ", null);
	      cursor.moveToFirst();
	      getpedidosCursor(pedidos, cursor);
		  bd.close();
		  database.close();
	}
	
	public void getModelo(ArrayList<modelo> modelo) {
		// TODO Auto-generated method stub
		 bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM modelo ", null);
	      cursor.moveToFirst();
	      getModeloCursor(modelo, cursor);
		  bd.close();
		  database.close();
	}
	
	public void getModelo(ArrayList<modelo> modelo,int codmaterial) {
		// TODO Auto-generated method stub
		 bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM modelo where codmaterial = ?", new String[]{codmaterial+""});
	      cursor.moveToFirst();
	      getModeloCursor(modelo, cursor);
		  bd.close();
		  database.close();
	}
	public void getMaterial(ArrayList<material> material) {
		// TODO Auto-generated method stub
		 bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM material ", null);
	      cursor.moveToFirst();
	      getMaterialCursor(material, cursor);
		  bd.close();
		  database.close();
	}
	
	public void getMaterial(ArrayList<material> material, int codpedido) {
		// TODO Auto-generated method stub
		 bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM material where codpedido = ? ", new String[]{codpedido+""});
	      cursor.moveToFirst();
	      getMaterialCursor(material, cursor);
		  bd.close();
		  database.close();
	}
	public int getClientePorLetra(ArrayList<Cliente> cliente, char letra) {
		// TODO Auto-generated method stub
		bd = database.getReadableDatabase();
		  Cursor cursor = bd.rawQuery("SELECT * FROM cliente WHERE NOME LIKE "+"'"+letra+"%'", null);
	      cursor.moveToFirst();
	      getClienteCursor(cliente, cursor);
		  bd.close();
		  database.close();
		return cursor.getCount();
	}

	public Boolean ServicoUtilizado()
	{
    	bd = database.getReadableDatabase();
    	Cursor cursor = bd.rawQuery("SELECT * FROM SERVICE_ATIVO", null);
    	cursor.moveToFirst();
    	if(cursor.getCount() == 0){
    		ContentValues values = new ContentValues();
 		    values.put("aberto", "N");   
 		    bd.insert("SERVICE_ATIVO", null, values);
            bd.close();
            database.close();
            return false;
    	}
    	String aberto_ou_fechado = cursor.getString(0);
        bd.close();
        database.close();
        if(aberto_ou_fechado == "N")return false;
        else return true;
	}
	public Boolean apagarDatabase()
	{
	   return this.Contexto.deleteDatabase("Toldos");	
	}



		
}
