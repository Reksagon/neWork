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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class Ident0Activity extends  Activity { 
	
	
	private double cnt = 0;
	private String key = "";
	private HashMap<String, Object> dev = new HashMap<>();
	private double device_id = 0;
	private String key_s = "";
	private HashMap<String, Object> testfilt = new HashMap<>();
	
	private ArrayList<String> lststr = new ArrayList<>();
	private ArrayList<String> filtered = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> enablesmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear11;
	private LinearLayout linear9;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear10;
	private Button save;
	private TextView textview12;
	private EditText name_of_device;
	private TextView textview11;
	private Spinner spinner1;
	private TextView textview2;
	private EditText ipad;
	private TextView textview3;
	private EditText mult;
	private TextView textview4;
	private EditText divi;
	private TextView textview5;
	private EditText res_ref;
	private Button res;
	private Button neu;
	private Button start;
	private Button stop;
	private ListView listview1;
	private Spinner spinner2;
	
	private SharedPreferences sharedpref;
	private Intent int0 = new Intent();
	private RequestNetwork netreq;
	private RequestNetwork.RequestListener _netreq_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.ident0);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		save = (Button) findViewById(R.id.save);
		textview12 = (TextView) findViewById(R.id.textview12);
		name_of_device = (EditText) findViewById(R.id.name_of_device);
		textview11 = (TextView) findViewById(R.id.textview11);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		textview2 = (TextView) findViewById(R.id.textview2);
		ipad = (EditText) findViewById(R.id.ipad);
		textview3 = (TextView) findViewById(R.id.textview3);
		mult = (EditText) findViewById(R.id.mult);
		textview4 = (TextView) findViewById(R.id.textview4);
		divi = (EditText) findViewById(R.id.divi);
		textview5 = (TextView) findViewById(R.id.textview5);
		res_ref = (EditText) findViewById(R.id.res_ref);
		res = (Button) findViewById(R.id.res);
		neu = (Button) findViewById(R.id.neu);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		listview1 = (ListView) findViewById(R.id.listview1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		netreq = new RequestNetwork(this);
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sharedpref.edit().putString("ip", ipad.getText().toString()).commit();
				sharedpref.edit().putString("res_mul", mult.getText().toString()).commit();
				sharedpref.edit().putString("res_div", divi.getText().toString()).commit();
				sharedpref.edit().putString("devices", new Gson().toJson(dev)).commit();
				int0.setClass(getApplicationContext(), ChoosetypeActivity.class);
				startActivity(int0);
				if (false) {
					//removed feature for version 1.0
					sharedpref.edit().putString("device_name", lststr.get((int)(spinner1.getSelectedItemPosition()))).commit();
				}
				sharedpref.edit().putString("device_name", name_of_device.getText().toString()).commit();
				finish();
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				testfilt.clear();
				enablesmap.clear();
				device_id = _position;
				cnt = 1;
				for(int _repeat129 = 0; _repeat129 < (int)(6); _repeat129++) {
					key = String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))));
					key_s = String.valueOf((long)(device_id + 1)).concat("e".concat(String.valueOf((long)(cnt))));
					if (dev.containsKey(key)) {
						testfilt = new HashMap<>();
						testfilt.put("name", dev.get(key).toString());
						testfilt.put("enabled", dev.get(key_s).toString());
						enablesmap.add(testfilt);
					}
					cnt++;
				}
				listview1.setAdapter(new Listview1Adapter(enablesmap));
				if (false) {
					//removed feature for version 1.0
					sharedpref.edit().putString("device_name", lststr.get((int)(_position))).commit();
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
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
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
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
		ipad.setText(sharedpref.getString("ip", ""));
		mult.setText(sharedpref.getString("res_mul", ""));
		divi.setText(sharedpref.getString("res_div", ""));
		dev = new Gson().fromJson(sharedpref.getString("devices", ""), new TypeToken<HashMap<String, Object>>(){}.getType());
		cnt = 1;
		for(int _repeat21 = 0; _repeat21 < (int)(21); _repeat21++) {
			key = String.valueOf((long)(cnt));
			if (dev.containsKey(key)) {
				lststr.add(dev.get(key).toString());
			}
			cnt++;
		}
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lststr));
		if (lststr.contains(sharedpref.getString("device_name", ""))) {
			spinner1.setSelection((int)(lststr.indexOf(sharedpref.getString("device_name", ""))));
		}
		else {
			spinner1.setSelection((int)(0));
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.exercises, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final TextView train = (TextView) _view.findViewById(R.id.train);
			final CheckBox enable = (CheckBox) _view.findViewById(R.id.enable);
			
			train.setText(enablesmap.get((int)_position).get("name").toString());
			if (enablesmap.get((int)_position).get("enabled").toString().equals("yes")) {
				enable.setChecked(true);
			}
			else {
				enable.setChecked(false);
			}
			enable.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (enable.isChecked()) {
						dev.put(String.valueOf((long)(device_id + 1)).concat("e".concat(String.valueOf((long)(_position + 1)))), "yes");
					}
					else {
						dev.put(String.valueOf((long)(device_id + 1)).concat("e".concat(String.valueOf((long)(_position + 1)))), "no");
					}
				}
			});
			
			return _view;
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