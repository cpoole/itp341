package itp341.inclass.week5formatting;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {

	EditText editNumerator;
	EditText editDenominator;
	TextView textDivisionOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
       editNumerator = (EditText) findViewById(R.id.editNum);
       editDenominator = (EditText) findViewById(R.id.editDen);
       textDivisionOutput = (TextView) findViewById(R.id.textDivisionOutput);
       
       editDenominator.setOnEditorActionListener(new OnEditorActionListener(){

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			// TODO Auto-generated method stub
			int numerator = Integer.parseInt(
								editNumerator.getText().toString());
			int denominator = Integer.parseInt(
					editDenominator.getText().toString());
			String msg = getApplicationContext().getResources().getString(
							R.string.label_division_output, 
							numerator,
							denominator,
							(double)numerator/denominator
							);
			textDivisionOutput.setText(msg);
			return false;
		}
    	   
       });
    }
}
