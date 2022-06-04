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
import java.util.ArrayList;
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
import java.text.DecimalFormat;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class SecsPreActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private double resist = 0;
	private double max_resist = 0;
	private double min_resist = 0;
	private String strmnts = "";
	private String strsecs = "";
	private String strscspa = "";
	private double secs = 0;
	private double setting_min_secs = 0;
	
	private ArrayList<String> timeformatlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear14;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private ImageView imageview2;
	private TextView txt_title;
	private TextView textview11;
	private SeekBar sb_top;
	private TextView txt_time;
	private ImageView imageview1;
	private TextView reps_minus;
	private TextView rd1;
	private TextView rd2;
	private TextView reps_plus;
	private TextView textview10;
	private LinearLayout linear13;
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
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.secs_pre);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		txt_title = (TextView) findViewById(R.id.txt_title);
		textview11 = (TextView) findViewById(R.id.textview11);
		sb_top = (SeekBar) findViewById(R.id.sb_top);
		txt_time = (TextView) findViewById(R.id.txt_time);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		reps_minus = (TextView) findViewById(R.id.reps_minus);
		rd1 = (TextView) findViewById(R.id.rd1);
		rd2 = (TextView) findViewById(R.id.rd2);
		reps_plus = (TextView) findViewById(R.id.reps_plus);
		textview10 = (TextView) findViewById(R.id.textview10);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		sb_bottom = (SeekBar) findViewById(R.id.sb_bottom);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		s1 = (TextView) findViewById(R.id.s1);
		s2 = (TextView) findViewById(R.id.s2);
		s3 = (TextView) findViewById(R.id.s3);
		s4 = (TextView) findViewById(R.id.s4);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
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
				intent0.setClass(getApplicationContext(), SecsTrainActivity.class);
				startActivity(intent0);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		_always_on();
		linear12.setVisibility(View.GONE);
		_roundgg(txt_resist);
		_buttg(reps_plus);
		_buttg(reps_minus);
		_roundg(rd1);
		_roundg(rd2);
		_buttr(btn_OK);
		resist = Double.parseDouble(sharedpref.getString("train_resist", ""));
		rd1.setText(String.valueOf((long)(resist / 10)));
		rd2.setText(String.valueOf((long)(resist - (Double.parseDouble(rd1.getText().toString()) * 10))));
		min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		secs = Double.parseDouble(sharedpref.getString("train_secs", ""));
		setting_min_secs = Double.parseDouble(sharedpref.getString("setting_min_secs", ""));
		_timeformat(secs);
		s1.setText(timeformatlist.get((int)(0)));
		s2.setText(timeformatlist.get((int)(1)));
		s3.setText(timeformatlist.get((int)(2)));
		s4.setText(String.valueOf((long)(secs)).substring((int)(String.valueOf((long)(secs)).length() - 1), (int)(String.valueOf((long)(secs)).length())));
		txt_time.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
		txt_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps_minus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		rd1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		rd2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps_plus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_resist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		btn_OK.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
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
	
	
	public void _timeformat (final double _seconds) {
		strmnts = String.valueOf((long)(_seconds / 60));
		strsecs = String.valueOf((long)((Double.parseDouble(String.valueOf(_seconds / 60)) - Double.parseDouble(String.valueOf((long)(_seconds / 60)))) * 60));
		strscspa = String.valueOf((Double.parseDouble(String.valueOf(_seconds / 60)) - Double.parseDouble(String.valueOf((long)(_seconds / 60)))) * 60);
		timeformatlist.add((int)(0), String.valueOf((long)(Double.parseDouble(strmnts) / 10)));
		timeformatlist.add((int)(1), String.valueOf((long)((Double.parseDouble(String.valueOf(Double.parseDouble(strmnts) / 10)) - Double.parseDouble(String.valueOf((long)(Double.parseDouble(strmnts) / 10)))) * 10)));
		timeformatlist.add((int)(2), String.valueOf((long)((Double.parseDouble(strscspa) / 9.9d) + 0)));
		timeformatlist.add((int)(3), String.valueOf((long)((Double.parseDouble(String.valueOf(Double.parseDouble(strscspa) / 9.9d)) * 9.9d) - (Double.parseDouble(String.valueOf((long)(Double.parseDouble(strscspa) / 9.9d))) * 9.9d))));
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