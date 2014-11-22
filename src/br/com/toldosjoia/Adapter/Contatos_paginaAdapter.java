package br.com.toldosjoia.Adapter;

import java.util.ArrayList;
import java.util.List;
import br.com.toldosjoia.R;
import br.com.toldosjoia.Activity.Contatos_Activity;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.dados.Cliente;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Contatos_paginaAdapter extends PagerAdapter{

	Context contexto;
	List<View> viewList;
	ArrayList<String> letras;
	ArrayList<Cliente> cliente;
	Contatos_Activity contatos;
	LinearLayout LinearContatos;
	ArrayList<LinearLayout> pg = new ArrayList<LinearLayout>();
	

	private Operacoes_no_banco_local banco_local;
	private int quantidade_de_item_cada_letra;
	private int indice = 0;
	int y = 1;
	  int index_pg = 0;
	private final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public Contatos_paginaAdapter(Context contexto, ArrayList<String> letras, ArrayList<Cliente> cliente)
	{
		this.contexto = contexto;
		contatos = (Contatos_Activity) contexto;
		viewList = new ArrayList<View>();
		this.letras = letras;
		this.cliente = cliente;
		banco_local = new Operacoes_no_banco_local(this.contexto);
		adicionar_pagina();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		((ViewPager) container).addView(viewList.get(position));
		return viewList.get(position);
		
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(viewList.get(position));
	}
	 private void adicionar_pagina()
	 {
                for(int x = 0; x < alfabeto.length();x++){
	    		quantidade_de_item_cada_letra = banco_local.getClientePorLetra(cliente,alfabeto.charAt(x));
	    		if(quantidade_de_item_cada_letra > 0){
	    			letras.add(alfabeto.charAt(x)+"");
	    			LayoutInflater Inflater = LayoutInflater.from(this.contexto);
	    			pg.add((LinearLayout) Inflater.inflate(R.layout.contatos_layout, null));  
	    			adicionar_contatos();
					viewList.add(pg.get(index_pg));
					index_pg++;
	    		}  
           }
	    }
	    private void adicionar_contatos()
	    {
	    	for(int m = 0 ;m < quantidade_de_item_cada_letra;m++,indice++){
				LayoutInflater Inflater = LayoutInflater.from(this.contexto);
				
				Inflater.inflate(R.layout.contatos_nomes, (LinearLayout)pg.get(index_pg).findViewById(R.id.linearLayoutContatos));
				((LinearLayout)((LinearLayout)pg.get(index_pg).findViewById(R.id.linearLayoutContatos)).getChildAt(m)).setOnClickListener(contatos);
				((LinearLayout)((LinearLayout)pg.get(index_pg).findViewById(R.id.linearLayoutContatos)).getChildAt(m)).setId(indice);
	            ((TextView)((LinearLayout)pg.get(index_pg).findViewById(R.id.linearLayoutContatos)).getChildAt(m).findViewById(R.id.textViewContatosNome)).setText(cliente.get(indice).getNome());
	            ((TextView)((LinearLayout)pg.get(index_pg).findViewById(R.id.linearLayoutContatos)).getChildAt(m).findViewById(R.id.textViewContatosTelefone)).setText(cliente.get(indice).getTelefone());
	            ((TextView)((LinearLayout)pg.get(index_pg).findViewById(R.id.linearLayoutContatos)).getChildAt(m).findViewById(R.id.textViewContatosCelular)).setText(cliente.get(indice).getCelular());
	            
	    	}
	    }
}
