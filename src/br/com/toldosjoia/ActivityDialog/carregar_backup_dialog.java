package br.com.toldosjoia.ActivityDialog;

import br.com.toldosjoia.R;
import br.com.toldosjoia.customView.fileListcarregar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class carregar_backup_dialog extends Activity{

	fileListcarregar file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carregar_backup_layout);
		file = (fileListcarregar) findViewById(R.id.filecarregar);

		
	}



}
