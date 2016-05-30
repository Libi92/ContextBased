package com.example.contextbaseaccesscontrol.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.example.contextbaseaccesscontrol.Globals;

public class WebServiceClient {

	public static String connect(String methodName, String[] args,
			String[] values) {

		try {
			SoapObject request = new SoapObject(Globals.NAMESPACE, methodName);
			int i = 0;

			for (String s : args) {
				PropertyInfo propInfo = new PropertyInfo();
				propInfo.name = s;
				propInfo.type = PropertyInfo.STRING_CLASS;
				request.addProperty(propInfo, values[i]);
				i++;
			}

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					Globals.URL);
			androidHttpTransport.call(Globals.NAMESPACE + methodName, envelope);
			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse(); // Receiving return string

			String result = resultsRequestSOAP.toString();
			if (result != null) {
				return result;
			}
			return "-1";
		} catch (Exception ex) {
			Log.d("Web Service Exception", ex.toString());
			return "-1";
		}
	}

	public static String login(String username,String password) {
		
		String[] args = new String[2];
		String[] values = new String[2];
		
		args[0] = "username";
		values[0] = username;
		args[1] = "password";
		values[1] = password;
		

		String result = connect("login", args, values);
		Log.d("login Result", result);
		return result;
	}
	
	public static String getconfig(String username) {
		
		String[] args = new String[1];
		String[] values = new String[1];
		
		args[0] = "username";
		values[0] = username;
		

		String result = connect("getconfig", args, values);
		Log.d("getconfig Result", result);
		return result;
	}
	
	public static String getwifiid(String roomname) {
		String[] args = new String[1];
		String[] values = new String[1];
		
		args[0] = "roomname";
		values[0] = roomname;
		

		String result = connect("getwifiid", args, values);
		Log.d("getwifiid Result", result);
		return result;
	}
	
	public static String getPermissionLocation(String username) {
		
		String[] args = new String[1];
		String[] values = new String[1];
		
		args[0] = "username";
		values[0] = username;
		

		String result = connect("getPermissionLocation", args, values);
		Log.d("getconfig Result", result);
		return result;
	}

}
