package br.com.toldosjoia.Activity;


import java.util.ArrayList;

import br.com.toldosjoia.R;
import br.com.toldosjoia.ActivityDialog.Contatos_Dialog;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.dados.Cliente;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Contatos_Activity extends Activity implements OnClickListener{
	
	LinearLayout LinearContatos; 
	private ArrayList<Cliente> cliente = new ArrayList<Cliente>();
	private Operacoes_no_banco_local banco_local;
	private int quantidade_de_item_cada_letra;
	private int indice = 0;
	int y = 1;
	private final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contatos_layout);
		banco_local = new Operacoes_no_banco_local(this);
		LinearContatos = (LinearLayout) findViewById(R.id.linearLayoutContatos);
		nao_sei();
	}
    private void nao_sei()
    {
    	for(int x = 0; x < alfabeto.length();x++)
    	{
    		quantidade_de_item_cada_letra = banco_local.getClientePorLetra(cliente,alfabeto.charAt(x));
    		if(quantidade_de_item_cada_letra > 0){
    			tambem_nao_sei(x);
    		}
    	}
    }
    private void tambem_nao_sei(int x)
    {
    
    
    	TextView letra = new TextView(this);
    	
    	
    	letra.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
    	letra.setTextAppearance(this, android.R.style.TextAppearance_Large);
		letra.setTextColor(getResources().getColor(android.R.color.black));
		letra.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		letra.setGravity(Gravity.CENTER);
    	letra.setText(alfabeto.charAt(x)+"");
    	LinearContatos.addView(letra);;
    	int aux = indice;
    	int z = y;
    	for( ;indice < quantidade_de_item_cada_letra+aux;indice++,z++){
			LayoutInflater Inflater = LayoutInflater.from(this);
			
			Inflater.inflate(R.layout.contatos_nomes, LinearContatos);
			((LinearLayout)LinearContatos.getChildAt(z)).setOnClickListener(this);
			((LinearLayout)LinearContatos.getChildAt(z)).setId(indice);
            ((TextView)((LinearLayout)LinearContatos.getChildAt(z)).findViewById(R.id.textViewContatosNome)).setText(cliente.get(indice).getNome());
            ((TextView)((LinearLayout)LinearContatos.getChildAt(z)).findViewById(R.id.textViewContatosTelefone)).setText(cliente.get(indice).getTelefone());
            ((TextView)((LinearLayout)LinearContatos.getChildAt(z)).findViewById(R.id.textViewContatosCelular)).setText(cliente.get(indice).getCelular());
    	}
       y = z+1;
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,Contatos_Dialog.class);
		intent.putExtra("cliente", cliente.get(v.getId()));
		startActivity(intent);
	}
}
