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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import java.text.DecimalFormat;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

import com.my.neworkt.databinding.Choosetype2Binding;
import com.my.neworkt.databinding.ChoosetypeBinding;

import me.tankery.lib.circularseekbar.CircularSeekBar;


public class ChoosetypeActivity extends  Activity { 
	
	private Timer _timer = new Timer();

	private boolean expand = false;
	private int time_animate = 200;
	private double train_resist = 0;
	private double setting_min_resist = 0;
	private double setting_max_resist = 0;
	private double resist_seek_max = 0;
	private double resiat_seek_progress = 0;
	private String train_type = "";
	private double setting_min_secs = 0;
	private double setting_max_secs = 0;
	private double setting_min_reps = 0;
	private double setting_max_reps = 0;
	private double bottom_seek_max = 0;
	private double bottom_seek_progress = 0;
	private double train_reps = 0;
	private double train_secs = 0;
	private String stringMins = "";
	private String strsecs = "";
	private String strmnts = "";
	private String strscspa = "";
	private boolean admin_flag_1 = false;
	private boolean admin_flag_3 = false;
	private boolean admin_flag_4 = false;
	private boolean admin_flag_2 = false;
	private boolean flag_engine_off = false;
	private double hour_now = 0;
	
	private ArrayList<String> timeformatlist = new ArrayList<>();
	private ArrayList<String> test = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear13;
	private TextView txt_title;
	private ImageView imageview1;
	private TextView top_minus;
	private LinearLayout lin_resist;
	private TextView top_plus;
	private LinearLayout linear11;
	private TextView txt_resist;
	private TextView resist;
	private TextView textview10;
	private SeekBar sb_top;
	private TextView bottom_minus;
	private LinearLayout lin_secs;
	private LinearLayout lin_reps;
	private TextView bottom_plus;
	private TextView secs;
	private TextView txt_secs;
	private TextView reps;
	private TextView txt_reps;
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
	private AlertDialog.Builder dia0;
	private TimerTask timer_motor;
	private Calendar cal = Calendar.getInstance();

	Choosetype2Binding binding;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = Choosetype2Binding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		reqnet = new RequestNetwork(this);
		dia0 = new AlertDialog.Builder(this);

		binding.weight.setMax(100);
		binding.weight.setStartAngle(325);
		binding.weight.setEndAngle(215);
		binding.weight.setCircleStrokeWidth(25);
		binding.weight.setPointerColor(Color.parseColor("#DF2323"));
		binding.weight.setCircleProgressColor(Color.parseColor("#DF2323"));
		binding.weight.setPointerStrokeWidth(60);
		binding.weight.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
			@Override
			public void onProgressChanged(CircularSeekBar circularSeekBar, float v, boolean b) {
				resiat_seek_progress = (int) v;
				binding.txtWeight.setText(String.valueOf((int) (v + setting_min_resist)));
			}

			@Override
			public void onStopTrackingTouch(CircularSeekBar circularSeekBar) {

			}

