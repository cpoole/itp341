package itp341.poole.connor.a3.app;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	TextView percentValue;
	TextView tipValue;
	TextView totalValue;
	TextView personValue;
	TextView personString;
	EditText billValue;
	Spinner splitSpinner;
	SeekBar percentSeek;
	
	float tipPercentage = 15;
	int spinnerIndex = 0;
	double billNumber = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        percentValue = (TextView) findViewById(R.id.percentValue);
        tipValue = (TextView) findViewById(R.id.tipValue);
        totalValue = (TextView) findViewById(R.id.totalValue);
        personValue = (TextView) findViewById(R.id.personValue);
        personString = (TextView) findViewById(R.id.per_person);
        percentSeek = (SeekBar) findViewById(R.id.percentSeek);
        splitSpinner = (Spinner) findViewById(R.id.splitSpinner);
        billValue = (EditText) findViewById(R.id.billEntry);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.split_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        splitSpinner.setAdapter(adapter);
        
        
        personString.setVisibility(View.INVISIBLE);
        personValue.setVisibility(View.INVISIBLE);
        
        totalValue.setText("0"); 
        billValue.setText("");
        tipValue.setText("0");
        percentValue.setText("15%");
        
        
	    percentSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
	    	@Override
	    	public void onProgressChanged(SeekBar percentSeek, int progress, boolean fromUser){
	        	percentValue.setText(String.valueOf(progress)+ "%");
	        	tipPercentage = progress;
	        	calculateTip();
	        	
	        }
			@Override
			public void onStartTrackingTouch(SeekBar percentSeek) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStopTrackingTouch(SeekBar percentSeek) {
				// TODO Auto-generated method stub
				
			}
	    });
	    
	    splitSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(position != 0){
					personString.setVisibility(View.VISIBLE);
			        personValue.setVisibility(View.VISIBLE);
			        spinnerIndex = position;
			        calculateTip();
				}else{
					personString.setVisibility(View.INVISIBLE);
			        personValue.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });   	
	    
	    billValue.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try{
					if (!billValue.getText().toString().isEmpty()){
						billNumber = Double.parseDouble(billValue.getText().toString());
						calculateTip();
					}else{
						totalValue.setText("0"); 
						tipValue.setText("0");
						personValue.setText("0");
						
					}
				}catch (Exception e){
					totalValue.setText("0"); 
			        billValue.setText("");
			        tipValue.setText("0");
				}
			}
	    	
	    });
	    
    }
    public void calculateTip(){
    	if(billValue.getText() != null){
	    	tipValue.setText(Double.toString(Math.round((billNumber * (tipPercentage / 100.0))*100.0)/100.0));
	    	totalValue.setText(Double.toString(Math.round(((billNumber * (tipPercentage / 100))+billNumber)*100.0)/100.0));
	    	if(spinnerIndex != 0 ){
	    		personValue.setText(Double.toString(Math.round((((billNumber * (tipPercentage / 100))+billNumber)/(spinnerIndex+1) )*100.0)/100.0 ) );
	    	}
    	}
    }
    
}
