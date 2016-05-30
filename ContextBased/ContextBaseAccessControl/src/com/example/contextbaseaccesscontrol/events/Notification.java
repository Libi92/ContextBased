package com.example.contextbaseaccesscontrol.events;

import java.util.Calendar;
import java.util.List;

import com.example.contextbaseaccesscontrol.MainActivity;
import com.example.contextbaseaccesscontrol.tables.Eventschedule;

import android.R;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

public class Notification extends Service{


	@Override
	public IBinder onBind(Intent intent) {
		final List<Eventschedule> schedules=Eventschedule.listAll(Eventschedule.class);
		long frtime=((Eventschedule) schedules).getFromTime();
		long totime=((Eventschedule) schedules).getToTime(); 
		long current_time=Calendar.getInstance().getTime().getTime();
		long b4event=frtime-3600000;
		if(current_time==b4event){
			
			showNotificationb4event("Meeting Scheduled", "From NavApp", "Your Next Meetin at"+frtime, (int)(Math.random() * 1000), getApplicationContext());
			}
		
		return null;
	}
	public void showNotificationb4event(String notiText, String contentTitle, String contentText, int sequence, Context context){
		try {
			NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			NotificationCompat.Builder nBuilder;
	        Uri alarmSound = RingtoneManager
	                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//	        nBuilder = (Builder) new NotificationCompat.Builder(context)
//	                .setSmallIcon(R.drawable.btn_star)
//	                .setContentTitle(notiText)
//	                .setLights(Color.BLUE, 500, 500).setContentText(contentText)
//	                .setAutoCancel(true).setTicker("Remainder from NavApp")
//	                .setVibrate(new long[] { 100, 250, 100, 250, 100, 250 })
//	                .setSound(alarmSound);
//			
//			
//			Intent notificationIntent = new Intent(context,
//					MainActivity.class);
//			PendingIntent contentIntent = PendingIntent.getActivity(
//					context, 0, notificationIntent, 0);
//
//			android.app.Notification notification = nBuilder.build();
////			notification.setLatestEventInfo(NotificationService.this, contentTitle, contentText,
////					contentIntent);
//
//			notificationManager.notify(sequence, notification);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
