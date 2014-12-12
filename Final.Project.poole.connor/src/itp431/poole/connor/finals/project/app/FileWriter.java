package itp431.poole.connor.finals.project.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

public class FileWriter {
	private Context applicationContext;
	
	public FileWriter(Context context){
		this.applicationContext = context;
	}
	
	public void writeFile(String filename, String fileContents){
		try {
			File file = new File(applicationContext.getFilesDir(),filename);
			FileOutputStream fOut = applicationContext.openFileOutput(filename,Context.MODE_PRIVATE);
			fOut.write(fileContents.getBytes());
			fOut.close();
			Log.d("FileWriter","Successfully wrote to file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFile(String filename){
		String FileContent = "";
		try {
			FileInputStream fIn;
			fIn = applicationContext.openFileInput(filename);
			InputStreamReader isr = new InputStreamReader(fIn);
	        BufferedReader bfr = new BufferedReader(isr);
	        while(true){
	        	String line;
				line = bfr.readLine();
				if (line == null)
					break;
				FileContent += line;
	        }
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("noteList","file read failed");
		}
		return FileContent;
	}
	
}