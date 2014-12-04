package edu.usc.itp341.understanding_listeners;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import edu.usc.itp341.test1.R;

/* http://www.androiddesignpatterns.com/2013/01/inner-class-handler-memory-leak.html
 * 
 * In java, only an inner class can be made static
 * Static inner class can be instantiated WITHOUT creating an instance
 * of the outer class
 * 
 * The weakreference is needed because the static class no longer has 
 * an implicit reference to the class so a reference is needed.
 * WeakReference is simply a special reference that guarantees that if the reference object
 * gets GC'd, then the object holding the reference won't cause a memory leak
 */



// implement listener in this class and create onClick method
public class StaticInnerClass extends Activity {

	// Create private member to refer to button
	private Button mBtn1;
	// create private member to point to listener object
	private ButtonListener mButtonListener;
	// create variable for example below to demonstrate that inner class
	// can access private outer class data
	private int mNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// instantiate listener object
		mButtonListener = new ButtonListener(this);

		// Assign private member to View widget
		// findViewById returns View object so cast to Button (child)
		mBtn1 = (Button) findViewById(R.id.btn1);

		// set up listener object as listener for button clicks
		mBtn1.setOnClickListener(mButtonListener);

		mNumber = 3;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Create static inner class that acts as listener
	// the class is private because it shouldn't be access from the outside
	// world
	// This class is static to ensure no memory leaks
	private static class ButtonListener implements OnClickListener {
		private final WeakReference<StaticInnerClass> mActivity;

		public ButtonListener(StaticInnerClass activity) {
			mActivity = new WeakReference<StaticInnerClass>(activity);
		}

		// onClick for button -- popup Toast
		@Override
		public void onClick(View v) {
			StaticInnerClass activity = mActivity.get();
			if (mActivity != null) {
				Toast.makeText(activity,
					"Static Inner Class!\nPrivate outer class int = " + activity.mNumber,
					Toast.LENGTH_SHORT).show();

			}
			
		}
	}

}
