package com.example.contextbaseaccesscontrol.events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.contextbaseaccesscontrol.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment{
	private GoogleMap mMap;		
	
	static View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	
	view = inflater.inflate(R.layout.fragment_app, container, false);
	
	
	SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
	mapFragment.getMapAsync(new OnMapReadyCallback() {
		
		@Override
		public void onMapReady(GoogleMap googleMap) {
			Toast.makeText(getActivity(), "Mapp Callback", Toast.LENGTH_SHORT).show();
			Log.d("Callback", "MAP Callback");
			mMap = googleMap;
			double latitude=9.9558501;
			double longitude=76.3242149;
			MarkerOptions marker =new MarkerOptions().position(new LatLng(latitude,longitude)).title("Maps");
			mMap.addMarker(marker);
			CameraPosition cameraPosition =new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			mMap.setMyLocationEnabled(true);
			
		}
	});
	return view;
}
	
//	@Override
//	public void onMapReady(GoogleMap googleMap) {
//		Toast.makeText(getActivity(), "Mapp Callback", Toast.LENGTH_SHORT).show();
//		Log.d("Callback", "MAP Callback");
//		mMap = googleMap;
//		double latitude=9.9558501;
//		double longitude=76.3242149;
//		MarkerOptions marker =new MarkerOptions().position(new LatLng(latitude,longitude)).title("Maps");
//		mMap.addMarker(marker);
//		CameraPosition cameraPosition =new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
//		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//		mMap.setMyLocationEnabled(true);
//		
//		
//	}

}
