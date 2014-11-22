package br.com.toldosjoia.customView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import br.com.toldosjoia.R;
import br.com.toldosjoia.Adapter.CalendarioAdapter;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class calendarioView extends LinearLayout implements OnClickListener, OnItemClickListener{

	
	LinearLayout calendarioLayout;
	Button voltar;
	TextView meses;
	Button proximo;
	GridView semanas_caledario;
	View View_convetida;
	GregorianCalendar Calendario;
	Date[] semana;
	private SimpleDateFormat 				FormatoMes;
	private SimpleDateFormat 				FormatoDia;
	private SimpleDateFormat 				FormatoAno;
	CalendarioAdapter calendarioadapter;
	OnDispatchDateSelectListener ListenerDataSelecionada;
	
	public interface OnDispatchDateSelectListener {
		public void onDispatchDateSelect(Date date);
	}
	public calendarioView(Context context,AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		FormatoMes		= new SimpleDateFormat("MMMM");
		FormatoDia			= new SimpleDateFormat("d");
		FormatoAno		= new SimpleDateFormat("yyyy");
		  
		
		
		View_convetida = LayoutInflater.from(context).inflate(R.layout.calendario, this);
		semanas_caledario=(GridView) View_convetida.findViewById(R.id.calendario_semanas);
		
		semanas_caledario.setOnItemClickListener(this);
	
		voltar = (Button) View_convetida.findViewById(R.id.Semana_anterior);
		meses = (TextView) findViewById(R.id.calendar_month);
		proximo = (Button) View_convetida.findViewById(R.id.Semana_proxima);
		
		voltar.setOnClickListener(this);
		proximo.setOnClickListener(this);
		
		Calendario = new GregorianCalendar();
		
		Calendario.setTime(new Date());
				
	    semana = new Date[7];
	    for(int i=0;i<7;i++)
	    {
	    	semana[i] = Calendario.getTime();
	    	
	    	Calendario.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    calendarioadapter = new CalendarioAdapter(context, semana);
	    semanas_caledario.setAdapter(calendarioadapter);
	    Mes_Selecionado();	
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view,int position,
			long id) {
		// TODO Auto-generated method stub
		clearBackground(null);
		view.setBackgroundColor(Color.rgb(255, 255, 255));
		view.setScrollBarStyle(getResources().getColor(R.color.customgray));
		((TextView)((LinearLayout) view.findViewById(R.id.definirPropriedades)).getChildAt(0)).setTextColor(getResources().getColor(R.color.hilight));
		((TextView)((LinearLayout) view.findViewById(R.id.definirPropriedades)).getChildAt(1)).setTextColor(getResources().getColor(R.color.hilight));
		ListenerDataSelecionada.onDispatchDateSelect(semana[position]);		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.Semana_anterior:
				SemanaAnterior();
			break;
			case R.id.Semana_proxima:
				proximaSemana();
			break;
		}
	}
	private void proximaSemana()
	{
		for(int i=0;i<7;i++)
		{
			semana[i]=Calendario.getTime();
	    	Calendario.add(Calendar.DAY_OF_YEAR, 1);
		}
		calendarioadapter.notifyDataSetChanged();
		Mes_Selecionado();
		clearBackground(null);
	}
	private void SemanaAnterior()
	{
		 Calendario.add(Calendar.DAY_OF_YEAR, -14);
		 for(int i=0;i<7;i++)
		 {
		    	semana[i]=Calendario.getTime();
		    	Calendario.add(Calendar.DAY_OF_YEAR, 1);
		}
		 calendarioadapter.notifyDataSetChanged();
		 Mes_Selecionado();

		 clearBackground(null);
	}
	private void clearBackground(View view)
	{
		for(int i=0;i<semanas_caledario.getCount();i++)
		{
			semanas_caledario.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
			((TextView)((LinearLayout)semanas_caledario.getChildAt(i).findViewById(R.id.definirPropriedades)).getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
			((TextView)((LinearLayout)semanas_caledario.getChildAt(i).findViewById(R.id.definirPropriedades)).getChildAt(1)).setTextColor(getResources().getColor(R.color.white));
		}
		
		
	}
	private void Mes_Selecionado()
	{
		String mes;
		if(Integer.parseInt(FormatoDia.format(semana[0]))>Integer.parseInt(FormatoDia.format(semana[6])))
			mes=FormatoMes.format(semana[0])+" / "+FormatoMes.format(semana[6]);
		else
			mes=FormatoMes.format(semana[0]);
		
		meses.setText(mes+" "+FormatoAno.format(semana[6]));
	}
	public void setOnDispatchDateSelectListener(OnDispatchDateSelectListener v) {
		ListenerDataSelecionada = v;
	}
}
