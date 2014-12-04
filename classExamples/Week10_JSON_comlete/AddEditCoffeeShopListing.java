package itp341.exercises.week10_json.app;

import itp341.exercises.week10_json.R;
import itp341.exercises.week10_json.app.model.CoffeeShop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEditCoffeeShopListing extends Activity {

	private static final String TAG = AddEditCoffeeShopListing.class
			.getSimpleName();
	public static final String EXTRA_COFFEE_SHOP_OBJECT = "extra_coffee_shop_object";

	EditText editName;
	EditText editAddress;
	EditText editCity;
	Spinner spinnerState;
	EditText editZip;
	EditText editPhone;
	EditText editWebsite;
	Button buttonSaveListing;
	CoffeeShop coffeeShop;

	public static String[] states; // read from arrays.xml for US states
	ArrayAdapter<CharSequence> spinnerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_edit_coffee_shop_listing);

		editName = (EditText) findViewById(R.id.edit_name);
		editAddress = (EditText) findViewById(R.id.edit_address);
		editCity = (EditText) findViewById(R.id.edit_city);
		spinnerState = (Spinner) findViewById(R.id.spinner_state); // update
		editZip = (EditText) findViewById(R.id.edit_zip);
		editPhone = (EditText) findViewById(R.id.edit_phone);
		editWebsite = (EditText) findViewById(R.id.edit_website);
		buttonSaveListing = (Button) findViewById(R.id.button_save_listing);

		// phone formatter
		editPhone
				.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		
		spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.states,
				android.R.layout.simple_spinner_item);
		spinnerState.setAdapter(spinnerAdapter);
		
		buttonSaveListing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				saveAndClose();

			}
		});

		Intent i = getIntent();
		coffeeShop = (CoffeeShop) i
				.getSerializableExtra(EXTRA_COFFEE_SHOP_OBJECT);

		if (coffeeShop != null) { // this means we are editing old record
			loadData();
		}




	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();

	}

	// **********************************************************************************************
	// Button listener to Save
	// **********************************************************************************************

	private void saveAndClose() {
		Log.d(TAG, "onClick");
		if (buttonSaveListing != null) {
			coffeeShop.setName(editName.getText().toString());
			coffeeShop.setAddress(editAddress.getText().toString());
			coffeeShop.setCity(editCity.getText().toString());
			coffeeShop.setZip(editZip.getText().toString());
			coffeeShop.setName(editName.getText().toString());
			coffeeShop.setPhone(editPhone.getText().toString());
			coffeeShop.setWebsite(editWebsite.getText().toString());


			TextView tv = (TextView) spinnerState.getSelectedView();
			if (tv != null) {
				coffeeShop.setState(tv.getText().toString());
			}
			Intent i = new Intent();
			i.putExtra(EXTRA_COFFEE_SHOP_OBJECT, coffeeShop);
			setResult(RESULT_OK, i);
			finish();

		}

	}

	// **********************************************************************************************
	// Button listener to Save
	// **********************************************************************************************
	private void loadData() {
		editName.setText(coffeeShop.getName());
		editAddress.setText(coffeeShop.getAddress());
		editCity.setText(coffeeShop.getCity());

		editZip.setText(coffeeShop.getZip());
		editPhone.setText(coffeeShop.getPhone());
		editWebsite.setText(coffeeShop.getWebsite());

		// find spinner value
		int pos = spinnerAdapter.getPosition(coffeeShop.getState());
		spinnerState.setSelection(pos);
	}

}
