package edu.usc.itp341.understanding_listeners;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import edu.usc.itp341.test1.R;

// implement listener in this class and create onClick method
public class AnonymousClass extends Activity {

	// Create private member to refer to button
	private Button mBtn1;
	// create variable for example below to demonstrate that inner class
	// can access private outer class data
	private int mNumber;
	// 
	//create private member to point to listener object
	private OnClickListener mButtonListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		// Assign private member to View widget
		// findViewById returns View object so cast to Button (child)
		mBtn1 = (Button) findViewById(R.id.btn1);

		mButtonListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), 
						"Anonymous Class!\nPrivate outer class int = " + mNumber, 
						Toast.LENGTH_SHORT).show();
				
			}
		};
		mBtn1.setOnClickListener(mButtonListener);
			
		mNumber = 3;
	}

	

}