			@Override
			public void onStartTrackingTouch(CircularSeekBar circularSeekBar) {
				sharedpref.edit().putString("train_resist", String.valueOf((long) (circularSeekBar.getProgress() + setting_min_resist))).apply();
			}
		});


		binding.repss.setMax(100);
		binding.repss.setStartAngle(325);
		binding.repss.setEndAngle(215);
		binding.repss.setCircleStrokeWidth(25);
		binding.repss.setPointerColor(Color.parseColor("#DF2323"));
		binding.repss.setCircleProgressColor(Color.parseColor("#DF2323"));
		binding.repss.setPointerStrokeWidth(60);
		binding.repss.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
			@Override
			public void onProgressChanged(CircularSeekBar circularSeekBar, float v, boolean b) {
				resiat_seek_progress = (int) v;
				binding.txtRepss.setText(String.valueOf((int) (v + setting_min_resist)));
			}

			@Override
			public void onStopTrackingTouch(CircularSeekBar circularSeekBar) {

			}

			@Override
			public void onStartTrackingTouch(CircularSeekBar circularSeekBar) {

				sharedpref.edit().putString("train_reps", String.valueOf((long) (circularSeekBar.getProgress() + setting_min_reps))).apply();
				train_reps = circularSeekBar.getProgress() + setting_min_reps;

			}
		});

		binding.bttnUpWeight.setOnClickListener(v ->
		{
			if ((int) binding.weight.getProgress() < 100)
				binding.weight.setProgress(binding.weight.getProgress() + 1);
		});

		binding.bttnDownWeight.setOnClickListener(v ->
		{
			if ((int) binding.weight.getProgress() > 0)
				binding.weight.setProgress(binding.weight.getProgress() - 1);
		});

		binding.bttnUpRepss.setOnClickListener(v ->
		{
			if ((int) binding.repss.getProgress() < 100)
				binding.repss.setProgress(binding.repss.getProgress() + 1);
		});

		binding.bttnDownRepss.setOnClickListener(v ->
		{
			if ((int) binding.repss.getProgress() > 0)
				binding.repss.setProgress(binding.repss.getProgress() - 1);
		});

		binding.bttnStart.setOnClickListener(v ->
		{
			_buttrp(binding.bttnStart);
			binding.bttnStart.setTextColor(0xFFE0E0E0);
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_buttr(binding.bttnStart);
							binding.bttnStart.setTextColor(0xFFFFFFFF);
						}
					});
				}
			};
			_timer.schedule(timer, (int) (200));

			sharedpref.edit().putString("train_reps", String.valueOf((long) (binding.repss.getProgress() + setting_min_reps))).apply();
			intent0.setClass(getApplicationContext(), RepsTrainActivity.class);
			startActivity(intent0);

			sharedpref.edit().putString("train_resist", String.valueOf((long) (binding.weight.getProgress() + setting_min_resist))).apply();
			sharedpref.edit().putString("train_type", train_type).apply();
		});

		binding.barExpand.setTranslationX(-2000);
		binding.bttnCancel.setTranslationX(-2000);
		binding.txtDeviceNameIp.setTranslationX(-2000);
		binding.txtNumberResistance.setTranslationX(-2000);
		binding.txtRatioRangeOf.setTranslationX(-2000);
		binding.txtResistanceR.setTranslationX(-2000);

		binding.bttnBar.setOnClickListener(v->
		{
			if(!expand) {
				binding.barExpand.animate().translationX(0).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.bttnCancel.animate().translationX(0).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtDeviceNameIp.animate().translationX(0).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtNumberResistance.animate().translationX(0).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtRatioRangeOf.animate().translationX(0).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtResistanceR.animate().translationX(0).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				expand = true;
			}
			else
			{
				binding.barExpand.animate().translationX(-2000).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.bttnCancel.animate().translationX(-2000).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtDeviceNameIp.animate().translationX(-2000).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtNumberResistance.animate().translationX(-2000).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtRatioRangeOf.animate().translationX(-2000).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				binding.txtResistanceR.animate().translationX(-2000).setDuration(time_animate).setInterpolator(new BounceInterpolator());
				expand = false;
			}
		});

		binding.bttnCancel.setOnClickListener(v->
		{
			binding.barExpand.animate().translationX(-2000).setDuration(100);
			binding.bttnCancel.animate().translationX(-2000).setDuration(100);
			binding.txtDeviceNameIp.animate().translationX(-2000).setDuration(100);
			binding.txtNumberResistance.animate().translationX(-2000).setDuration(100);
			binding.txtRatioRangeOf.animate().translationX(-2000).setDuration(100);
			binding.txtResistanceR.animate().translationX(-2000).setDuration(100);
			expand = false;
		});
		//initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		txt_title = (TextView) findViewById(R.id.txt_title);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		top_minus = (TextView) findViewById(R.id.top_minus);
		lin_resist = (LinearLayout) findViewById(R.id.lin_resist);
		top_plus = (TextView) findViewById(R.id.top_plus);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		resist = (TextView) findViewById(R.id.resist);
		textview10 = (TextView) findViewById(R.id.textview10);
		sb_top = (SeekBar) findViewById(R.id.sb_top);
		bottom_minus = (TextView) findViewById(R.id.bottom_minus);
		lin_secs = (LinearLayout) findViewById(R.id.lin_secs);
		lin_reps = (LinearLayout) findViewById(R.id.lin_reps);
		bottom_plus = (TextView) findViewById(R.id.bottom_plus);
		secs = (TextView) findViewById(R.id.secs);
		txt_secs = (TextView) findViewById(R.id.txt_secs);
		reps = (TextView) findViewById(R.id.reps);
		txt_reps = (TextView) findViewById(R.id.txt_reps);
		sb_bottom = (SeekBar) findViewById(R.id.sb_bottom);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		s1 = (TextView) findViewById(R.id.s1);
		s2 = (TextView) findViewById(R.id.s2);
		s3 = (TextView) findViewById(R.id.s3);
		s4 = (TextView) findViewById(R.id.s4);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		reqnet = new RequestNetwork(this);
		dia0 = new AlertDialog.Builder(this);
		
		linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				admin_flag_1 = false;
				admin_flag_2 = false;
				admin_flag_3 = false;
				admin_flag_4 = false;
			}
		});
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (false) {
					intent0.setClass(getApplicationContext(), Settings221020Activity.class);
					startActivity(intent0);
				}
			}
		});
		
		txt_title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (false) {
					imageview1.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
									intent0.setClass(getApplicationContext(), NewdevActivity.class);
									startActivity(intent0);
								}
							});
						}
					};
					_timer.schedule(timer, (int)(200));
				}
				if (admin_flag_4) {
					admin_flag_1 = false;
					admin_flag_2 = false;
					admin_flag_3 = false;
					admin_flag_4 = false;
					intent0.setClass(getApplicationContext(), Settings221020Activity.class);
					startActivity(intent0);
				}
				else {
					if (admin_flag_3) {
						admin_flag_4 = true;
					}
					else {
						if (admin_flag_2) {
							admin_flag_3 = true;
						}
						else {
							if (admin_flag_1) {
								admin_flag_2 = true;
							}
							else {
								admin_flag_1 = true;
							}
						}
					}
				}
				imageview1.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
			}
		});
		
		top_minus.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				
				return true;
				}
			 });
		
		top_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(top_minus);
				top_minus.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttg(top_minus);
								top_minus.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (0 < resiat_seek_progress) {
					resiat_seek_progress--;
					sb_top.setProgress((int)resiat_seek_progress);
					resist.setText(String.valueOf((long)(sb_top.getProgress() + setting_min_resist)));
				}
			}
		});
		
		top_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(top_plus);
				top_plus.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttg(top_plus);
								top_plus.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (resiat_seek_progress < sb_top.getMax()) {
					resiat_seek_progress++;
					sb_top.setProgress((int)resiat_seek_progress);
					resist.setText(String.valueOf((long)(sb_top.getProgress() + setting_min_resist)));
				}
			}
		});
		
		txt_resist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		sb_top.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				resiat_seek_progress = _progressValue;
				resist.setText(String.valueOf((long)(_progressValue + setting_min_resist)));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				sharedpref.edit().putString("train_resist", String.valueOf((long)(sb_top.getProgress() + setting_min_resist))).commit();
			}
		});
		
		bottom_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(bottom_minus);
				bottom_minus.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttg(bottom_minus);
								bottom_minus.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (0 < bottom_seek_progress) {
					bottom_seek_progress--;
					sb_bottom.setProgress((int)bottom_seek_progress);
					if (train_type.equals("reps")) {
						reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
					}
					else {
						secs.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_secs)));
						_timeformat(sb_bottom.getProgress() + setting_min_secs);
						s1.setText(timeformatlist.get((int)(0)));
						s2.setText(timeformatlist.get((int)(1)));
						s3.setText(timeformatlist.get((int)(2)));
						s4.setText(secs.getText().toString().substring((int)(secs.getText().toString().length() - 1), (int)(secs.getText().toString().length())));
						secs.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
					}
				}
			}
		});
		
		lin_secs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				train_type = "secs";
				_roundg(lin_reps);
				_roundr(lin_secs);
				txt_secs.setTextColor(0xFFADADB8);
				secs.setTextColor(0xFFFF0000);
				txt_reps.setTextColor(0xFF474752);
				reps.setTextColor(0xFF474752);
				bottom_seek_max = setting_max_secs - setting_min_secs;
				sb_bottom.setMax((int)bottom_seek_max);
				bottom_seek_progress = train_secs - setting_min_secs;
				sb_bottom.setProgress((int)bottom_seek_progress);
				secs.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_secs)));
				_timeformat(sb_bottom.getProgress() + setting_min_secs);
				s1.setText(timeformatlist.get((int)(0)));
				s2.setText(timeformatlist.get((int)(1)));
				s3.setText(timeformatlist.get((int)(2)));
				s4.setText(secs.getText().toString().substring((int)(secs.getText().toString().length() - 1), (int)(secs.getText().toString().length())));
				secs.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
			}
		});
		
		lin_reps.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				train_type = "reps";
				_roundr(lin_reps);
				_roundg(lin_secs);
				txt_reps.setTextColor(0xFFADADB8);
				reps.setTextColor(0xFFFF0000);
				txt_secs.setTextColor(0xFF474752);
				secs.setTextColor(0xFF474752);
				bottom_seek_max = setting_max_reps - setting_min_reps;
				sb_bottom.setMax((int)bottom_seek_max);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
				reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
			}
		});
		
		bottom_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(bottom_plus);
				bottom_plus.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttg(bottom_plus);
								bottom_plus.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (bottom_seek_progress < sb_bottom.getMax()) {
					bottom_seek_progress++;
					sb_bottom.setProgress((int)bottom_seek_progress);
					if (train_type.equals("reps")) {
						reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
					}
					else {
						secs.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_secs)));
						_timeformat(sb_bottom.getProgress() + setting_min_secs);
						s1.setText(timeformatlist.get((int)(0)));
						s2.setText(timeformatlist.get((int)(1)));
						s3.setText(timeformatlist.get((int)(2)));
						s4.setText(secs.getText().toString().substring((int)(secs.getText().toString().length() - 1), (int)(secs.getText().toString().length())));
						secs.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
					}
				}
			}
		});
		
		sb_bottom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				bottom_seek_progress = _progressValue;
				if (train_type.equals("reps")) {
					reps.setText(String.valueOf((long)(_progressValue + setting_min_reps)));
				}
				else {
					secs.setText(String.valueOf((long)(_progressValue + setting_min_secs)));
					_timeformat(_progressValue + setting_min_secs);
					s1.setText(timeformatlist.get((int)(0)));
					s2.setText(timeformatlist.get((int)(1)));
					s3.setText(timeformatlist.get((int)(2)));
					s4.setText(secs.getText().toString().substring((int)(secs.getText().toString().length() - 1), (int)(secs.getText().toString().length())));
					secs.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				if (train_type.equals("reps")) {
					sharedpref.edit().putString("train_reps", String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps))).commit();
					train_reps = sb_bottom.getProgress() + setting_min_reps;
				}
				else {
					sharedpref.edit().putString("train_secs", String.valueOf((long)(sb_bottom.getProgress() + setting_min_secs))).commit();
					train_secs = sb_bottom.getProgress() + setting_min_secs;
				}
			}
		});
		
		btn_OK.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				
				return true;
				}
			 });
		
		btn_OK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttrp(btn_OK);
				btn_OK.setTextColor(0xFFE0E0E0);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttr(btn_OK);
								btn_OK.setTextColor(0xFFFFFFFF);
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
				if (train_type.equals("reps")) {
					sharedpref.edit().putString("train_reps", String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps))).commit();
					intent0.setClass(getApplicationContext(), RepsTrainActivity.class);
					startActivity(intent0);
				}
				else {
					sharedpref.edit().putString("train_secs", String.valueOf((long)(sb_bottom.getProgress() + setting_min_secs))).commit();
					intent0.setClass(getApplicationContext(), SecsTrainActivity.class);
					startActivity(intent0);
				}
				sharedpref.edit().putString("train_resist", String.valueOf((long)(sb_top.getProgress() + setting_min_resist))).commit();
				sharedpref.edit().putString("train_type", train_type).commit();
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
//		imageview1.setVisibility(View.VISIBLE);
//		_weight_off();
//		if (false) {
//			_always_on();
//		}
//		txt_title.setText(sharedpref.getString("device_name", ""));
//		imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
//		linear12.setVisibility(View.GONE);
		train_resist = Double.parseDouble(sharedpref.getString("train_resist", ""));
		train_reps = Double.parseDouble(sharedpref.getString("train_reps", ""));
		train_secs = Double.parseDouble(sharedpref.getString("train_secs", ""));
		setting_min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		setting_max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		setting_min_secs = Double.parseDouble(sharedpref.getString("setting_min_secs", ""));
		setting_max_secs = Double.parseDouble(sharedpref.getString("setting_max_secs", ""));
		setting_min_reps = Double.parseDouble(sharedpref.getString("setting_min_reps", ""));
		setting_max_reps = Double.parseDouble(sharedpref.getString("setting_max_reps", ""));
		train_type = sharedpref.getString("train_type", "");
