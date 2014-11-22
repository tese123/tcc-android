package br.com.toldosjoia.Activity;

import java.util.ArrayList;
import java.util.List;

import br.com.toldosjoia.Adapter.mostrar_dados_paginaAdapter;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.dados.Cliente;
import br.com.toldosjoia.dados.Pedidos;
import br.com.toldosjoia.dados.material;
import br.com.toldosjoia.dados.modelo;
import br.com.toldosjoia.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

public class mostra_dados_Activity extends Activity implements OnPageChangeListener {
               
	  ViewPager pagina;
	  Cliente cliente;
	  Pedidos pedido;
	  ArrayList<material> material = new ArrayList<material>();
	  ArrayList<modelo> modelo = new ArrayList<modelo>();
      Operacoes_no_banco_local banco_local = new Operacoes_no_banco_local(this);
	   public void onCreate(Bundle savedInstanceState){
            	   super.onCreate(savedInstanceState);
            	   setContentView(R.layout.mostrar_dados_layout);
            	   
            	  cliente = (Cliente) this.getIntent().getSerializableExtra("cliente");
            	  pedido = (Pedidos) this.getIntent().getSerializableExtra("pedidos");
            	 banco_local.getMaterial(material,pedido.getCodpedido());
            	 for(int x = 0; x < material.size();x++)
            	 banco_local.getModelo(modelo,material.get(x).getCodmaterial());
            	  this.setTitle("Cliente: "+cliente.getNome());
            	  pagina = (ViewPager) findViewById(R.id.paginaDados);
            	  pagina.setAdapter(new mostrar_dados_paginaAdapter(this));
            	  pagina.setOnPageChangeListener(this);
            	
            	  
               }
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		if(arg0 == 0)inserir_informacoes_do_cliente();
		if(arg0 == 1)if(modelo.size() > 0 && material.size() > 0)inserir_informacoes_do_material();
	}
	public void inserir_informacoes_do_cliente()
	{
		/*((TextView)(findViewById(R.id.TextViewClienteInformacaoRua))).setText(cliente.getrua());
		((TextView)(findViewById(R.id.TextViewClienteInformacaoBairro))).setText(cliente.getBairro());
		((TextView)(findViewById(R.id.TextViewClienteInformacaoCEP))).setText(" - "+cliente.getCep());
		((TextView)(findViewById(R.id.TextViewClienteInformacaoCidade))).setText(cliente.getCidade());
		((TextView)(findViewById(R.id.textViewClienteInformacaoEstado))).setText(" - "+cliente.getEstado());
		((TextView)(findViewById(R.id.TextViewClienteInformacaoN))).setText(" - "+cliente.getNumero()+"");
		((TextView)(findViewById(R.id.TextViewClienteInformacaoCelular))).setText(cliente.getCelular());
		((TextView)(findViewById(R.id.TextViewClienteInformacaoTelefone))).setText(cliente.getTelefone());
		
		if(pedido != null){
		((TextView)(findViewById(R.id.textViewInformacoesClienteServico))).setText(pedido.getTipo_de_servico());
		((TextView)(findViewById(R.id.textViewInformacoesPrazodeEntrega))).setText(pedido.getPrazo_de_entrega()+"");
		((TextView)(findViewById(R.id.textViewInformacoesClienteIrnoDia))).setText(pedido.getIr_dia()+"");
		}*/
	}
	public void inserir_informacoes_do_material(){
		if(material.size() > 0 && modelo.size() > 0){
		((TextView)(findViewById(R.id.textViewCoberturaEstrutura))).setText(material.get(0).getEstrutura());
		((TextView)(findViewById(R.id.textViewCoberturaEstruturaCor))).setText(material.get(0).getCor());
		((TextView)(findViewById(R.id.textViewCoberturaMaterialCor))).setText(material.get(0).getBabado());
		((TextView)(findViewById(R.id.textViewCoberturaModelo))).setText(modelo.get(0).getNome());
		}
		
	}
}
