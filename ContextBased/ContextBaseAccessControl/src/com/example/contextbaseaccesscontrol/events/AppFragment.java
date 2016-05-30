package com.example.contextbaseaccesscontrol.events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contextbaseaccesscontrol.R;

public class AppFragment extends Fragment{
	static View view;
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_app, container, false);
		return view;
	}
	

}