//		_buttr(btn_OK);
//		_buttg(top_minus);
//		_buttg(top_plus);
//		_buttg(bottom_minus);
//		_buttg(bottom_plus);
//		_roundg(lin_resist);
		resist_seek_max = setting_max_resist - setting_min_resist;
		binding.weight.setMax((int)resist_seek_max);
		resiat_seek_progress = train_resist - setting_min_resist;
		binding.weight.setProgress((int)resiat_seek_progress);
		//resist.setText(String.valueOf((long)(sb_top.getProgress() + setting_min_resist)));
		//if (train_type.equals("reps")) {
			//txt_reps.setTextColor(0xFFADADB8);
			//reps.setTextColor(0xFFFF0000);
			//txt_secs.setTextColor(0xFF474752);
			//secs.setTextColor(0xFF474752);
			//_roundr(lin_reps);
			//_roundg(lin_secs);
			bottom_seek_max = setting_max_reps - setting_min_reps;
			binding.repss.setMax((int)bottom_seek_max);
			bottom_seek_progress = train_reps - setting_min_reps;
			binding.repss.setProgress((int)bottom_seek_progress);
			//reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
		//}
//		else {
//			txt_secs.setTextColor(0xFFADADB8);
//			secs.setTextColor(0xFFFF0000);
//			txt_reps.setTextColor(0xFF474752);
//			reps.setTextColor(0xFF474752);
//			_roundg(lin_reps);
//			_roundr(lin_secs);
//			bottom_seek_max = setting_max_secs - setting_min_secs;
//			sb_bottom.setMax((int)bottom_seek_max);
//			bottom_seek_progress = train_secs - setting_min_secs;
//			sb_bottom.setProgress((int)bottom_seek_progress);
//			secs.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_secs)));
//			_timeformat(sb_bottom.getProgress() + setting_min_secs);
//			s1.setText(timeformatlist.get((int)(0)));
//			s2.setText(timeformatlist.get((int)(1)));
//			s3.setText(timeformatlist.get((int)(2)));
//			s4.setText(secs.getText().toString().substring((int)(secs.getText().toString().length() - 1), (int)(secs.getText().toString().length())));
//			secs.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
//		}

