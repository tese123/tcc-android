package br.com.toldosjoia.ActivityDialog;

import br.com.toldosjoia.R;
import br.com.toldosjoia.dados.Cliente;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contatos_Dialog extends Activity{
	Cliente cliente;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contatos_dialog_layout);
	    cliente = (Cliente) getIntent().getSerializableExtra("cliente");
         this.setTitle(cliente.getNome());
	    
	    ((TextView)findViewById(R.id.TextViewContatosDialogCidade)).setText(cliente.getCidade());
	    ((TextView)findViewById(R.id.TextViewContatosDialogBairro)).setText(cliente.getBairro());
	    ((TextView)findViewById(R.id.TextViewContatosDialogRua)).setText(cliente.getrua());
	    ((TextView)findViewById(R.id.TextViewContatosDialogN)).setText(cliente.getNumero()+"");
	    //((TextView)findViewById(R.id.TextViewContatosDialogCEP)).setText();
	    ((TextView)findViewById(R.id.TextViewContatosDialogTelefone)).setText(cliente.getTelefone());
	    ((TextView)findViewById(R.id.TextViewContatosDialogCelular)).setText(cliente.getCelular());
	}
    public void Ligar(View v)
    {
    	Uri uri = Uri.parse("tel:"+ cliente.getTelefone());
    	Intent intent = new Intent(Intent.ACTION_CALL,uri);
    	startActivity(intent);   
    }
    public void Mensagem(View v)
    {
    	Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.putExtra("address", cliente.getCelular());
		startActivity(smsIntent);
    }
}
