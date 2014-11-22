package br.com.toldosjoia.Adapter;

import java.util.ArrayList;
import java.util.List;
import br.com.toldosjoia.R;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyPagerAdapter extends PagerAdapter {

	Context context;
	List<View> viewList;

	public MyPagerAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		viewList = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(context);
		View pg1 = inflater.inflate(R.layout.menu_agenda, null);
		View pg2 = inflater.inflate(R.layout.contato_menu, null);
		View pg3 = inflater.inflate(R.layout.menu_mapa, null);
		View pg4 = inflater.inflate(R.layout.menu_configuracoes, null);
	
		viewList.add(pg1);
		viewList.add(pg2);
		viewList.add(pg3);
		viewList.add(pg4);

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
}
