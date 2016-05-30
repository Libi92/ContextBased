package com.example.contextbaseaccesscontrol.utils;

import java.util.List;

import com.example.contextbaseaccesscontrol.R;
import com.example.contextbaseaccesscontrol.tables.Eventschedule;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter
{
	 /*********** Declare Used Variables *********/
    private Activity activity;
    private List data;
    private static LayoutInflater inflater=null;
    Eventschedule tempValues=null;
    int i=0;
    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Activity a, List d) {
         
           /********** Take passed values **********/
            activity = a;
            data=d;
            
         
            /***********  Layout inflator to call external xml layout () ***********/
             inflater = ( LayoutInflater )activity.
                                         getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
    }         /******** What is the size of Passed Arraylist Size ************/
	@Override
	public int getCount() {
         return data.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	 /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
         
    	public TextView textViewTitle;
        public TextView textViewDate;
        public TextView textViewTime;
        public TextView textViewPlace;
        
 
    }
    
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View vi = convertView;
         ViewHolder holder;
         if(convertView==null){
             
             /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
             vi = inflater.inflate(R.layout.layout_event, null);
              
             /****** View Holder Object to contain tabitem.xml file elements ******/

             holder = new ViewHolder();
             holder.textViewDate = (TextView) vi.findViewById(R.id.textViewEventDate);
             holder.textViewPlace=(TextView)vi.findViewById(R.id.textViewEventPlace);
             holder.textViewTime=(TextView)vi.findViewById(R.id.textViewEventTime);
             holder.textViewTitle=(TextView)vi.findViewById(R.id.textViewEventTitle);
            
              
            /************  Set holder with LayoutInflater ************/
             vi.setTag( holder );
         }
         else 
             holder=(ViewHolder)vi.getTag();
          
         
         
             /***** Get each Model object from Arraylist ********/
             tempValues=null;
             tempValues = ( Eventschedule ) data.get( position );
              
             /************  Set Model values in Holder elements ***********/

             new DateFormat();
			String eventTime = (String) DateFormat.format("HH:mm", tempValues.getFromTime());
             
             holder.textViewDate.setText( tempValues.getDate() );
             holder.textViewPlace.setText( tempValues.getPlace() );
             holder.textViewTime.setText( eventTime );
             holder.textViewTitle.setText( tempValues.getTitle() );
              
               
                            
         
		return vi;
	}
}
