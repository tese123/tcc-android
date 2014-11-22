package br.com.toldosjoia.Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import br.com.toldosjoia.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.customView.calendarioView;
import br.com.toldosjoia.customView.calendarioView.OnDispatchDateSelectListener;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Pedidos;

public class Calendario_Activity extends Activity implements OnDispatchDateSelectListener, OnClickListener{
	private TextView 			data;
	private SimpleDateFormat 	Formato;
	private ArrayList<Cliente> cliente = new ArrayList<Cliente>();
    private ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();

	private LinearLayout hora;
	private Operacoes_no_banco_local banco_local;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.calendario_layout);
        hora = (LinearLayout)findViewById(R.id.line);
        data= (TextView)findViewById(R.id.dia_selecionado);
        banco_local = new Operacoes_no_banco_local(this);
        Formato = new SimpleDateFormat("EEEE d MMMM yyyy");
   
        
        ((calendarioView) findViewById(R.id.calendar)).setOnDispatchDateSelectListener(this);
        evento_data(new Date());
    }
	@Override
	public void onDispatchDateSelect(Date date) {
		evento_data(date);		
	}
	private void evento_data(Date data){
		this.data.setText(Formato.format(data));
		    hora.removeAllViews();
		    cliente.removeAll(cliente);
			pedidos.removeAll(pedidos);
	    	banco_local.getClientePorData(pedidos,cliente,data);
	    	
	   
	     
			for(int x = 0; x < pedidos.size();x++){
			
			LayoutInflater Inflater = LayoutInflater.from(this);
			
			Inflater.inflate(R.layout.pedido_selecione, hora);
		    ((TextView)((LinearLayout)hora.getChildAt(x)).findViewById(R.id.textViewPedidoNome)).setText(cliente.get(x).getNome());
		    ((TextView)((LinearLayout)hora.getChildAt(x)).findViewById(R.id.textViewPedidoTipo)).setText(pedidos.get(x).getTipo_de_servico());
		    ((TextView)((LinearLayout)hora.getChildAt(x)).findViewById(R.id.textViewPedido)).setText(cliente.get(x).getCidade());
		    ((TextView)((LinearLayout)hora.getChildAt(x)).findViewById(R.id.textViewPedidoHora)).setText(pedidos.get(x).getIr_hora().substring(0,5));
			
		    ((LinearLayout)hora.getChildAt(x)).setId(x);
		    ((LinearLayout)hora.getChildAt(x)).setOnClickListener(this);
		    
		    ((LinearLayout)hora.getChildAt(x)).setId(x);
		    ((LinearLayout)hora.getChildAt(x)).setOnClickListener(this);
		    
			}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, mostra_dados_Activity.class);
		intent.putExtra("cliente", cliente.get(v.getId()));
		intent.putExtra("pedidos", pedidos.get(v.getId()));
		startActivity(intent);
		
	}

}
