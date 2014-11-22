package br.com.toldosjoia.Activity;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.zip.Inflater;

import br.com.toldosjoia.R;
import br.com.toldosjoia.ActivityDialog.carregar_backup_dialog;
import br.com.toldosjoia.ActivityDialog.salva_backup_dialog;
import br.com.toldosjoia.banco_de_dados.Operacoes_no_banco_local;
import br.com.toldosjoia.customView.fileListcarregar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class Configuracao_Activity extends PreferenceActivity{

	public View view;
	Context contexto;
	 	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        contexto = this;
        
		//this.getActionBar().setIcon(R.drawable.ic_action_previous_item);
		this.setTitle("Configurações");
		
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
				//startActivity(new Intent(Configuracao_Activity.this,carregar_backup_dialog.class));
				LayoutInflater Inflater = LayoutInflater.from(Configuracao_Activity.this);
				view = Inflater.inflate(R.layout.carregar_backup_layout, null);
				
					AlertDialog.Builder carregar = new AlertDialog.Builder(Configuracao_Activity.this);
					carregar.setView(view);
					carregar.setTitle("Carregar Backup");
					carregar.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							try {
						        File data = Environment.getDataDirectory();
						            
						            String currentDBPath = "//data//br.com.toldosjoia//databases//Toldos";
						          
						            File currentDB = new File(data, currentDBPath);
						            File backupDB = new File(TascaramAMaoNaGoiaba.caminho_backup);

						            if (backupDB.exists()) {
						                FileChannel src = new FileInputStream(backupDB).getChannel();
						                FileChannel dst = new FileOutputStream(currentDB).getChannel();
						                dst.transferFrom(src, 0, src.size());
						                src.close();
						                dst.close();
						             
						                 Toast.makeText(Configuracao_Activity.this, "Carregado", Toast.LENGTH_SHORT).show();
						              //  ((Activity)Configuracao_Activity.this).finish();
						                
						                
						            }
						            else
						            	Toast.makeText(Configuracao_Activity.this, "Sem base de dados", Toast.LENGTH_SHORT).show();
						       
						    } catch (Exception e) {
						    	Toast.makeText(Configuracao_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
						    }
				    	}
					});
					// Método executado se escolher Não
					carregar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							
						}
					});
					carregar.show();
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
				alerta.setMessage("Você tem certeza que quer apagar os dados desta aplicação?");
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
