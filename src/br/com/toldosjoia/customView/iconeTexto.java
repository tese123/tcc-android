package br.com.toldosjoia.customView;

import android.graphics.drawable.Drawable;

public class iconeTexto implements Comparable<iconeTexto>{

	private String mTexto = "";
	private Drawable mIcone;
	private boolean mSelecionado = true;
	
	public iconeTexto(String texto,Drawable icone) {
		// TODO Auto-generated constructor stub
		setIcone(icone);
		setTexto(texto);
	}
	public boolean estaSelecionado(){
		return mSelecionado;
	}
	
	@Override
	public int compareTo(iconeTexto another) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//getter e setter
	public Drawable getIcone() {
		return mIcone;
	}
	public void setIcone(Drawable mIcone) {
		this.mIcone = mIcone;
	}
	public String getTexto() {
		return mTexto;
	}
	public void setTexto(String mTexto) {
		this.mTexto = mTexto;
	}
	public void setSelecionado(boolean selecao){
		mSelecionado = selecao;
	}

}
