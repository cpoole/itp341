package personal.examples.multichoicelist;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	ListView myList; 
    Button getChoice; 
 
    String[] listContent = {
             "January",
             "February",
             "March",
             "April",
             "May",
             "June",
             "July",
             "August",
             "September",
             "October",
             "November",
             "December"
     };
  
    /** Called when the activity is first created. */
 
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice,
				listContent);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        getListView().setAdapter(adapter);
	}
}
