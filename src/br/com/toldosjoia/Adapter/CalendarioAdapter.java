package br.com.toldosjoia.Adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import br.com.toldosjoia.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarioAdapter extends BaseAdapter {

	private Context 		 context;
    private Date[]  		 semana;
    private SimpleDateFormat 	formadoNumero;
    private SimpleDateFormat 	formadoDia;
    private LayoutInflater		Inflater;
  

	public CalendarioAdapter(Context context, Date[] semana) {
		// TODO Auto-generated constructor stub
	 	this.context 		= context; 
        this.semana			= semana; 
        formadoNumero	= new SimpleDateFormat("d");
        formadoDia  	= new SimpleDateFormat("EEE");
        Inflater 		= LayoutInflater.from(this.context);  
	}
	private static class View_calendario {
	    public TextView nome_do_dia_da_semana;
	    public TextView dia_da_semana;
    }
	@Override
	public int getCount() {
		
		return semana.length;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  View_calendario dias;
		if(convertView == null){
        convertView	= Inflater.inflate(R.layout.calendario_dia, null);
        dias = new View_calendario();
        dias.nome_do_dia_da_semana 	= (TextView)  convertView.findViewById(R.id.nomeDia);
        dias.dia_da_semana	= (TextView)  convertView.findViewById(R.id.numeroDia);
        convertView.setTag(dias);
		}else{
			dias = (View_calendario) convertView.getTag();
		}

        dias.nome_do_dia_da_semana.setText(formadoDia.format(semana[position]));
        dias.dia_da_semana.setText(formadoNumero.format(semana[position]));
		
        return convertView;
	}
}
