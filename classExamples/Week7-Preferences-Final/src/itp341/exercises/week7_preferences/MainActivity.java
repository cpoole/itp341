package itp341.exercises.week7_preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	public static final String PREFERENCE_FILENAME = "itp341.exercises.week7.app_prefs";
	public static final String PREFERENCE_KEY_USERNAME = "itp341.exercises.week7.username";

	TextView textDisplay;
	EditText editInfo;
	Button buttonSave;
	Button buttonLoad;
	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textDisplay = (TextView) findViewById(R.id.textDisplay);
		editInfo = (EditText) findViewById(R.id.editInfo);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonLoad = (Button) findViewById(R.id.buttonLoad);

		buttonSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "save onClick");
				prefs = getSharedPreferences(
						PREFERENCE_FILENAME, MODE_PRIVATE);
				SharedPreferences.Editor prefEditor = prefs.edit();
				Log.d(TAG, editInfo.getText().toString());
				prefEditor.putString(PREFERENCE_KEY_USERNAME, editInfo.getText().toString());
				prefEditor.commit();

			}
		});

		buttonLoad.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "Load onClick");
				SharedPreferences prefs = getSharedPreferences(
						PREFERENCE_FILENAME, MODE_PRIVATE);
				String s = prefs.getString(PREFERENCE_KEY_USERNAME, "default");
				textDisplay.setText(s);

			}
		});

	}
}