//		sb_top.getProgressDrawable().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
//		sb_top.getThumb().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
//		sb_bottom.getProgressDrawable().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
		

		//system admin enter field
//		admin_flag_1 = false;
//		admin_flag_2 = false;
//		admin_flag_3 = false;
//		admin_flag_4 = false;
//		if (false) {
//			_motor_timer(Double.parseDouble(sharedpref.getString("end_sd", "")), Double.parseDouble(sharedpref.getString("start_sd", "")), true);
//		}
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
	
	
	public void _roundg (final View _view) {
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
	
	
	public void _roundr (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("#474752")};//, Color.parseColor("black") };
		//gd.setColors(clr);
		//gd.setColor(Color.parseColor("red"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		gd.setStroke(3, Color.parseColor("red"));
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
	
	
	public void _weight_off () {
		if (false) {
			reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=off")), "o", _reqnet_request_listener);
		}
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=")).concat(String.valueOf((long)((Double.parseDouble(sharedpref.getString("weight_off", "")) * Double.parseDouble(sharedpref.getString("res_mul", ""))) / Double.parseDouble(sharedpref.getString("res_div", ""))))), "weight", _reqnet_request_listener);
	}
	
	
	public void _always_on () {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	public void _motor_timer (final double _turn_off, final double _turn_on, final boolean _enabled) {
		flag_engine_off = false;
		timer_motor = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						cal = Calendar.getInstance();
						hour_now = Double.parseDouble(new SimpleDateFormat("H").format(cal.getTime()));
						if (_turn_off > _turn_on) {
							if (((_turn_on < hour_now) || (_turn_on == hour_now)) && (hour_now < _turn_off)) {
								if (flag_engine_off) {
									_weight_off();
									imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
									flag_engine_off = false;
								}
							}
							else {
								if (!flag_engine_off) {
									_weight_sd();
									imageview1.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
									flag_engine_off = true;
								}
							}
						}
						else {
							if (((_turn_on < hour_now) || (_turn_on == hour_now)) && (hour_now < _turn_off)) {
								if (!flag_engine_off) {
									_weight_sd();
									imageview1.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
									flag_engine_off = true;
								}
							}
							else {
								if (flag_engine_off) {
									_weight_off();
									imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
									flag_engine_off = false;
								}
							}
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(timer_motor, (int)(0), (int)(10000));
	}
	
	
	public void _weight_sd () {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=")).concat(String.valueOf((long)((Double.parseDouble(sharedpref.getString("weight_sd", "")) * Double.parseDouble(sharedpref.getString("res_mul", ""))) / Double.parseDouble(sharedpref.getString("res_div", ""))))), "weight", _reqnet_request_listener);
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
