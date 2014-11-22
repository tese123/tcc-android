package br.com.toldosjoia.ActivityDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import br.com.toldosjoia.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.toldosjoia.customView.fileList;
public class salva_backup_dialog extends Activity{

	private fileList fileDialog;
	public EditText ban;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salva_backup_layout);
		fileDialog = (fileList) findViewById(R.id.button1);
		ban = (EditText) findViewById(R.id.editText1);
	}
	@Override
	public void onBackPressed() {
	
	    if(!fileDialog.upOneLevel())
	    	this.finish();
	}
	
  public void salvar(View view)
  {
	  String nome = ban.getText().toString().trim();
	  
	  try {
	        File data = Environment.getDataDirectory();
	        if (!nome.isEmpty()) {
	            String currentDBPath = "//data//br.com.toldosjoia//databases//Toldos";
	            String backupDBPath = nome +".backup";
	            File currentDB = new File(data, currentDBPath);
	            File backupDB = new File(fileDialog.caminho_pasta, backupDBPath);

	            if (currentDB.exists()) {
	                FileChannel src = new FileInputStream(currentDB).getChannel();
	                FileChannel dst = new FileOutputStream(backupDB).getChannel();
	                dst.transferFrom(src, 0, src.size());
	                src.close();
	                dst.close();
	                Toast.makeText(this, "salvo", Toast.LENGTH_SHORT).show();
	                this.finish();
	            }
	            else
	            	Toast.makeText(this, "Sem base de dados", Toast.LENGTH_SHORT).show();
	        }
            else
            	Toast.makeText(this, "nao posso ler", Toast.LENGTH_SHORT).show();
	    } catch (Exception e) {
	    	Toast.makeText(this, "erro ",Toast.LENGTH_SHORT).show();
	    }
  }
  public void Cancelar(View view)
  {
	  this.finish();
  }
}
