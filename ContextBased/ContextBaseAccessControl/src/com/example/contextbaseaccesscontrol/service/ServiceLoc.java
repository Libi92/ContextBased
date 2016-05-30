package com.example.contextbaseaccesscontrol.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.contextbaseaccesscontrol.AndroidDeviceAdminReceiver;
import com.example.contextbaseaccesscontrol.Globals;
import com.example.contextbaseaccesscontrol.webservice.WebServiceClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ServiceLoc extends Service implements LocationListener {

	Location locaton;

	int locationNo = 0;;
	int locationCount = 0;
	String tits, snips;
	LatLng position;
	String time1;
	String lat1 = "", lng1 = "";
	
	private DevicePolicyManager devicePolicyManager;
	private ComponentName demoDeviceAdmin;

	MarkerOptions markerOptions;

	SharedPreferences sp;
	int noti_count = 0;
	StringBuilder strAddress;

	public void getMyLocationAddress(double lat, double lng) {

		Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

		// Place your latitude and longitude
		List<Address> addresses;
		try {
			addresses = geocoder.getFromLocation(lat, lng, 1);
		

		if (addresses != null) {

			Address fetchedAddress = addresses.get(0);
			strAddress = new StringBuilder();

			for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
				strAddress.append(fetchedAddress.getAddressLine(i));
			}

		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onLocationChanged(Location location) {

		Log.d("inService", "Catch");

		

		// Getting latitude of the current location
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();

		String latitude1 = Double.toString(latitude);
		String longitude1 = Double.toString(longitude);
		lat1 = latitude1;
		lng1 = longitude1;
		LatLng latLng = new LatLng(latitude, longitude);

		try {

			getMyLocationAddress(latitude, longitude);
			
			String locationLatLng = WebServiceClient.getconfig(Globals.loggedInUser);
			
			Location location2 = new Location("");
			location2.setLatitude(Double.parseDouble(locationLatLng.split(",")[0]));
			location2.setLongitude(Double.parseDouble(locationLatLng.split(",")[1]));
			
			if(location.distanceTo(location2) < 20){
				startAdminControl();
				
				new Handler().post(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
						
					}
				});
				
			}
		} catch (Exception e) {
			Log.v("Inside Location", e.toString());
		}

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Toast.makeText(getApplicationContext(), "Loc Service Started", Toast.LENGTH_SHORT).show();
		
		Log.d("Inside onStart", "onstart");

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitAll().build());

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		if (location != null) {

			onLocationChanged(location);
		}
		//
		 locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, this);
		//
		//
		// onLocationChanged(this.locaton);

		return super.onStartCommand(intent, flags, startId);
	}
	
	private void activateDevicePolicyAdmin() {
		Intent intent = new Intent(
				DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
				demoDeviceAdmin);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
				"Organization Policy ");
		startActivity(intent);
	}

	
	public void startAdminControl(){
		
		Thread listeningThread = new Thread(new Runnable() {
			
			private AudioManager audioManager;
			private BluetoothAdapter bluetoothAdapter;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				
				BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
				
				demoDeviceAdmin = new ComponentName(ServiceLoc.this, AndroidDeviceAdminReceiver.class);
				
				activateDevicePolicyAdmin();
				
				this.bluetoothAdapter = bluetoothAdapter;
				this.audioManager = audioManager;
			
				
				while(true) {
					
					final String connectedWifiId = wifiManager.getConnectionInfo().getSSID();
					
					String config = WebServiceClient.getconfig(Globals.loggedInUser);
					
					String[] permissions = config.split("<>");
					
					for(String permission : permissions) {
						if(permission.equals(""))
							continue;
						
						String perm = permission.substring(1,permission.length()-1);
						
						Log.d("permissions", "Permission : "+perm);
						
						String[] perm1 = perm.split(",");
						
					    String roomName = perm1[2].trim();
					    String time = perm1[perm1.length-1].trim();
					    
					    String microphone = perm1[3].trim();
					    String camera = perm1[4].trim();
					    String bluetooth = perm1[5].trim();

					    
					    String roomWifiId = WebServiceClient.getwifiid(roomName);
					
					    if(roomWifiId.equals(connectedWifiId)) {
					       Log.d("room WifiId Same", roomWifiId);
					       
					       int hours = new Date().getHours();
					       int minutes = new Date().getMinutes();
					       
					       String h1="";
					       String m1 = "";
					       
					       if((""+hours).length()==1)
					    	   h1 = "0"+hours;
					       else
					    	   h1 = "" + hours;
					       
					       if((""+minutes).length()==1)
					    	   m1 = "0"+minutes;
					       else
					    	   m1 = "" + minutes;
					       
					       
					       Log.d("hour min", h1+" , "+m1);
					       
					       String start=time.split("-")[0];
					       String end=time.split("-")[1];
					       
					       Log.d("start time", start);
					       Log.d("end time", end);
					       
					       try {
							if(isTimeBetweenTwoTime(start+":00", end+":00",h1+":"+m1+":00")) {
								   		Log.d("inside room", "Inside Hours : "+hours+","+minutes);
									   
									   if(microphone.equals("general")) {
										   Log.d("inside room", "Microphone Enabled");
										   this.microphone(true);
									   } else if(microphone.equals("silent")) {
										   Log.d("inside room", "Microphone Disabled");
										   this.microphone(false);
									   }
									   
									   if(camera.equals("enabled")) {
										   Log.d("inside room", "Camera Enabled");
										   this.camera(true);
									   } else if(camera.equals("disabled")) {
										   Log.d("inside room", "Camera Disabled");
										   this.camera(false);
									   }

									   if(bluetooth.equals("enabled")) {
										   Log.d("inside room", "Bluetooth Enabled");
										   this.bluetooth(true);
									   } else if(bluetooth.equals("disabled")) {
										   Log.d("inside room", "Bluetooth Disabled");
										   this.bluetooth(false);
									   }
								   
							   }
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					         
					    }
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
			
			public boolean isTimeBetweenTwoTime(String argStartTime,
		            String argEndTime, String argCurrentTime) throws ParseException {
		        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
		        //
		        Log.d("reg", argStartTime+","+argEndTime+","+argCurrentTime);
		        
		        if (argStartTime.matches(reg) && argEndTime.matches(reg)
		                && argCurrentTime.matches(reg)) {
		            boolean valid = false;
		            // Start Time
		            java.util.Date startTime = new SimpleDateFormat("HH:mm:ss")
		                    .parse(argStartTime);
		            Calendar startCalendar = Calendar.getInstance();
		            startCalendar.setTime(startTime);

		            // Current Time
		            java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss")
		                    .parse(argCurrentTime);
		            Calendar currentCalendar = Calendar.getInstance();
		            currentCalendar.setTime(currentTime);

		            // End Time
		            java.util.Date endTime = new SimpleDateFormat("HH:mm:ss")
		                    .parse(argEndTime);
		            Calendar endCalendar = Calendar.getInstance();
		            endCalendar.setTime(endTime);

		            //
		            if (currentTime.compareTo(endTime) < 0) {

		                currentCalendar.add(Calendar.DATE, 1);
		                currentTime = currentCalendar.getTime();

		            }

		            if (startTime.compareTo(endTime) < 0) {

		                startCalendar.add(Calendar.DATE, 1);
		                startTime = startCalendar.getTime();

		            }
		            //
		            if (currentTime.before(startTime)) {

		                System.out.println(" Time is Lesser ");

		                valid = false;
		            } else {

		                if (currentTime.after(endTime)) {
		                    endCalendar.add(Calendar.DATE, 1);
		                    endTime = endCalendar.getTime();

		                }

		                System.out.println("Comparing , Start Time /n " + startTime);
		                System.out.println("Comparing , End Time /n " + endTime);
		                System.out
		                        .println("Comparing , Current Time /n " + currentTime);

		                if (currentTime.before(endTime)) {
		                    System.out.println("RESULT, Time lies b/w");
		                    valid = true;
		                } else {
		                    valid = false;
		                    System.out.println("RESULT, Time does not lies b/w");
		                }

		            }
		            return valid;

		        } else {
		            throw new IllegalArgumentException(
		                    "Not a valid time, expecting HH:MM:SS format");
		        }

		    }
			
			public void microphone(boolean enable) {
			    
				if(enable) {
					this.audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				} else {
					this.audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}
				
			}
			
			public void camera(boolean enable) {

				devicePolicyManager.setCameraDisabled(demoDeviceAdmin,!enable);
				
			}
			
			public void bluetooth(boolean enable) {
				if(enable) {
					this.bluetoothAdapter.enable();
				} else
					this.bluetoothAdapter.disable();
			}
			
			
		});
		
		listeningThread.start();
	}
}
