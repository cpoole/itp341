package com.example.week4pizzaorder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	
	CheckBox checkBoxPepperoni;
	CheckBox checkBoxPineapple;
	CheckBox checkBoxTofu;
	RadioGroup radioGroupSize;
	SeekBar seekBarNumPizzas;
	TextView textSeekBarProgressNumPizzas;
	TextView textOrderDisplay;
	Spinner spinnerSpecials;
	EditText editName;
	boolean wantsPineapple = false;
	boolean wantsPepperoni = false;
	boolean wantsTofu = false;
	String size = "medium";
	String specials = "none";
	int numPizzas;
	String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        checkBoxPepperoni = (CheckBox) findViewById(R.id.checkPepperoni);
        checkBoxPineapple = (CheckBox) findViewById(R.id.checkPineapple);
        checkBoxTofu = (CheckBox) findViewById(R.id.checkTofu);
        radioGroupSize = (RadioGroup) findViewById(R.id.radioGroupSize);
        seekBarNumPizzas = (SeekBar) findViewById(R.id.seekBarNumPizzas);
        textSeekBarProgressNumPizzas = (TextView) findViewById(R.id.textNumPizzasSeekBarProgress);
        spinnerSpecials = (Spinner) findViewById(R.id.spinnerSpecials);
        textOrderDisplay = (TextView) findViewById(R.id.textOrderDisplay);
        editName = (EditText) findViewById(R.id.editName);
        
        //addingListeners
        //edit text listener
        editName.setOnEditorActionListener(new OnEditorActionListener(){
        	
        	public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        		Log.d(TAG, "onEditorAction");
        		name = ((EditText)v).getText().toString();
        		displayPizzaOrder();
        		return false;
        	}
        	
        });
        
        //checkbox listener
        checkBoxPepperoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				wantsPepperoni = isChecked;
				displayPizzaOrder();
			}
		});
 checkBoxPineapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				wantsPineapple = isChecked;
				displayPizzaOrder();
			}
		});
 checkBoxTofu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			wantsTofu = isChecked;
			displayPizzaOrder();
		}
	});
 	
 	seekBarNumPizzas.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
 	{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onProgressChanged");
			numPizzas = progress;
			
			textSeekBarProgressNumPizzas.setText(String.valueOf(numPizzas));
			displayPizzaOrder();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
 			
 	});
 	
 	//radioGroup
 	radioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId){
			case R.id.radioSmall:
				size = getApplicationContext().getResources().getString(R.string.label_small);
				break;
			case R.id.radioMedium:
				size = getApplicationContext().getResources().getString(R.string.label_medium);
				break;
			case R.id.radioLarge:
				size = getApplicationContext().getResources().getString(R.string.label_large);
				break;
			}
		  displayPizzaOrder();	
		}
	});
 	//spinner
 	spinnerSpecials.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> arg0, View v, int arg2,
				long arg3) {
			Log.d(TAG, "onItemSelected");
			specials = ((TextView) v).getText().toString();
			displayPizzaOrder();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
 		
 	});
 
    }
    
    private void displayPizzaOrder(){
    	Log.d(TAG, "displayPizzaOrder");
    	StringBuilder msg;
    	
    	if(isOrderValid())
    	{
    		msg = new StringBuilder("Order for ");
    		msg.append(name);
    		msg.append("\nYou ordered ");
    		
    		msg.append(String.valueOf(numPizzas));
    		msg.append(" ");
    		msg.append(size);
    		msg.append(" pizzas with the following toppings: chesse");
    		if(wantsPepperoni)
    		{
    			msg.append(", " + getApplicationContext().
    							getResources().
    							getString(R.string.label_pepperoni));
    			
    		}
    		if(wantsPineapple)
    		{
    			msg.append(", " + getApplicationContext().
    							getResources().
    							getString(R.string.label_pineappple));
    			
    		}
    		if(wantsTofu)
    		{
    			msg.append(", " + getApplicationContext().
    							getResources().
    							getString(R.string.label_tofu));
    			
    		}
    		msg.append("You will get the following specials: ");
    		msg.append(specials);
    		
    		
    	}
    	else
    	{
    		msg = new StringBuilder("Please complete your order form");
    	}
    	textOrderDisplay.setText(msg);
    }
    
    private boolean isOrderValid(){
    	Log.d(TAG, "isOrderValid");
    	
    	if(!editName.getText().toString().equals("") && radioGroupSize.getCheckedRadioButtonId() != -1)
    	{
    		return true;
    	}
    	return false;
    }
}
