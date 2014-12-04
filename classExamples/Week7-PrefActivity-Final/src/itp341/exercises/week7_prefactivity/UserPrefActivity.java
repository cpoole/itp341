package itp341.exercises.week7_prefactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

//public class UserPrefActivity extends PreferenceActivity {
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		getPreferenceManager().setSharedPreferencesName("user_prefs");
//		addPreferencesFromResource(R.xml.userprefs);
//	}
//}

public class UserPrefActivity extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(android.R.id.content, 
				new UserPrefFragment()).commit();
	}
	public static class UserPrefFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			PreferenceManager manager = getPreferenceManager();
			manager.setSharedPreferencesName(MainActivity.PREFERENCE_FILENAME);
			addPreferencesFromResource(R.xml.settings);
		}
	}
}
