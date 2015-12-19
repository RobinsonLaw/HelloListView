package com.example.hellolistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.navdrawer.SimpleSideDrawer;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//private SimpleSideDrawer mNav;
	public class StableArrayAdapter extends ArrayAdapter<String>{
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		public StableArrayAdapter(Context context,int textViewResourceId,List<String> objects) {
			
			// TODO Auto-generated constructor stub
		     super(context, textViewResourceId, objects);
		      for (int i = 0; i < objects.size(); ++i) {
		        mIdMap.put(objects.get(i), i);
		      }	
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        //mNav = new SimpleSideDrawer(this);
        
		final ListView listview2=(ListView)findViewById(R.id.listview2);
		String[] values2 =new String[]{"Chinese","English"};
		final ArrayList<String> list2= new ArrayList<String>();
		for (int i=0;i<values2.length;i++)
			list2.add(values2[i]);
        
		
		final ListView listview=(ListView)findViewById(R.id.listview);
		String[] values =new String[]{"Chinese","English","Japanese","German","Spanish","Italian","Brazian","Test1","Test2","Test3","Test4"};
		final ArrayList<String> list= new ArrayList<String>();
		for (int i=0;i<values.length;i++)
			list.add(values[i]);
		
		final ArrayAdapter<?> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list2);
		listview2.setAdapter(adapter2);
		setListViewHeightBasedOnChildren(listview2);
		final ArrayAdapter<?> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		listview.setAdapter(adapter);
		setListViewHeightBasedOnChildren(listview);
		listview.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
			    Toast.makeText(getApplicationContext(),
			      "Click ListItem Number " + position, Toast.LENGTH_LONG)
			      .show();
			  }
			}); 		
	/*
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      @Override
		      public void onItemClick(AdapterView<?> parent, final View view,
		          int position, long id) {
		        final String item = (String) parent.getItemAtPosition(position);
		        view.animate().setDuration(2000).alpha(0)
		            .withEndAction(new Runnable() {
		              @Override
		              public void run() {
		                list.remove(item);
		                adapter.notifyDataSetChanged();
		                view.setAlpha(1);
		              }
		            });
		      }
		 });	*/	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
      if (listAdapter == null) {
      // pre-condition
            return;
      }

      int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
      for (int i = 0; i < listAdapter.getCount(); i++) {
           View listItem = listAdapter.getView(i, null, listView);
           if (listItem instanceof ViewGroup) {
              listItem.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
           }
           listItem.measure(0, 0);
           totalHeight += listItem.getMeasuredHeight();
      }

      ViewGroup.LayoutParams params = listView.getLayoutParams();
      params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
                listView.setLayoutParams(params);
  }

}
