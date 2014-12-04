package itp341.exercises.week10_json.app;

import itp341.exercises.week10_json.app.model.CoffeeShop;
import itp341.exercises.week10_json.app.model.CoffeeShopDataStore;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewAllCoffeeShops extends ListActivity {

	private static final String TAG = ViewAllCoffeeShops.class.getSimpleName();

	private ArrayList<CoffeeShop> coffeeShops;
	private ArrayAdapter<CoffeeShop> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		// load dummy values
		// loadCoffeeShop();

		coffeeShops = CoffeeShopDataStore
				.loadCoffeeShops(getApplicationContext());
		adapter = new ArrayAdapter<CoffeeShop>(this,
				android.R.layout.simple_list_item_1, coffeeShops);

		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		Log.d(TAG, "onListItemClick");
		Toast.makeText(getApplicationContext(),
				"Id: " + id + ", position: " + position, Toast.LENGTH_SHORT)
				.show();

		Intent i = new Intent(getApplicationContext(),
				AddEditCoffeeShopListing.class);
		i.putExtra(AddEditCoffeeShopListing.EXTRA_COFFEE_SHOP_OBJECT,
				coffeeShops.get(position));
		startActivityForResult(i, position);

	}


	protected void onPause() {
		// Called after onStart() as Activity comes to foreground.
		Log.d(TAG, "onResume");
		super.onPause();
		CoffeeShopDataStore.saveCoffeeShops(coffeeShops,
				getApplicationContext());

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		coffeeShops
				.set(requestCode,
						(CoffeeShop) data
								.getSerializableExtra(AddEditCoffeeShopListing.EXTRA_COFFEE_SHOP_OBJECT));
		adapter.notifyDataSetChanged();
	}

	// use this to get started (before JSON)
	private void loadCoffeeShop() {
		coffeeShops = new ArrayList<CoffeeShop>();
		for (int i = 0; i < 5; i++) {
			CoffeeShop cs = new CoffeeShop("Trojan Grounds", "123 Main St",
					"Los Angeles", "CA", "90089", "2137404542", "usc.edu", 5);
			coffeeShops.add(cs);
		}
	}

}
