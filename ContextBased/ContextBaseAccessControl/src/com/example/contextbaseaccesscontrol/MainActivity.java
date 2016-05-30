package com.example.contextbaseaccesscontrol;

import com.example.contextbaseaccesscontrol.webservice.WebServiceClient;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        
        buttonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
				EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
				
				
				final String userName = editTextUserName.getText().toString();
				final String password = editTextPassword.getText().toString();
				
				
				Thread callThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						String status = WebServiceClient.login(userName, password);
						
						if(status.equals("success")) {
							Globals.loggedInUser = userName;
						} else {
							Globals.loggedInUser = "[failed]";
						}
					}
				});
				
				callThread.start();

				while(Globals.loggedInUser==null)
					;
				
				if(!Globals.loggedInUser.equals("[failed]")) {
					Toast.makeText(getApplicationContext(), "Login Successfull!", Toast.LENGTH_LONG).show();		
					startActivity(new Intent(getApplicationContext(), MenuActivity.class));
				} else {
					Toast.makeText(getApplicationContext(), "Login Unsuccessfull!", Toast.LENGTH_LONG).show();		
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()) {
		case R.id.action_settings:
		{
			final Dialog dialog = new Dialog(this);

			dialog.setContentView(R.layout.settings_layout);
			
			final EditText editTextIPAddress = (EditText) dialog.findViewById(R.id.editTextIPAddress);
			final Button buttonSetIP = (Button) dialog.findViewById(R.id.buttonSetServerIPAddress);
			
			final SharedPreferences preferences = getSharedPreferences("CONTEXTBASED_ACCESS",Context.MODE_PRIVATE);
			editTextIPAddress.setText(preferences.getString("IPADDRESS", "192.168.1.19"));
			
			buttonSetIP.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final SharedPreferences preferences = getSharedPreferences("CONTEXTBASED_ACCESS",Context.MODE_PRIVATE);
					
					Editor editor = preferences.edit();
					
					editor.putString("IPADDRESS", editTextIPAddress.getText().toString());
					editor.commit();
					
					Globals.URL="http://"+editTextIPAddress.getText().toString()+":8080/ContextBasedAccessControlWeb/AccessControlService?xsd=1";
				
					dialog.dismiss();
					
					Toast.makeText(getApplicationContext(), "Preferences Saved!", Toast.LENGTH_LONG).show();
				}
			});
		
			dialog.show();
			
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
    
}
