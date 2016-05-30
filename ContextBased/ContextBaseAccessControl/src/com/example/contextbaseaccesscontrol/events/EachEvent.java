package com.example.contextbaseaccesscontrol.events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contextbaseaccesscontrol.R;
import com.example.contextbaseaccesscontrol.tables.Eventschedule;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EachEvent extends Fragment implements OnMapReadyCallback{
	static View view;
	private GoogleMap mMap;
	public long eventId;
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.each_event, container, false);
		
		TextView date = (TextView) view.findViewById(R.id.textViewDate);
		TextView title = (TextView) view.findViewById(R.id.textViewTitle);
		TextView place = (TextView) view.findViewById(R.id.textViewPlace);
		TextView fromtime = (TextView) view.findViewById(R.id.textViewFromTime);
		TextView totime = (TextView) view.findViewById(R.id.textViewToTime);
		TextView devicetime = (TextView) view.findViewById(R.id.textViewToTime);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
	    eventId = getActivity().getIntent().getLongExtra("eventId", 0l);
		
		Eventschedule schedule = Eventschedule.findById(Eventschedule.class, eventId);
		String eventdate=schedule.getDate();
		new DateFormat();
		String eventfromtime = (String) DateFormat.format("HH:mm", schedule.getFromTime());
		new DateFormat();
		String eventtotime = (String) DateFormat.format("HH:mm", schedule.getToTime());
		String eventplace=schedule.getPlace();
		String eventtitle=schedule.getTitle();
		date.setText(eventdate);
		place.setText(eventplace);
		fromtime.setText(eventfromtime);
		totime.setText(eventtotime);
		title.setText(eventtitle);
		return view;
	}
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		double latitude=9.9558501;
		double longitude=76.3242149;
		MarkerOptions marker =new MarkerOptions().position(new LatLng(latitude,longitude)).title("Maps");
		mMap.addMarker(marker);
		CameraPosition cameraPosition =new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		mMap.setMyLocationEnabled(true);
		
	}

}
