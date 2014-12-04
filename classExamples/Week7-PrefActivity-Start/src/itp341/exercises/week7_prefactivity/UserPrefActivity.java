package itp341.exercises.week7_prefactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/*
 * This code demonstrates how to use a PreferenceActivity (now deprecated)
 */
public class UserPrefActivity extends PreferenceActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getPreferenceManager().setSharedPreferencesName(MainActivity.PREFERENCE_FILENAME);
		addPreferencesFromResource(R.xml.settings);
	}
}

/*
 * This code demonstrates how to use a PreferenceFragment (now preferred)
 */
//public class UserPrefActivity extends PreferenceActivity {
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		FragmentManager manager = getFragmentManager();
//		FragmentTransaction transaction = manager.beginTransaction();
//		transaction.replace(android.R.id.content, 
//				new UserPrefFragment()).commit();
//	}
//	public static class UserPrefFragment extends PreferenceFragment {
//		@Override
//		public void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			PreferenceManager manager = getPreferenceManager();
//			manager.setSharedPreferencesName(MainActivity.PREFERENCE_FILENAME);
//			addPreferencesFromResource(R.xml.settings);
//		}
//	}
//}
