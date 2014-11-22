package br.com.toldosjoia.Adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.toldosjoia.R;
import br.com.toldosjoia.Activity.mostra_dados_Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class mostrar_dados_paginaAdapter extends PagerAdapter{

	Context contexto;
	List<View> viewList;
	mostra_dados_Activity mostrar;
	public mostrar_dados_paginaAdapter(Context contexto)
	{
		this.contexto = contexto;
		mostrar = (mostra_dados_Activity) contexto;
		viewList = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(contexto);
		View pg1 = inflater.inflate(R.layout.cliente_informacoes_layout, null);
		View pg2 = inflater.inflate(R.layout.cobertura_layout, null);
	
		viewList.add(pg1);
		viewList.add(pg2);
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
		if(position == 0)mostrar.inserir_informacoes_do_cliente();

		return viewList.get(position);
		
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(viewList.get(position));
	}

}
