package itp341.poole.connor.a5.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEditor extends Activity {
	EditText noteTitle;
	EditText noteContent;
	Button saveButton;
	Intent recievedIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_edit);
		noteTitle = (EditText) findViewById(R.id.editNoteTitle);
		noteContent = (EditText) findViewById(R.id.editNoteContent);
		saveButton = (Button) findViewById(R.id.saveNote);
		
		recievedIntent = getIntent();
		noteTitle.setText(recievedIntent.getStringExtra(NoteList.EXTRA_NOTE_TITLE));
		noteContent.setText(recievedIntent.getStringExtra(NoteList.EXTRA_NOTE_CONTENT));
		saveButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent(getApplicationContext(), NoteList.class);
				returnIntent.putExtra(NoteList.EXTRA_NOTE_TITLE, noteTitle.getText().toString());
				returnIntent.putExtra(NoteList.EXTRA_NOTE_CONTENT, noteContent.getText().toString());
				returnIntent.putExtra(NoteList.EXTRA_NOTE_INDEX, recievedIntent.getIntExtra(NoteList.EXTRA_NOTE_INDEX, 0));
				setResult(1, returnIntent);
				finish();
				
			}
		});
	}
	@Override
	protected void onPause(){
		Intent returnIntent = new Intent(getApplicationContext(), NoteList.class);
		returnIntent.putExtra(NoteList.EXTRA_NOTE_TITLE, noteTitle.getText().toString());
		returnIntent.putExtra(NoteList.EXTRA_NOTE_CONTENT, noteContent.getText().toString());
		returnIntent.putExtra(NoteList.EXTRA_NOTE_INDEX, recievedIntent.getIntExtra(NoteList.EXTRA_NOTE_INDEX, 0));
		setResult(1, returnIntent);
		finish();
		super.onPause();
	}
}
