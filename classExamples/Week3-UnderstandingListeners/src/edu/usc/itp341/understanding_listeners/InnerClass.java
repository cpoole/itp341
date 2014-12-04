package edu.usc.itp341.understanding_listeners;

import edu.usc.itp341.test1.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

// implement listener in this class and create onClick method
public class InnerClass extends Activity {

	// Create private member to refer to button
	private Button mBtn1;
	//create private member to point to listener object
	private ButtonListener mButtonListener;
	//create variable for example below to demonstrate that inner class
	//can access private outer class data
	private int mNumber;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//instantiate listener object
		mButtonListener = new ButtonListener();

		// Assign private member to View widget
		// findViewById returns View object so cast to Button (child)
		mBtn1 = (Button) findViewById(R.id.btn1);

		// set up listener object as listener for button clicks
		mBtn1.setOnClickListener(mButtonListener);
		
		mNumber = 3;
	}


	// Create inner class that acts as listener
	// the class is private because it shouldn't be access from the outside world
	// another advantage is that this class could access private data of the outer class
	private class ButtonListener implements OnClickListener {
		// onClick for button -- popup Toast
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), 
					"Inner Class!\nPrivate outer class int = " + mNumber, 
					Toast.LENGTH_SHORT).show();

		}
	}

}
