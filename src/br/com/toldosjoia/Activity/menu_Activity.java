package br.com.toldosjoia.Activity;

import java.io.File;
import java.util.ArrayList;

import br.com.toldosjoia.Adapter.MyPagerAdapter;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.banco_de_dados.atualizar_dados;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.webService.CelulaREST;
import br.com.toldosjoia.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class menu_Activity extends Activity implements OnClickListener {
	private ImageView mScroll1;
	private ImageView mScroll2;
	private ImageView mScroll3;
	private ImageView mScroll4;
	
	private TextView mTextView1;
	private TextView mTextView2;
	private TextView mTextView3;
	private TextView mTextView4;
    private ViewPager pager;
	private Usuario usuario;
	private Operacoes_no_banco_local banco_local;
	private File currentDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		File data = Environment.getDataDirectory();
		  String currentDBPath = "//data//br.com.toldosjoia//databases//Toldos";
        currentDB = new File(data, currentDBPath);
		usuario = (Usuario) getIntent().getSerializableExtra("usuario");
    	mScroll1 = (ImageView) findViewById(R.id.scroll_1);
 		mScroll2 = (ImageView) findViewById(R.id.scroll_2);
 		mScroll3 = (ImageView) findViewById(R.id.scroll_3);
 		mScroll4 = (ImageView) findViewById(R.id.scroll_4);
 		
 		mTextView1 = (TextView) findViewById(R.id.textViewAgendaMenu);
 		mTextView2 = (TextView) findViewById(R.id.textViewContatosMenu);
 		mTextView3 = (TextView) findViewById(R.id.textViewMapaMenu);
 		mTextView4 = (TextView) findViewById(R.id.textViewConfiguracoesMenu);
 		
 		mTextView1.setOnClickListener(this);
 		mTextView2.setOnClickListener(this);
        mTextView3.setOnClickListener(this);
        mTextView4.setOnClickListener(this);
 		this.setTitle(usuario.getUsuario());
 		
 		banco_local = new Operacoes_no_banco_local(this);
    	
    	this.getActionBar().setIcon(R.drawable.usuario);
 	   pager = (ViewPager) findViewById(R.id.pagina);
	   pager.setAdapter(new MyPagerAdapter(this));
	   pager.setOnPageChangeListener(new OnPageChangeListener() {

	@Override
	public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				setCurrentScroll(arg0);
			}
	@Override
	public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}});}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
	    switch(item.getItemId())
	    {
	     case R.id.atualizarMenu:
	    	         if(currentDB.exists())iniciar_Atualizacao(); 
	    	         else Toast.makeText(this, "Sem banco de dados", Toast.LENGTH_SHORT).show();
	    	         return true;
	     case R.id.desconectarMenu:
	    	         if(currentDB.exists())desconectar();
	    	         else Toast.makeText(this, "Sem banco de dados", Toast.LENGTH_SHORT).show();
	    	          
	    	          return true;
	     case R.id.sairMenu:
	                  this.finish();
	                  return true;
	    }
        return true;	
	}


	private void setCurrentScroll(int position) {
		if (mScroll1 != null && mScroll2 != null && mScroll3 != null  && mScroll4 != null) {
			mScroll1.setVisibility(position == 0 ? View.VISIBLE
					: View.INVISIBLE);
			mScroll2.setVisibility(position == 1 ? View.VISIBLE
					: View.INVISIBLE);
			mScroll3.setVisibility(position == 2 ? View.VISIBLE
					: View.INVISIBLE);
			mScroll4.setVisibility(position == 3 ? View.VISIBLE
					: View.INVISIBLE);
		}
		
		if(position == 0)
		mTextView1.setTextAppearance(this , android.R.style.TextAppearance_Medium);
		else mTextView1.setTextAppearance(this, android.R.style.TextAppearance);
		mTextView1.setTextColor(getResources().getColor(R.color.dimGray));
		
		if(position == 1)
			mTextView2.setTextAppearance(this , android.R.style.TextAppearance_Medium);
		else mTextView2.setTextAppearance(this, android.R.style.TextAppearance);
		mTextView2.setTextColor(getResources().getColor(R.color.dimGray));
		if(position == 2)
			mTextView3.setTextAppearance(this , android.R.style.TextAppearance_Medium);
		else mTextView3.setTextAppearance(this, android.R.style.TextAppearance);
		mTextView3.setTextColor(getResources().getColor(R.color.dimGray));
		if(position == 3)
		mTextView4.setTextAppearance(this , android.R.style.TextAppearance_Medium);
		else mTextView4.setTextAppearance(this, android.R.style.TextAppearance);
		mTextView4.setTextColor(getResources().getColor(R.color.dimGray));
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int numeroPg = 0;
		switch(v.getId()){
		case R.id.textViewAgendaMenu:
			   numeroPg = 0;
			   break;
		case R.id.textViewContatosMenu:
			   numeroPg = 1;
			   break;
		case R.id.textViewMapaMenu:
			   numeroPg = 2;
			   break;
		case R.id.textViewConfiguracoesMenu:
			numeroPg = 3;
			  break;
		}
		pager.setCurrentItem(numeroPg, true);
	}
    public void abrir_Contato(View v){
    	if(currentDB.exists())
    	startActivity(new Intent(this,Contatos_Activity.class));
    	else Toast.makeText(this, "Sem banco de dados", Toast.LENGTH_SHORT).show();
    }
    public void abrir_Mapa(View v){
    	if(currentDB.exists())
    	startActivity(new Intent(this,Mapa_Activity.class));
    	else Toast.makeText(this, "Sem banco de dados", Toast.LENGTH_SHORT).show();
    }
    public void desconectar()
    {
        banco_local.Desconectar(usuario);
		Intent intent = new Intent(this,Login_Activity.class);
		intent.putExtra("Usuario",banco_local.retorna_lista());
		startActivity(intent);
		  overridePendingTransition(R.anim.back_incoming_slide, R.anim.back_outgoing_slide);
		this.finish();
    }
    public void abrir_Agenda(View v){
    	if(currentDB.exists())
    	startActivity(new Intent(this,Calendario_Activity.class));
    	else Toast.makeText(this, "Sem banco de dados", Toast.LENGTH_SHORT).show();
    }
    public void abrir_Configuracoes(View v){
    
    	startActivity(new Intent(this,Configuracao_Activity.class));
    	
    }
    private void atualizar() 
    {
        try{
    	String dataEhoraAtual;
    	
    	dataEhoraAtual = banco_local.getUltima_Atualizacao();
 
        
    	CelulaREST rest = new CelulaREST(this);
    	
    	int NovosDadosCliente = rest.getListClienteAtualizada(dataEhoraAtual);
    	int NovosDadosUsuario = rest.getListUsuarioAtualizada(dataEhoraAtual);
     	int NovosDadosPedidos = rest.getListaPedidosAtualizada(dataEhoraAtual);
       	int NovosDadosMaterial = rest.getListaMaterialAtualizada(dataEhoraAtual);
       	int NovosDadosModelo = rest.getListaModeloAtualizada(dataEhoraAtual);
       	
    	if(NovosDadosCliente == 2 || NovosDadosUsuario == 2 || NovosDadosPedidos == 2 || NovosDadosMaterial == 2 || NovosDadosModelo == 2){
        new atualizar_dados(this).Atualizar_ULTIMA_ATUALIZACAO();
        banco_local.getUltima_Atualizacao();
		Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
		}
    	else if(NovosDadosCliente == 0 && NovosDadosUsuario == 0 && NovosDadosPedidos == 0 && NovosDadosMaterial == 0 && NovosDadosModelo == 0)
    		Toast.makeText(this, "Erro na conexão", Toast.LENGTH_SHORT).show();
    	
    	else
    		Toast.makeText(this, "sem dados novos", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
        	Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void inserir_entrada_automatica()
    {
    	if(usuario.getAutoEntra() == 'S'){
    		banco_local.entrar_automaticamente(usuario);
    	}
    }
    private void iniciar_Atualizacao()
    {
 	   new Thread() {
 		   
 	      @Override
 		   public void run(){
 	    	 atualizar(); 
 	       }
 	   }.run();
    }
}
