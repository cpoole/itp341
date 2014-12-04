package itp341.poole.connor.a5.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NoteList extends ListActivity {
	
	String[] noteTitles;
	String[] noteDates;
	Note[] notes;
	ArrayList<String> listNoteTitles = new ArrayList<String>();
	ArrayList<String> listNoteDates = new ArrayList<String>();
	SimpleAdapter adapter;
	ActionBar actionBar;
	ListView mainList;
	
	private GestureDetectorCompat mDetector; 
	
	public static final String EXTRA_NOTE_TITLE = "itp.341.poole.connor.a5.app.note_title";
	public static final String EXTRA_NOTE_CONTENT = "itp.341.poole.connor.a5.app.note_content";
	public static final String EXTRA_NOTE_INDEX = "itp.341.poole.connor.a5.app.note_index";
	public static final String PREFERENCE_FILENAME = "itp.341.poole.connor.a5.app.app_prefs";
	public static final String PREFERENCE_INITIAL_BOOT = "itp.341.poole.connor.a5.app.initial_boot";
	
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_list);
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		mDetector = new GestureDetectorCompat(this, new MyGestureListener());
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		getListView().setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				 int x = (int)event.getX();
				 int y = (int)event.getY();
				 Log.d("NoteList","x coordinate"+x);
				 Log.d("NoteList","y coordinate"+y);
				return false;
			}
		});
		actionBar = getActionBar();
		prefs = getSharedPreferences(PREFERENCE_FILENAME, MODE_PRIVATE);
		String FileContent = "";
		if(prefs.getString(PREFERENCE_INITIAL_BOOT, "default") == "default"){
			try {
				AssetManager am = getApplicationContext().getAssets();
				BufferedReader bfr = new BufferedReader(new InputStreamReader(am.open("notes.json")));
				while(true){
					String line = bfr.readLine();
					if (line == null)
						break;
					FileContent += line;	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			SharedPreferences.Editor prefEditor = prefs.edit();
			prefEditor.putString(PREFERENCE_INITIAL_BOOT,"false");
			prefEditor.commit();
		}else{
			FileWriter fread = new FileWriter(getApplicationContext());
			FileContent = fread.readFile("notes.json");
		}

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
		notes = gson.fromJson(FileContent,  Note[].class);
		
		

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.note_list_actionbar, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_add:
			Intent i = new Intent(this, NoteEditor.class);
	        i.putExtra(EXTRA_NOTE_TITLE, "");
	        i.putExtra(EXTRA_NOTE_CONTENT, "");
	        i.putExtra(EXTRA_NOTE_INDEX, notes.length +1);
	        startActivityForResult(i,0);
	        return true;
	    default:
	    	return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onStart(){
		super.onStart();
		adapter= null;
		listNoteTitles.clear();
		listNoteDates.clear();
		Log.d("notelist","notes length = "+ notes.length);
		for(int i=0; i< notes.length; i++){
			listNoteTitles.add(notes[i].getTitle());
			listNoteDates.add(new SimpleDateFormat("dd-MM-yyyy").format(notes[i].getDateModified()));
		}
		noteTitles = new String[listNoteTitles.size()];
		noteDates = new String[listNoteDates.size()];
		noteTitles = listNoteTitles.toArray(noteTitles);
		noteDates = listNoteDates.toArray(noteDates);
		Log.d("notelist","notes length = "+ noteTitles.length);
		////////////////CREATING THE LIST VIEW ADAPTER///////////////////
		List<HashMap<String,String>> hashList = new ArrayList<HashMap<String,String>>();
		for(int i=0; i<noteTitles.length; i++){
			Log.d("notelist","hello from loop");
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("title", noteTitles[i]);
			hm.put("date", noteDates[i]);
			//hm.put("checkbox", "");
			hashList.add(hm);
		}
		//Keys used for hashmaps
		String[] keys = {"title","date"};//,"checkbox"};
		// Id's for text views in listview layout
		int[] ids = {R.id.title, R.id.date};//, R.id.checkbox};
		adapter = new SimpleAdapter(
				this, 
				hashList, 
				R.layout.single_row,
				keys,
				ids);
		getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);		
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
		///////////////////////////////////////////////////////////////////
	}
	@Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        super.onListItemClick(parent, v, position, id); 
        Intent i = new Intent(this, NoteEditor.class);
        i.putExtra(EXTRA_NOTE_TITLE, notes[position].getTitle());
        i.putExtra(EXTRA_NOTE_CONTENT, notes[position].getContent());
        i.putExtra(EXTRA_NOTE_INDEX, position);
        startActivityForResult(i,0);
        
    }
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != 0){
			SimpleDateFormat sdf = new SimpleDateFormat(Note.ISO_DATE_FORMAT);
			Date currentDate= new Date();
			Log.d("NoteList","result code = "+resultCode);
			int noteIndex = data.getIntExtra(EXTRA_NOTE_INDEX, 0);
			if(noteIndex<= notes.length){
				notes[noteIndex].setTitle(data.getStringExtra(EXTRA_NOTE_TITLE));
				notes[noteIndex].setContent(data.getStringExtra(EXTRA_NOTE_CONTENT));
				notes[noteIndex].setDateModified(currentDate);
			}else{
				Note newNote = new Note();
				newNote.setTitle(data.getStringExtra(EXTRA_NOTE_TITLE));
				newNote.setContent(data.getStringExtra(EXTRA_NOTE_CONTENT));
				newNote.setDateCreated(currentDate);
				newNote.setDateModified(currentDate);
				Note[] oldNotes = notes;
				Note[] newNotes = new Note[notes.length+1];
				for(int i=0; i<oldNotes.length; i++){
					newNotes[i]=oldNotes[i];
				}
				newNotes[newNotes.length-1] = newNote;
				notes = newNotes;
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
			String json = gson.toJson(notes);
			FileWriter fout = new FileWriter(getApplicationContext());
			fout.writeFile("notes.json",json);
	
			SharedPreferences.Editor prefEditor = prefs.edit();
			prefEditor.putString(PREFERENCE_INITIAL_BOOT,"false");
			prefEditor.commit();
		}
	}
// 	This works outside of the list.
//	@Override 
//    public boolean onTouchEvent(MotionEvent event){
//        this.mDetector.onTouchEvent(event);
//        int x = (int)event.getX();
//	    int y = (int)event.getY();
//	    Log.d("NoteList","x coordinate"+x);
//	    Log.d("NoteList","y coordinate"+y);
//        return super.onTouchEvent(event);
//    }
    
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        @Override
        public boolean onDown(MotionEvent event) { 
            Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }
    }
		
}
