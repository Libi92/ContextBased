package com.example.contextbaseaccesscontrol.events;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.contextbaseaccesscontrol.R;
import com.example.contextbaseaccesscontrol.tables.Eventschedule;
import com.example.contextbaseaccesscontrol.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Add extends Fragment implements OnMapReadyCallback {
	static View view;
	EditText editTextFrom;
	EditText editTextTo;
	private GoogleMap mMap;
	EditText editTextPlaceName;
	EditText editTextTitle;
	
	Calendar fromDate;
	Calendar toDate;
	public static int hr;
	public static int min;
	public static int yr;
	public static int mnth;
	public static int day;
	double mapLat;
	double mapLng;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_new, container, false);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		
		fromDate = Calendar.getInstance();
		toDate = Calendar.getInstance();
		
		editTextPlaceName = (EditText)view.findViewById(R.id.editTextPlaceName);
		editTextTitle =(EditText)view.findViewById(R.id.etxt_title);
		final EditText editDate = (EditText) view.findViewById(R.id.etxt_date);
		editTextFrom = (EditText) view.findViewById(R.id.etxt_fromtime);
		editTextTo = (EditText) view.findViewById(R.id.etxt_totime);
		editDate.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					final Calendar calendar = Calendar.getInstance();

					/*Log.d("year", calendar.get(Calendar.YEAR) + "");
					Log.d("month", calendar.get(Calendar.MONTH) + "");
					Log.d("date", calendar.get(Calendar.DATE) + "");*/

					DatePickerDialog datePickerDialog = new DatePickerDialog(
							getActivity(), new OnDateSetListener() {

								boolean dateSet = false;

								@Override
								public void onDateSet(DatePicker view,
										final int year, final int monthOfYear,
										final int dayOfMonth) {

											fromDate.set(Calendar.YEAR, year);
											fromDate.set(Calendar.MONTH, monthOfYear);
											fromDate.set(Calendar.DATE, dayOfMonth);
											
											yr=year;
											mnth=monthOfYear;
											day=dayOfMonth;
											
											toDate.set(Calendar.YEAR, year);
											toDate.set(Calendar.MONTH, monthOfYear);
											toDate.set(Calendar.DATE, dayOfMonth);
											

														editDate
																.setText(dayOfMonth
																		+ "-"
																		+ (monthOfYear + 1)
																		+ "-"
																		+ year
																		+ " ");
																	

												

										
									
								}
							}, calendar.get(Calendar.YEAR), calendar
									.get(Calendar.MONTH), calendar
									.get(Calendar.DATE));

					datePickerDialog.show();
				}
				return true;
			}
			
		});
		editTextFrom.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					final Calendar calendar = Calendar.getInstance();
					TimePickerDialog timePickerDialog = new TimePickerDialog(
							getActivity(),
							new TimePickerDialog.OnTimeSetListener() {

								@Override
								public void onTimeSet(
										TimePicker view,
										int hourOfDay,
										int minute) {
									
									
									int AM_PM = Calendar.AM;
									if(hourOfDay > 12){
										hourOfDay = hourOfDay - 12;
										AM_PM = Calendar.PM;
									}
									
									fromDate.set(Calendar.HOUR, hourOfDay);
									fromDate.set(Calendar.MINUTE, minute);
									fromDate.set(Calendar.AM_PM, AM_PM);
									
									hr=hourOfDay;
									min=minute;
									
									editTextFrom
											.setText(
													
													+ hourOfDay
													+ " : "
													+ minute);

								}
							}, calendar.get(Calendar.HOUR),
							calendar.get(Calendar.MINUTE),
							false);

					timePickerDialog.show();
					
				}
				// TODO Auto-generated method stub
				return true;
			}});
		

		editTextTo.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_UP) {
					final Calendar calendar = Calendar.getInstance();
					
					TimePickerDialog timePickerDialog = new TimePickerDialog(
							getActivity(),
							new TimePickerDialog.OnTimeSetListener() {

								@Override
								public void onTimeSet(
										TimePicker view,
										int hourOfDay,
										int minute) {
									
									int AM_PM = Calendar.AM;
									if(hourOfDay > 12){
										hourOfDay = hourOfDay - 12;
										AM_PM = Calendar.PM;
									}
									
									toDate.set(Calendar.HOUR, hourOfDay);
									toDate.set(Calendar.MINUTE, minute);
									toDate.set(Calendar.AM_PM, AM_PM);
									
									editTextTo
											.setText(
													
													+ hourOfDay
													+ " : "
													+ minute);

								}
							}, calendar.get(Calendar.HOUR),
							calendar.get(Calendar.MINUTE),
							false);

					timePickerDialog.show();
					
				}
				return true;
			}
			
		});

		
		((Button)view.findViewById(R.id.buttonaddnew)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String date= editDate.getText().toString();
				
				String place = editTextPlaceName.getText().toString();
				String title=editTextTitle.getText().toString();
				
				Intent i = new Intent("in.wptrafficanalyzer.servicealarmdemo.demoactivity");
				PendingIntent operation = PendingIntent.getActivity(getActivity(), 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
				Eventschedule schedule=new Eventschedule(date,fromDate.getTime().getTime(),toDate.getTime().getTime(),mapLat,mapLng,place,title);
				schedule.save();
				
				long alarm_time = fromDate.getTimeInMillis();
				AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP  , alarm_time , operation);
			}
		});
		
		((Button)view.findViewById(R.id.buttonSearch)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String place = editTextPlaceName.getText().toString();
				new GetSearchLocation(place).execute();
			}
		});
		
		
			
		return view;
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
mMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng latLng) {
				
				mapLat = latLng.latitude;
				mapLng = latLng.longitude;
				Toast.makeText(getActivity(),mapLat+"."+mapLng,Toast.LENGTH_LONG ).show();
				  // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();
 
                // Setting the position for the marker
                markerOptions.position(latLng);
 
                // Setting the title for the marker.
                // This will be displayed on taping the marker
                String placename=editTextPlaceName.getText().toString();
                markerOptions.title(placename);
 
                // Clears the previously touched position
                mMap.clear();
 
                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
 
                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
			}
		});
		
		GPSTracker gpsTracker = new GPSTracker(getActivity());
		
		double latitude = gpsTracker.getLatitude();
		double longitude = gpsTracker.getLongitude();
		CameraPosition cameraPosition =new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		mMap.setMyLocationEnabled(true);
		
	}
	
	class GetSearchLocation extends AsyncTask<Void, Void, Void>{
		String place;
		double latitude;
		double longitude;
		
		public GetSearchLocation(String place) {
			this.place = place;		}
		@Override
		protected Void doInBackground(Void... params) {
			Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
			List<Address> addressList;
			try {
				addressList = geocoder.getFromLocationName(place, 1);
				if (addressList != null && addressList.size() > 0) {
	                Address address = addressList.get(0);
	                latitude = address.getLatitude();
	                longitude = address.getLongitude();
	            }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			CameraPosition cameraPosition =new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			super.onPostExecute(result);
		}
	}
	

}
