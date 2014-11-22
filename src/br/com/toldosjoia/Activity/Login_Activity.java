package br.com.toldosjoia.Activity;

import java.util.ArrayList;

import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.dados.Usuario;
import br.com.toldosjoia.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends Activity implements OnFocusChangeListener{
	
    private int Id;
	private EditText nome;
	private EditText senha;
	private CheckBox auto;
	private ArrayList<Usuario> usuario = new ArrayList<Usuario>();
	private Operacoes_no_banco_local banco_local;
	
	
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		usuario = (ArrayList<Usuario>) getIntent().getSerializableExtra("Usuario");
        banco_local = new Operacoes_no_banco_local(this);
		setContentView(R.layout.login_layout);
		nome = (EditText) findViewById(R.id.EditTextNome);
		senha = (EditText) findViewById(R.id.EditTextSenha);
		auto = (CheckBox) findViewById(R.id.checkBoxEntrarAuto);
		nome.setOnFocusChangeListener(this);
		senha.setOnFocusChangeListener(this);
	}

	public void Entrar(View v)
	{
    	if(autenticacao()){
    		if(auto.isChecked())banco_local.entrar_automaticamente(usuario.get(Id));
    		Intent intent = new Intent(this,menu_Activity.class);
    		intent.putExtra("usuario", usuario.get(Id));
    		startActivity(intent);
    		overridePendingTransition( R.anim.incoming_slide ,  R.anim.outgoing_slide );
    		this.finish();
    	}
    	else
    		Toast.makeText(this, "nome ou senha errado", Toast.LENGTH_SHORT).show();
		
	}
    private boolean autenticacao()
    {
    	boolean retorno = false;
    	for(int x = 0; x < usuario.size();x++)
    	{
    		Log.i("tabela", "NOME:"+usuario.get(x).getUsuario()+" senha:"+usuario.get(x).getSenha());
    		if(usuario.get(x).getUsuario().equals(nome.getText().toString()) &&
    		   usuario.get(x).getSenha().equals(senha.getText().toString()))
    			{
    			Id = x;
    			retorno = true;
    			break;
    			  
    			}
    	}
    	return retorno;
    }
    
    public void ClickEditTextUsuario(View v){
    	if(nome.getText().toString().equals("Usuário"))
    	{
    		nome.setText("");
    	}
    }
    
    public void ClickEditTextSenha(View v){
    	if(senha.getText().toString().equals("Senha"))
    	{
    		senha.setText("");
    		senha.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    	}
    }

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if(nome.getText().toString().equals("Usuário")){
			nome.setText("");
		}
		else if(senha.getText().toString().equals("Senha")){
			senha.setText("");
		}
		else if(nome.getText().toString().equals("")){
			nome.setText("Usuário");
		}
		else if(senha.getText().toString().equals("")){
			senha.setText("Senha");
		}
	}

}
