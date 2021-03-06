package itp341.poole.connor.a4.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	public static final String PREFERENCE_FILENAME = "itp341.a4.app_prefs";
	public static final String PREFERENCE_KEY_ORDERNAME = "itp341.a4.ordername";
	public static final String PREFERENCE_KEY_TOPPINGS = "itp341.a4.toppings";
	public static final String PREFERNCE_KEY_SIZE = "itp.341.a4.size";
	public static final String PREFERNCE_KEY_SPECIAL = "itp.341.a4.special";
	public static final String PREFERNCE_KEY_NUMPIZZA = "itp.341.a4.numpizza";

	EditText orderName;
	CheckBox pepperoniBox;
	CheckBox pineappleBox;
	CheckBox tofuBox;
	RadioGroup sizeRadioGroup;
	RadioButton smallRadio;
	RadioButton mediumRadio;
	RadioButton largeRadio;
	Spinner specialSpinner;
	SeekBar numPizzas;
	Button saveFaveButton;
	Button loadFaveButton;
	Button orderButton;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		orderName = (EditText) findViewById(R.id.editName);
		pepperoniBox = (CheckBox) findViewById(R.id.checkPepperoni);
		pineappleBox = (CheckBox) findViewById(R.id.checkPineapple);
		tofuBox = (CheckBox) findViewById(R.id.checkTofu);
		sizeRadioGroup = (RadioGroup) findViewById(R.id.radioGroupSize);
		smallRadio = (RadioButton) findViewById(R.id.radioSmall);
		mediumRadio = (RadioButton) findViewById(R.id.radioMedium);
		largeRadio = (RadioButton) findViewById(R.id.radioLarge);
		numPizzas = (SeekBar) findViewById(R.id.seekBarNumPizzas);
		saveFaveButton = (Button) findViewById(R.id.saveFave);
		loadFaveButton = (Button) findViewById(R.id.loadFave);
		orderButton = (Button) findViewById(R.id.orderPizza);
		
		specialSpinner = (Spinner) findViewById(R.id.spinnerSpecials);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.specials_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        specialSpinner.setAdapter(adapter);
        
        saveFaveButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				prefs = getSharedPreferences(
						PREFERENCE_FILENAME, MODE_PRIVATE);
				SharedPreferences.Editor prefEditor = prefs.edit();
				prefEditor.putString(PREFERENCE_KEY_ORDERNAME, orderName.getText().toString());
				int toppingNum = 0;
				if(tofuBox.isChecked()){
					toppingNum += 1;
				}
				if(pineappleBox.isChecked()){
					toppingNum += 2;
				}
				if(pepperoniBox.isChecked()){
					toppingNum += 4;
				}
				prefEditor.putInt(PREFERENCE_KEY_ORDERNAME, toppingNum);
				int sizeNum = 1;
				if(smallRadio.isChecked()){
					sizeNum = 1;
				}else if(mediumRadio.isChecked()){
					sizeNum = 2;
				}else{
					sizeNum = 3;
				}
				prefEditor.putInt(PREFERNCE_KEY_SIZE, sizeNum);
				prefEditor.commit();

			}
        });
        
        loadFaveButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = getSharedPreferences(
						PREFERENCE_FILENAME, MODE_PRIVATE);
				String s = prefs.getString(PREFERENCE_KEY_ORDERNAME, "default");
				orderName.setText(s);
				
			}
        });
		
        orderButton.setOnClickListener(new Button.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		
        	}
        });
        
        
		
	}
}
