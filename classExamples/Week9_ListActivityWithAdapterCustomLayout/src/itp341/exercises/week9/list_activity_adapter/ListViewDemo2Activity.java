package itp341.exercises.week9.list_activity_adapter;

import android.os.Bundle;
import android.app.ListActivity;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;

public class ListViewDemo2Activity extends ListActivity { 
    TextView selectedOpt;
    String[] fruits={"Peaches", "Mango", "Orange", "Grapes", "Banana"};
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo2); 
        selectedOpt=(TextView)findViewById(R.id.selectedopt);
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(
        		this, 
        	    android.R.layout.simple_list_item_multiple_choice,
        	    fruits);
        	getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        setListAdapter(arrayAdpt);           
    }
    
    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        super.onListItemClick(parent, v, position, id);  
        selectedOpt.setText("You have selected "+fruits[position]);
    }
}

//package com.androidunleashed.listviewdemo2;
//
//import android.os.Bundle;
//import android.app.ListActivity;
//import android.widget.TextView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.view.View;
//
//public class ListViewDemo2Activity extends ListActivity { 
//    TextView selectedOpt;
//    String[] fruits={"Apple", "Mango", "Orange", "Grapes", "Banana"};
//  
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_view_demo2); 
//        selectedOpt=(TextView)findViewById(R.id.selectedopt);
//        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this, 
//        	    android.R.layout.simple_list_item_multiple_choice,fruits);
//        	getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        setListAdapter(arrayAdpt);           
//    }
//    
//    @Override
//    public void onListItemClick(ListView parent, View v, int position, long id) {
//        super.onListItemClick(parent, v, position, id);  
//        selectedOpt.setText("You have selected "+fruits[position]);
//    }
//}
