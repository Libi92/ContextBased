package com.example.contextbaseaccesscontrol.tables;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orm.SugarRecord;

public class Eventschedule extends SugarRecord<Eventschedule> implements Comparable {
	String date;
	long fromtime;
	long totime;
	double lt;
	double ln;
	String place;
	String title;

	public Eventschedule() {

	}

	public Eventschedule(String date, long fromtime2, long totime2,double lt,double ln,String place,String title) {
		this.date = date;
		this.fromtime = fromtime2;
		this.totime = totime2;
		this.lt = lt;
		this.ln = ln;
		this.place = place;
		this.title=title;
	}

	public String getDate() {
		return date;
	}
	public String getTitle() {
		return title;
	}
	public String getPlace() {
		return place;
	}

	public long getFromTime() {
		return fromtime;
	}

	public long getToTime() {
		return totime;
	}

	public double  getLongitude() {
		return ln;
	}

	public double getLatitude() {
		return lt;
	}

	@Override
	public int compareTo(Object another) {
		Eventschedule event2 = (Eventschedule)another;
		try {
			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(this.date);
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(event2.date);
			
			return (date1.after(date2)) ? (date1.equals(date2)) ? 0 : -1 : 1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
