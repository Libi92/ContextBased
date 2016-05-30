package com.example.contextbaseaccesscontrol.events;

import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.contextbaseaccesscontrol.R;
import com.example.contextbaseaccesscontrol.tables.Eventschedule;
import com.example.contextbaseaccesscontrol.utils.CustomAdapter;


public class HomeFragment extends Fragment {
    ListView listview;
	static View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		view = inflater.inflate(R.layout.fragment_home, container, false);
		
		listview = (ListView)view.findViewById(R.id.listViewEvents);
		final List<Eventschedule> schedules =Eventschedule.listAll(Eventschedule.class);
		Collections.sort(schedules);
		CustomAdapter adapter = new CustomAdapter(getActivity(), schedules);
		
		listview.setAdapter(adapter);
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
//				getActivity().getIntent().putExtra("eventId", schedules.get(position).getId());
				final Eventschedule eventschedule = schedules.get(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Delete Item");
				builder.setMessage("Confirm Delete:");
				builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						eventschedule.delete();
						dialog.dismiss();
					}
				});
				builder.create();
				builder.show();
				
				return true;
			}
			
		});
		listview.setOnItemClickListener(new OnItemClickListener(){
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				getActivity().getIntent().putExtra("eventId", schedules.get(position).getId());
				switchFragment(new EachEvent());
			}
			        
		});		
		return view;
	}
	
	private void switchFragment(Fragment fragment){
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
		.beginTransaction()
		.replace(R.id.container, fragment).commit();
	}
	
}
