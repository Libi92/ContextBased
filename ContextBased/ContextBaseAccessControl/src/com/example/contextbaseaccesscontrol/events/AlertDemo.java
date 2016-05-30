package com.example.contextbaseaccesscontrol.events;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.WindowManager.LayoutParams;

import com.example.contextbaseaccesscontrol.R;

public class AlertDemo extends DialogFragment{
	MediaPlayer m;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		MediaPlayer mplayer = MediaPlayer.create(getActivity(), R.raw.alarm);
		mplayer.start();
		getActivity().getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Alarm");
		
		/** Setting the content for the alert dialog */
		builder.setMessage("An Alarm by AlarmManager");
		builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getActivity().finish();
				
			}
		});
		
		return builder.create();
	}
	@Override
	public void onDestroy() {		
		super.onDestroy();
		getActivity().finish();
	}

}
