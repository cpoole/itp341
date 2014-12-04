package itp341.exercises.week7_prefactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String PREFERENCE_FILENAME = "settings";
	public static final String PREFERENCE_KEY_POET = "pref_key_poet";
	public static final String PREFERENCE_KEY_RED_TEXT = "pref_key_red_color";
	public static final String PREFERENCE_KEY_NAME = "pref_key_name";

	private static final String TAG = MainActivity.class.getSimpleName();


	TextView textViewQuote;
	TextView textViewName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		textViewQuote = (TextView) findViewById(R.id.textViewQuote);
		textViewName = (TextView) findViewById(R.id.textViewName);

		Button buttonPrefs = (Button) findViewById(R.id.buttonPrefs);




		buttonPrefs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), UserPrefActivity.class);
				startActivityForResult(i,0);
				

			}
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult");
		updateFromPreferences();



	}
	private void updateFromPreferences() {
		SharedPreferences prefs = getSharedPreferences(PREFERENCE_FILENAME,MODE_PRIVATE);
		String quote = prefs.getString(PREFERENCE_KEY_POET, "No Quote");
		String userName = prefs.getString(PREFERENCE_KEY_NAME, "No name");
		boolean redText= prefs.getBoolean(PREFERENCE_KEY_RED_TEXT, false);
		
		textViewName.setText(userName);
		textViewQuote.setText(quote);
		
		if(redText){
			textViewQuote.setTextColor(Color.RED);
		}else{
			textViewQuote.setTextColor(Color.BLACK);
		}
		
	}

}
