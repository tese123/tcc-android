package br.com.toldosjoia.customView;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class iconeTextoListAdapter extends BaseAdapter {

	Context mContexto;
	private List<iconeTexto> mItens = new ArrayList<iconeTexto>();
	public iconeTextoListAdapter(Context contexto) {
		mContexto = contexto;
	}
	public void addItens(iconeTexto iconetexto){ mItens.add(iconetexto);}
	public void setListaItens(List<iconeTexto> lista){
		mItens = lista;
		
		}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItens.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItens.get(position);
	}
    public boolean todosItemSelecionado(){return false;}
    public boolean estaSelecionado(int posicao){
    	return mItens.get(posicao).estaSelecionado();
    }
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		iconeTextoTextView textViewTexto;
		if(convertView == null)
			textViewTexto = new iconeTextoTextView(mContexto, mItens.get(position));
		else{
			textViewTexto = (iconeTextoTextView) convertView;
			textViewTexto.setTexto(mItens.get(position).getTexto().substring(mItens.get(position).getTexto().lastIndexOf("/")+1));
			textViewTexto.setIcone(mItens.get(position).getIcone());
			
		}
		return textViewTexto;
	}

}
