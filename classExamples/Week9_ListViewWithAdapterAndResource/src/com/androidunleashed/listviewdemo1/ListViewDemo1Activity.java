package com.androidunleashed.listviewdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;

public class ListViewDemo1Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo1);
        
        final String[] fruits=getResources().getStringArray(R.array.fruits);
        final TextView selectedOpt=(TextView)findViewById(R.id.selectedopt);
        ListView fruitsList = (ListView)findViewById(R.id.fruits_list);
        
        ArrayAdapter<String> arrayAdpt= new ArrayAdapter<String>(this, 
            android.R.layout.simple_list_item_1, fruits);
        
        fruitsList.setAdapter(arrayAdpt);   

        fruitsList.setOnItemClickListener(new OnItemClickListener(){
        
        	@Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                selectedOpt.setText("You have selected "+fruits[position]);
            }
        });
    }
}
//public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_list_view_demo1);
//    final String[] fruits={"Apple", "Mango", "Orange", "Grapes", "Banana"};
//    final TextView selectedOpt=(TextView)findViewById(R.id.selectedopt);
//    ListView fruitsList = (ListView)findViewById(R.id.fruits_list);
//    final ArrayAdapter<String> arrayAdpt= new ArrayAdapter<String>(this, 
//        android.R.layout.simple_list_item_1, fruits);
//    fruitsList.setAdapter(arrayAdpt);   
//    fruitsList.setOnItemClickListener(new OnItemClickListener(){
//        @Override
//        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
//        {
//            selectedOpt.setText("You have selected "+fruits[position]);
//        }
//    });
//}
