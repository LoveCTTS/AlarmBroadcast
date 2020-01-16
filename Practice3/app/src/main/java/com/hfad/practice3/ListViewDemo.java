package com.hfad.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListViewDemo extends ListActivity {

        ArrayList<String> listItems = new ArrayList<String>();

        ArrayAdapter<String> adapter;

        int clickCounter = 0 ;

        @Override
        public void onCreate(Bundle icicle) {
                super.onCreate(icicle);
                setContentView(R.layout.activity_main);
                adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
                setListAdapter(adapter);

        }

        public void addItems(View v) {
                listItems.add("Clicked : "+clickCounter++);
                adapter.notifyDataSetChanged();
        }
}
