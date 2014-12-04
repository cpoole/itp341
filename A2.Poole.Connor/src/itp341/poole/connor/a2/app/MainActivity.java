package itp341.poole.connor.a2.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button coffeeBtn;
	private Button teaBtn;
	
	public static final String TEA_COUNT = "itp341.poole.connor.a2.teaCount";
	private int teaCount;
	public static final String COFFEE_COUNT = "itp341.poole.connor.a2.coffeeCount";
	private int coffeeCount;
	
	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.activity_main);
		
		if(state !=null){
			teaCount = state.getInt(TEA_COUNT);
			coffeeCount = state.getInt(COFFEE_COUNT);
		}else{
			teaCount = 0;
			coffeeCount = 0;
		}
		
		coffeeBtn = (Button) findViewById(R.id.coffeeBtn);
		coffeeBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				coffeeCount ++;
				Toast.makeText(getApplicationContext(),
						getString(R.string.coffeeNotification) + coffeeCount + getString(R.string.timeClose), 
						Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
		teaBtn = (Button) findViewById(R.id.teaBtn);
		teaBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				teaCount ++;
				Toast.makeText(getApplicationContext(),
						getString(R.string.teaNotification) + teaCount + getString(R.string.timeClose), 
						Toast.LENGTH_SHORT).show();
				
				
			}
			
		});
		
	}
	@Override
	public void onSaveInstanceState(Bundle state){
		super.onSaveInstanceState(state);
		state.putInt(TEA_COUNT, teaCount);
		state.putInt(COFFEE_COUNT,coffeeCount);
		
	}
}
