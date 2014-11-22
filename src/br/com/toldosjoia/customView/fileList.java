package br.com.toldosjoia.customView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.toldosjoia.R;
import br.com.toldosjoia.ActivityDialog.salva_backup_dialog;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;




public class fileList extends ListView implements android.widget.AdapterView.OnItemClickListener { 
		
	 public fileList(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContexto = context;
		browseToRoot();
	}


    
	private File currentDirectory = new File("/");
	private List<iconeTexto> directoryEntries = new ArrayList<iconeTexto>();
	private Context mContexto;
	public String caminho_pasta;
    /** Called when the activity is first created. */

    
    private void browseToRoot() {
    	
		browseTo(Environment.getExternalStorageDirectory());
    }
    
 
    
    public boolean upOneLevel(){
		if(!this.currentDirectory.equals(Environment.getExternalStorageDirectory())){
			this.browseTo(this.currentDirectory.getParentFile());
			return true;
		}
		return false;
	}
	
	private void browseTo(final File aDirectory){

		if (aDirectory.isDirectory()){
			this.currentDirectory = aDirectory;
			this.caminho_pasta = aDirectory.getPath();
			Activity title = (Activity) mContexto;
			title.setTitle(caminho_pasta);
			fill(aDirectory.listFiles());
		}
	}
	
	

	
	
	private void fill(File[] files) {
		this.directoryEntries.clear();

		if(!this.currentDirectory.equals(Environment.getExternalStorageDirectory())){
			this.directoryEntries.add(new iconeTexto("..", 
					getResources().getDrawable(R.drawable.iconevoltar)));
		}
		Drawable currentIcon = null;
		for (File currentFile : files){
			if (currentFile.canRead()){
				if (currentFile.isDirectory()) {
					currentIcon = getResources().getDrawable(R.drawable.iconepasta);
					this.directoryEntries.add(new iconeTexto(currentFile.getPath(), currentIcon));
				}
				else if(currentFile.isFile()){
					if(currentFile.getName().toLowerCase().endsWith(".backup")){
					 currentIcon = getResources().getDrawable(R.drawable.iconebackup);
					 this.directoryEntries.add(new iconeTexto(currentFile.getPath(), currentIcon));
					}
				}
			} 
		}
		Collections.sort(this.directoryEntries);
		
		
		iconeTextoListAdapter itla = new iconeTextoListAdapter(mContexto);
		itla.setListaItens(this.directoryEntries);		
		this.setAdapter(itla);
		this.setOnItemClickListener(this);
		
	}





	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if(this.directoryEntries.size() > position){
			String selectedFileString = this.directoryEntries.get(position).getTexto();
			if(selectedFileString.equals("..")){
				this.upOneLevel();
			} else if(this.directoryEntries.get(position).getTexto().toLowerCase().endsWith(".backup")){
			   ((salva_backup_dialog)mContexto).ban.setText(this.directoryEntries.get(position).getTexto().substring(this.directoryEntries.get(position).getTexto().lastIndexOf("/")+1).replace(".backup", ""));
			  }else {
			    File clickedFile = new File(this.directoryEntries.get(position).getTexto());
				if(clickedFile != null)this.browseTo(clickedFile);
			}
		}
	}
}	


