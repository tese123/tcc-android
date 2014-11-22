package br.com.toldosjoia.customView;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class iconeTextoTextView extends LinearLayout {

	private ImageView mIcone;
	private TextView mTexto;

	public iconeTextoTextView(Context contexto,iconeTexto iconetexto) {
		super(contexto);
		this.setOrientation(HORIZONTAL);
	
		
		mIcone = new ImageView(contexto);
		mIcone.setImageDrawable(iconetexto.getIcone());
		mIcone.setPadding(0, 2, 5, 0);
		
		addView(mIcone, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		mTexto = new TextView(contexto);
		mTexto.setTextColor(getResources().getColor(android.R.color.black));
		
		if(iconetexto.getTexto().equals(".."))
			mTexto.setText(iconetexto.getTexto());
		else
		mTexto.setText(iconetexto.getTexto().substring(iconetexto.getTexto().lastIndexOf("/")+1));
		
		mTexto.setGravity(Gravity.CENTER_VERTICAL);
		
		addView(mTexto, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.FILL_PARENT));
	}
	public void setTexto(String texto){
		mTexto.setText(texto);
	}
	public void setIcone(Drawable icone)
	{
		mIcone.setImageDrawable(icone);
	}

}
