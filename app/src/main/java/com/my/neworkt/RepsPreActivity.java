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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class RepsPreActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private double resist = 0;
	private double min_resist = 0;
	private double max_resist = 0;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private ImageView imageview1;
	private TextView txt_title;
	private TextView textview11;
	private SeekBar sb_top;
	private LinearLayout linear17;
	private LinearLayout lin_resist;
	private TextView d1;
	private TextView d2;
	private TextView textview1;
	private TextView d3;
	private TextView d4;
	private TextView txt_reps;
	private LinearLayout linear18;
	private LinearLayout linear13;
	private LinearLayout linear19;
	private TextView reps_minus;
	private TextView rd1;
	private TextView rd2;
	private TextView reps_plus;
	private TextView textview10;
	private TextView txt_resist;
	private SeekBar sb_bottom;
	private LinearLayout linear12;
	private Button btn_OK;
	private TextView s1;
	private TextView s2;
	private TextView s3;
	private TextView s4;
	
	private TimerTask timer;
	private SharedPreferences sharedpref;
	private Intent intent0 = new Intent();
	private RequestNetwork reqnet;
	private RequestNetwork.RequestListener _reqnet_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.reps_pre);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		txt_title = (TextView) findViewById(R.id.txt_title);
		textview11 = (TextView) findViewById(R.id.textview11);
		sb_top = (SeekBar) findViewById(R.id.sb_top);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		lin_resist = (LinearLayout) findViewById(R.id.lin_resist);
		d1 = (TextView) findViewById(R.id.d1);
		d2 = (TextView) findViewById(R.id.d2);
		textview1 = (TextView) findViewById(R.id.textview1);
		d3 = (TextView) findViewById(R.id.d3);
		d4 = (TextView) findViewById(R.id.d4);
		txt_reps = (TextView) findViewById(R.id.txt_reps);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		reps_minus = (TextView) findViewById(R.id.reps_minus);
		rd1 = (TextView) findViewById(R.id.rd1);
		rd2 = (TextView) findViewById(R.id.rd2);
		reps_plus = (TextView) findViewById(R.id.reps_plus);
		textview10 = (TextView) findViewById(R.id.textview10);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		sb_bottom = (SeekBar) findViewById(R.id.sb_bottom);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		s1 = (TextView) findViewById(R.id.s1);
		s2 = (TextView) findViewById(R.id.s2);
		s3 = (TextView) findViewById(R.id.s3);
		s4 = (TextView) findViewById(R.id.s4);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		reqnet = new RequestNetwork(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		reps_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(reps_minus);
				reps_minus.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttg(reps_minus);
								reps_minus.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (min_resist < resist) {
					resist--;
					rd1.setText(String.valueOf((long)(resist / 10)));
					rd2.setText(String.valueOf((long)(resist - (Double.parseDouble(rd1.getText().toString()) * 10))));
				}
			}
		});
		
		reps_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(reps_plus);
				reps_plus.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttg(reps_plus);
								reps_plus.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (resist < max_resist) {
					resist++;
					rd1.setText(String.valueOf((long)(resist / 10)));
					rd2.setText(String.valueOf((long)(resist - (Double.parseDouble(rd1.getText().toString()) * 10))));
				}
			}
		});
		
		btn_OK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sharedpref.edit().putString("train_resist", String.valueOf((long)(resist))).commit();
				intent0.setClass(getApplicationContext(), RepsTrainActivity.class);
				startActivity(intent0);
				finish();
			}
		});
		
		_reqnet_request_listener = new RequestNetwork.RequestListener() {
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
		_always_on();
		linear12.setVisibility(View.GONE);
		_roundgg(txt_reps);
		_roundgg(txt_resist);
		_roundg(d1);
		_roundg(d2);
		_roundg(d3);
		_roundg(d4);
		_buttg(reps_plus);
		_buttg(reps_minus);
		_roundg(rd1);
		_roundg(rd2);
		_buttr(btn_OK);
		d3.setText(String.valueOf((long)(Double.parseDouble(sharedpref.getString("train_reps", "")) / 10)));
		d4.setText(String.valueOf((long)(Double.parseDouble(sharedpref.getString("train_reps", "")) - (Double.parseDouble(d3.getText().toString()) * 10))));
		resist = Double.parseDouble(sharedpref.getString("train_resist", ""));
		rd1.setText(String.valueOf((long)(resist / 10)));
		rd2.setText(String.valueOf((long)(resist - (Double.parseDouble(rd1.getText().toString()) * 10))));
		min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		txt_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_reps.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps_minus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		rd1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		rd2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps_plus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_resist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		btn_OK.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		
	}
	public void _roundg (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		int clr[] = new int[]{ Color.parseColor("#14141f"),Color.parseColor("#14141f"),Color.parseColor("#474752"),Color.parseColor("#1f1f2e")};//, Color.parseColor("black") };
		gd.setColors(clr);
		//gd.setColor(Color.parseColor("#80808090"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		gd.setStroke(3, Color.parseColor("#808090"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _roundgg (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("#474752")};//, Color.parseColor("black") };
		//gd.setColors(clr);
		//gd.setColor(Color.parseColor("#80808090"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		gd.setStroke(3, Color.parseColor("#808090"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _buttg (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("#808090")};//, Color.parseColor("black") };
		//gd.setColors(clr);
		gd.setColor(Color.parseColor("#808090"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		//gd.setStroke(6, Color.parseColor("red"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _buttgp (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("#474752")};//, Color.parseColor("black") };
		//gd.setColors(clr);
		gd.setColor(Color.parseColor("red"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		//gd.setStroke(6, Color.parseColor("red"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _buttr (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("gray"), Color.parseColor("black") };
		gd.setColor(Color.parseColor("red"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, 0,0,0,0});
		gd.setStroke(6, Color.parseColor("red"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _buttrp (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("gray"), Color.parseColor("black") };
		gd.setColor(Color.parseColor("#B71C1C"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, 0,0,0,0});
		gd.setStroke(6, Color.parseColor("red"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _weight_off () {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=off")), "o", _reqnet_request_listener);
	}
	
	
	public void _always_on () {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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