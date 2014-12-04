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
public class ThisListener extends Activity implements OnClickListener{
	
	//Create private member to refer to button
	private Button mBtn1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Assign private member to View widget
		//findViewById returns View object so cast to Button (child)
		mBtn1 = (Button) findViewById(R.id.btn1);
		
		//set up this class as listener for button clicks
		mBtn1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//onClick for button -- popup Toast
	//implmented as an instance method
	@Override
	public void onClick(View v) {
		Toast.makeText(this, 
				"Implement Interface", 
				Toast.LENGTH_SHORT).show();
		
	}

}
