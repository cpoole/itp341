package itp341.exercises.week9_autocomplete;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AutoCompleteAppActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_auto_complete_app);
		
		AutoCompleteTextView productNames = (AutoCompleteTextView) findViewById(R.id.product_names);
		MultiAutoCompleteTextView productNamesMultiple = (MultiAutoCompleteTextView) findViewById(R.id.product_names_multiple);
		
	}
}
