package br.com.toldosjoia.Activity;



import br.com.toldosjoia.R;
import br.com.toldosjoia.ActivityDialog.carregar_backup_dialog;
import br.com.toldosjoia.ActivityDialog.salva_backup_dialog;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Configuracao_Activity extends PreferenceActivity{

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.configuracao);
        
        Preference prefbackup = (Preference) findPreference("fazer backup");
        prefbackup.setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Configuracao_Activity.this,salva_backup_dialog.class));
				return false;
			}
        	
        });
        
        Preference prefCbackup = (Preference) findPreference("carregar backup");
        prefCbackup.setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Configuracao_Activity.this,carregar_backup_dialog.class));
				return false;
			}});
        
        Preference prefDelete = (Preference)findPreference("deletar banco");
        prefDelete.setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alerta = new AlertDialog.Builder(Configuracao_Activity.this);
				alerta.setIcon(R.drawable.iconedeletebanco);
				alerta.setTitle("Deletar banco de dados");
				alerta.setMessage("Voce tem certeza que quer apagar os dados desta aplicação?");
				// Método executado se escolher Sim
				alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
			    	 Operacoes_no_banco_local banco_local = new Operacoes_no_banco_local(Configuracao_Activity.this.getApplication().getApplicationContext());
			    	   if(banco_local.apagarDatabase())
			    		   Toast.makeText(Configuracao_Activity.this.getApplication().getApplicationContext(), "Banco apagado com sucesso", Toast.LENGTH_SHORT).show();
			    	   else
			    		   Toast.makeText(Configuracao_Activity.this.getApplication().getApplicationContext(), "erro", Toast.LENGTH_SHORT).show();
					}
				});
				// Método executado se escolher Não
				alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
					}
				});
				// Exibe o alerta de confirmação
				alerta.show();
				return false;
			}
			});
        
        }
	
}
