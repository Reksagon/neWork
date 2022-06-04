package com.my.neworkt;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class Settings221020Activity extends  Activity { 
	
	
	private LinearLayout linear1;
	private LinearLayout linear11;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear15;
	private LinearLayout linear14;
	private LinearLayout linear12;
	private LinearLayout linear8;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private Button save;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private TextView textview12;
	private EditText name_of_device;
	private TextView textview2;
	private EditText ipad;
	private TextView textview3;
	private EditText mult;
	private TextView textview4;
	private EditText divi;
	private TextView textview16;
	private EditText min_res;
	private TextView textview15;
	private EditText max_res;
	private TextView textview13;
	private EditText res_idle;
	private TextView textview5;
	private EditText res_ref;
	private Button res;
	private Button neu;
	private Button start;
	private Button stop;
	private TextView textview17;
	private EditText t_sd_start;
	private TextView textview18;
	private EditText t_sd_end;
	private TextView textview19;
	private EditText t_sd_weight;
	
	private Intent int0 = new Intent();
	private SharedPreferences sharedpref;
	private RequestNetwork netreq;
	private RequestNetwork.RequestListener _netreq_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings221020);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		save = (Button) findViewById(R.id.save);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		textview12 = (TextView) findViewById(R.id.textview12);
		name_of_device = (EditText) findViewById(R.id.name_of_device);
		textview2 = (TextView) findViewById(R.id.textview2);
		ipad = (EditText) findViewById(R.id.ipad);
		textview3 = (TextView) findViewById(R.id.textview3);
		mult = (EditText) findViewById(R.id.mult);
		textview4 = (TextView) findViewById(R.id.textview4);
		divi = (EditText) findViewById(R.id.divi);
		textview16 = (TextView) findViewById(R.id.textview16);
		min_res = (EditText) findViewById(R.id.min_res);
		textview15 = (TextView) findViewById(R.id.textview15);
		max_res = (EditText) findViewById(R.id.max_res);
		textview13 = (TextView) findViewById(R.id.textview13);
		res_idle = (EditText) findViewById(R.id.res_idle);
		textview5 = (TextView) findViewById(R.id.textview5);
		res_ref = (EditText) findViewById(R.id.res_ref);
		res = (Button) findViewById(R.id.res);
		neu = (Button) findViewById(R.id.neu);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		textview17 = (TextView) findViewById(R.id.textview17);
		t_sd_start = (EditText) findViewById(R.id.t_sd_start);
		textview18 = (TextView) findViewById(R.id.textview18);
		t_sd_end = (EditText) findViewById(R.id.t_sd_end);
		textview19 = (TextView) findViewById(R.id.textview19);
		t_sd_weight = (EditText) findViewById(R.id.t_sd_weight);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		netreq = new RequestNetwork(this);
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sharedpref.edit().putString("ip", ipad.getText().toString()).commit();
				sharedpref.edit().putString("res_mul", mult.getText().toString()).commit();
				sharedpref.edit().putString("res_div", divi.getText().toString()).commit();
				sharedpref.edit().putString("device_name", name_of_device.getText().toString()).commit();
				sharedpref.edit().putString("weight_off", res_idle.getText().toString()).commit();
				sharedpref.edit().putString("setting_min_resist", min_res.getText().toString()).commit();
				sharedpref.edit().putString("setting_max_resist", max_res.getText().toString()).commit();
				sharedpref.edit().putString("weight_sd", t_sd_weight.getText().toString()).commit();
				sharedpref.edit().putString("start_sd", t_sd_start.getText().toString()).commit();
				sharedpref.edit().putString("end_sd", t_sd_end.getText().toString()).commit();
				int0.setClass(getApplicationContext(), ChoosetypeActivity.class);
				startActivity(int0);
				finish();
			}
		});
		
		res.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=")).concat(String.valueOf((long)((Double.parseDouble(res_ref.getText().toString()) * Double.parseDouble(mult.getText().toString())) / Double.parseDouble(divi.getText().toString())))), "is_ref", _netreq_request_listener);
			}
		});
		
		neu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=off")), "is_neut", _netreq_request_listener);
			}
		});
		
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/calcmd.php?bound=start")), "is_start", _netreq_request_listener);
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/calcmd.php?bound=stop")), "is_stop", _netreq_request_listener);
			}
		});
		
		_netreq_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		name_of_device.setText(sharedpref.getString("device_name", ""));
		ipad.setText(sharedpref.getString("ip", ""));
		mult.setText(sharedpref.getString("res_mul", ""));
		divi.setText(sharedpref.getString("res_div", ""));
		res_idle.setText(sharedpref.getString("weight_off", ""));
		min_res.setText(sharedpref.getString("setting_min_resist", ""));
		max_res.setText(sharedpref.getString("setting_max_resist", ""));
		t_sd_weight.setText(sharedpref.getString("weight_sd", ""));
		t_sd_start.setText(sharedpref.getString("start_sd", ""));
		t_sd_end.setText(sharedpref.getString("end_sd", ""));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}