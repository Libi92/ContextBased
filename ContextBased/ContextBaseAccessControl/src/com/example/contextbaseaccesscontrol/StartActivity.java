package com.example.contextbaseaccesscontrol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.contextbaseaccesscontrol.webservice.WebServiceClient;

public class StartActivity extends Activity {

	private DevicePolicyManager devicePolicyManager;
	private ComponentName demoDeviceAdmin;
	
	private final int ACTIVATION_REQUEST=0;

	private void activateDevicePolicyAdmin() {
		Intent intent = new Intent(
				DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
				demoDeviceAdmin);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
				"Organization Policy ");
		startActivityForResult(intent, ACTIVATION_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ACTIVATION_REQUEST:
			if (resultCode == Activity.RESULT_OK) {
				Log.i("admin", "Administration enabled!");
			} else {
				Log.i("admin", "Administration enable FAILED!");
			}
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		Button buttonStart = (Button) findViewById(R.id.buttonStart);
		
		buttonStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
						
						demoDeviceAdmin = new ComponentName(StartActivity.this, AndroidDeviceAdminReceiver.class);
						
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
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
