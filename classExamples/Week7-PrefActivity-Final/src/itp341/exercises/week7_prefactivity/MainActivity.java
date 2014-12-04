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

		PreferenceManager.setDefaultValues(getApplicationContext(), 
				PREFERENCE_FILENAME,
				MODE_PRIVATE,
				R.xml.settings, 
				false);
		updateFromPreferences();


		buttonPrefs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						UserPrefActivity.class);
				startActivityForResult(i, 0);

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult");

		updateFromPreferences();


	}
	private void updateFromPreferences() {

		SharedPreferences prefs = getSharedPreferences(PREFERENCE_FILENAME,
				MODE_PRIVATE);
		String s = prefs.getString(PREFERENCE_KEY_POET, "No quote");
		textViewQuote.setText(s);

		String n = prefs.getString(PREFERENCE_KEY_NAME, "No name");
		textViewName.setText(n);

		if (prefs.getBoolean(PREFERENCE_KEY_RED_TEXT, false))
			textViewQuote.setTextColor(Color.parseColor("#ff0000"));
		else {
			textViewQuote.setTextColor(Color.parseColor("#000000"));
		}
	}

}
