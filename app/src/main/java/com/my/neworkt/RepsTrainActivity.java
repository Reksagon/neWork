package com.my.neworkt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.util.*;

import java.util.*;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.graphics.Typeface;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.my.neworkt.databinding.SecsTrain2Binding;

import me.tankery.lib.circularseekbar.CircularSeekBar;


public class RepsTrainActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private String strmnts = "";
	private String strsecs = "";
	private String strscspa = "";
	private double resist = 0;
	private double min_resist = 0;
	private double max_resist = 0;
	private double train_time = 0;
	private double done_reps = 0;
	private double train_reps = 0;
	private boolean done_flag = false;
	private double intrange = 0;
	
	private ArrayList<String> timeformatlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear7;
	private LinearLayout linear15;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview3;
	private TextView txt_title;
	private LinearLayout linear12;
	private Button btn_OK;
	private TextView s1;
	private TextView s2;
	private TextView s3;
	private TextView s4;
	private TextView txt_time;
	private ImageView imageview1;
	private TextView textview12;
	private ImageView imageview2;
	private TextView textview11;
	private LinearLayout linear19;
	private LinearLayout lin_resist;
	private TextView d1;
	private TextView d2;
	private TextView textview1;
	private TextView d3;
	private TextView d4;
	private TextView txt_reps;
	private LinearLayout linear20;
	private LinearLayout linear13;
	private LinearLayout linear16;
	private TextView reps_minus;
	private TextView rd1;
	private TextView rd2;
	private TextView reps_plus;
	private TextView textview10;
	private TextView txt_resist;
	private LinearLayout bar1;
	private LinearLayout bar2;
	private LinearLayout bar3;
	private LinearLayout bar4;
	private LinearLayout bar5;
	private LinearLayout bar6;
	private LinearLayout bar7;
	private LinearLayout bar8;
	private LinearLayout bar9;
	private LinearLayout bar10;
	private LinearLayout bar11;
	private LinearLayout bar12;
	private LinearLayout bar13;
	private LinearLayout bar14;
	private LinearLayout bar15;
	private LinearLayout bar16;
	private LinearLayout bar17;
	private LinearLayout bar18;
	private LinearLayout bar19;
	private LinearLayout bar20;
	
	private SharedPreferences sharedpref;
	private TimerTask tw_reps;
	private TimerTask timer;
	private Intent intent0 = new Intent();
	private TimerTask delay;
	private AlertDialog.Builder dialogue;
	private RequestNetwork reqnet;
	private RequestNetwork.RequestListener _reqnet_request_listener;
	private RequestNetwork reqnet_fast;
	private RequestNetwork.RequestListener _reqnet_fast_request_listener;
	private TimerTask tw_range;
	SecsTrain2Binding binding;
	ImageView[] trains;
	private int yDelta;
	int max_range = 0;

	int lume = 0, range = 0, power = 0;
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		public void run() {
			secs += 100;
			if((secs / 100) % 70 == 0)
			{
				anim();
				mode = true;

			}

			if(mode)
			{
				if((secs / 100) % 5 == 0) {
					if (resist > min_resist) {
						resist--;
						sharedpref.edit().putString("train_resist", String.valueOf((long) (resist))).apply();
						binding.txtWeight.setText(String.valueOf((int) resist));
						_set_resist_during_set(resist);
					}
				}
			}
			handler.postDelayed(this, 100);
		}
	};
	int secs = 0;
	boolean mode = false;
	boolean start_auto_weight = false;

	private void anim()
	{
		if(!mode)
		{
			binding.logoImg.animate().scaleX(0).setDuration(300);
			Handler handler1 = new Handler();
			handler1.postDelayed(new Runnable() {
				@Override
				public void run() {
					binding.logoImg.setBackground(getResources().getDrawable(R.drawable.logo_train_2));
					binding.logoImg.getLayoutParams().width = (int) getDip(100);
					binding.logoImg.animate().scaleX(1).setDuration(300);
				}
			}, 300);
		}
	}

	@SuppressLint({"NewApi", "SetTextI18n", "UseCompatLoadingForDrawables"})
	@RequiresApi(api = Build.VERSION_CODES.Q)
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);

		binding = SecsTrain2Binding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));


		binding.weight.setStartAngle(320);
		binding.weight.setEndAngle(220);
		binding.weight.setCircleStrokeWidth(getResources().getFloat(R.dimen.stroke_width_circle));
		binding.weight.setPointerColor(Color.parseColor("#FFFFFF"));
		binding.weight.setCircleProgressColor(Color.parseColor("#FFFFFF"));
		binding.weight.setEnabled(false);
		binding.weight.setPointerStrokeWidth(1);


		resist = Double.parseDouble(sharedpref.getString("train_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		binding.weight.setMax(21);
		binding.weight.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
			@Override
			public void onProgressChanged(@Nullable CircularSeekBar circularSeekBar, float v, boolean b) {
				if((int)v > max_range)
					max_range = (int)v;
			}

			@Override
			public void onStopTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

			}

			@Override
			public void onStartTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

			}
		});
		binding.txtWeight.setText(String.valueOf((int)resist));


		binding.txtWeight.setOnClickListener(v->
		{
			if(mode)
			{
				mode = false;
				binding.logoImg.setBackground(getResources().getDrawable(R.drawable.ic_w));
				binding.logoImg.getLayoutParams().width = (int) getDip(60);
				secs = 0;
				start_auto_weight = false;
				handler.removeCallbacks(runnable);
			}
		});

		binding.bttnUpWeight.setOnClickListener(v ->
		{
			if (resist < (int)max_resist)
			{
				resist++;
				sharedpref.edit().putString("train_resist", String.valueOf((long) (resist))).apply();
				binding.txtWeight.setText(String.valueOf((int) resist));
				_set_resist_during_set(resist);
				if(mode)
				{
					mode = false;
					binding.logoImg.setBackground(getResources().getDrawable(R.drawable.ic_w));
					binding.logoImg.getLayoutParams().width = (int) getDip(60);
					secs = 0;
					start_auto_weight = false;
					handler.removeCallbacks(runnable);
				}
			}
		});

		binding.bttnDownWeight.setOnClickListener(v ->
		{
			if (resist > (int)min_resist)
			{
				resist--;
				sharedpref.edit().putString("train_resist", String.valueOf((long) (resist))).apply();
				binding.txtWeight.setText(String.valueOf((int) resist));
				_set_resist_during_set(resist);
				if(mode)
				{
					mode = false;
					binding.logoImg.setBackground(getResources().getDrawable(R.drawable.ic_w));
					binding.logoImg.getLayoutParams().width = (int) getDip(60);
					secs = 0;
					start_auto_weight = false;
					handler.removeCallbacks(runnable);
				}
			}
		});

		done_reps = 0;
		binding.reps1.setText(String.valueOf((int)done_reps));
		int train_reps = (int)Double.parseDouble(sharedpref.getString("train_reps", ""));
		binding.reps2.setText("/" + train_reps);
		trains = new ImageView[train_reps];

		for(int i = 0; i < train_reps; i++)
		{
			ImageView imageView = new ImageView(this);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_red));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10, 1);
			layoutParams.setMargins(5,0,5,0);
			layoutParams.gravity = Gravity.BOTTOM;
			imageView.setLayoutParams(layoutParams);
			trains[i] = imageView;
			binding.linerTrains.addView(imageView);
		}

		binding.root.setOnTouchListener(new View.OnTouchListener() {
			@Override public boolean onTouch(View view, MotionEvent event) {
				final int x = (int) event.getRawX();
				final int y = (int) event.getRawY();

				switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN: {
						FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) binding.percent.getLayoutParams();

						yDelta = y - lParams.topMargin;
						break;
					}
					case MotionEvent.ACTION_MOVE: {

						FrameLayout.LayoutParams layoutParams =
								(FrameLayout.LayoutParams) binding.percent.getLayoutParams();
						FrameLayout.LayoutParams layoutParams2 =
								(FrameLayout.LayoutParams) binding.trainPercent.getLayoutParams();

						layoutParams.topMargin = y - yDelta;
						layoutParams2.topMargin = y - yDelta - 10;

						if (layoutParams.topMargin <= binding.root.getHeight() / 2 && layoutParams.topMargin > 0) {
							binding.percent.setLayoutParams(layoutParams);
							binding.trainPercent.setLayoutParams(layoutParams2);
							int xx = (layoutParams.topMargin * 100)/ (binding.root.getHeight() / 2);
							binding.trainPercent.setText(percent(xx/2) + "%");
						}

						break;
					}
				}
				binding.root.invalidate();
				return true;
			}
		});
		reqnet = new RequestNetwork(this);
		reqnet_fast = new RequestNetwork(this);
		final int[] i = {0};

		_reqnet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("how_many_reps") && !_response.equals("")) {
					if(done_reps < Double.parseDouble(_response)) {
						done_reps = Double.parseDouble(_response);

						if (done_reps > 0) {
							LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) trains[(int) done_reps - 1].getLayoutParams();
							trains[(int) done_reps - 1].setImageDrawable(getResources().getDrawable(R.drawable.vector_red_fill));
							layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

							handler.removeCallbacks(runnable);
							start_auto_weight = false;

							int percent = 100 - ((secs / 1000)*10);
							secs = 0;
							int percent_margin = (100 - percent) + 10;
							power += percent;
							if(max_range > 20)
								max_range = 20;
							range += (max_range*5);
							max_range = 0;
							layoutParams.topMargin = percent_margin;
							trains[(int) done_reps - 1].setLayoutParams(layoutParams);
							if (mode) {
								mode = false;
								binding.logoImg.setBackground(getResources().getDrawable(R.drawable.ic_w));
								binding.logoImg.getLayoutParams().width = (int) getDip(60);
							}
							lume += resist;
						}
					}

				}
				if (!done_flag) {
					binding.reps1.setText(String.valueOf((int)done_reps));
					if (done_reps < train_reps) {

					}
					else {
						if (mode) {
							mode = false;
							binding.logoImg.setBackground(getResources().getDrawable(R.drawable.ic_w));
							binding.logoImg.getLayoutParams().width = (int) getDip(60);
							secs = 0;
						}
						done_flag = true;
						_weight_off();
						sharedpref.edit().putString("done_reps", String.valueOf((long)(done_reps))).commit();
						sharedpref.edit().putString("done_secs", String.valueOf((long)(train_time))).commit();
						sharedpref.edit().putString("done_resist", String.valueOf((long)((Double.parseDouble(binding.reps1.getText().toString()))))).commit();


						sharedpref.edit().putInt("lume", lume).apply();
						sharedpref.edit().putInt("power", power/(int)done_reps).apply();
						sharedpref.edit().putInt("range", range/(int)done_reps).apply();
						intent0.setClass(getApplicationContext(), WorkSumActivity.class);
						tw_reps.cancel();
						tw_range.cancel();
						delay = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										startActivity(intent0);
										finish();
									}
								});
							}
						};
						_timer.schedule(delay, (int)(2000));
					}
				}
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

			}
		};

		_reqnet_fast_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("range_percent") && !_response.equals("")) {
					intrange = Double.parseDouble(_response);
					if(intrange > 21)
						binding.weight.setProgress((float) 21);
					else
						binding.weight.setProgress((float) intrange);
					if(intrange > 1 && !start_auto_weight)
					{
						handler.post(runnable);
						start_auto_weight = true;
					}

					if(intrange < 17)
					{
						binding.weight.setPointerColor(Color.parseColor("#FFFFFF"));
						binding.weight.setCircleProgressColor(Color.parseColor("#FFFFFF"));
					}
					else
					{
						binding.weight.setPointerColor(Color.parseColor("#DF2323"));
						binding.weight.setCircleProgressColor(Color.parseColor("#DF2323"));
					}
				}
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

			}
		};

		tw_reps = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@SuppressLint("SetTextI18n")
					@Override
					public void run() {
						train_time++;
						if(train_time < 60) {
							if(train_time < 10)
								binding.timeTxt.setText(":0" + (int) train_time);
							else
								binding.timeTxt.setText(":" + (int) train_time);
						}
						else
						{
							int minute, seconds;
							minute = (int) train_time / 60;
							seconds = (int) train_time - (minute * 60);
							String time = "";

							if(minute < 10)
								time += "0" + minute + ":";
							else
								time += minute + ":";

							if(seconds < 10)
								time += "0" + seconds;
							else
								time += String.valueOf(seconds);
							binding.timeTxt.setText(time);
							if(minute == 5)
							{
								done_flag = true;
								sharedpref.edit().putString("done_reps", String.valueOf((long)(done_reps))).commit();
								sharedpref.edit().putString("done_secs", String.valueOf((long)(train_time))).commit();
								sharedpref.edit().putString("done_resist", String.valueOf((long)((Double.parseDouble(binding.reps1.getText().toString()))))).commit();
								tw_reps.cancel();
								tw_range.cancel();
								RepsTrainActivity.super.onBackPressed();
							}
						}

						reqnet.startRequestNetwork(RequestNetworkController.GET, "http://".concat(sharedpref.getString("ip", "")).concat("/view_reps.php"), "how_many_reps", _reqnet_request_listener);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tw_reps, (int)(1000), (int)(1000));

		tw_range = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						reqnet_fast.startRequestNetwork(RequestNetworkController.GET, "http://".concat(sharedpref.getString("ip", "")).concat("/view_ran.php"), "range_percent", _reqnet_fast_request_listener);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tw_range, (int)(100), (int)(200));

		binding.cancel.setOnClickListener(v ->
		{
			handler.removeCallbacks(runnable);
			tw_reps.cancel();
			tw_range.cancel();
			_weight_off();
			intent0.setClass(getApplicationContext(), ChoosetypeActivity.class);
			startActivity(intent0);
			finish();
		});

		_set_resist(resist);

		//initialize(_savedInstanceState);
		//initializeLogic();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	private int percent(int percent)
	{
		switch (percent)
		{
			case 0: return 100;
			case 1: return 99;
			case 2: return 98;
			case 3: return 97;
			case 4: return 96;
			case 5: return 95;
			case 6: return 94;
			case 7: return 93;
			case 8: return 92;
			case 9: return 91;
			case 10: return 90;
			case 11: return 89;
			case 12: return 88;
			case 13: return 87;
			case 14: return 86;
			case 15: return 85;
			case 16: return 84;
			case 17: return 83;
			case 18: return 82;
			case 19: return 81;
			case 20: return 80;
			case 21: return 79;
			case 22: return 78;
			case 23: return 77;
			case 24: return 76;
			case 25: return 75;
			case 26: return 74;
			case 27: return 73;
			case 28: return 72;
			case 29: return 71;
			case 30: return 70;
			case 31: return 69;
			case 32: return 68;
			case 33: return 67;
			case 34: return 66;
			case 35: return 65;
			case 36: return 64;
			case 37: return 63;
			case 38: return 62;
			case 39: return 61;
			case 40: return 60;
			case 41: return 59;
			case 42: return 58;
			case 43: return 57;
			case 44: return 56;
			case 45: return 55;
			case 46: return 54;
			case 47: return 53;
			case 48: return 52;
			case 49: return 51;
			case 50: return 50;



		}

		return 0;
	}
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		txt_title = (TextView) findViewById(R.id.txt_title);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		s1 = (TextView) findViewById(R.id.s1);
		s2 = (TextView) findViewById(R.id.s2);
		s3 = (TextView) findViewById(R.id.s3);
		s4 = (TextView) findViewById(R.id.s4);
		txt_time = (TextView) findViewById(R.id.txt_time);
		imageview1 = (ImageView) findViewById(R.id.intro);
		textview12 = (TextView) findViewById(R.id.textview12);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview11 = (TextView) findViewById(R.id.textview11);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		lin_resist = (LinearLayout) findViewById(R.id.lin_resist);
		d1 = (TextView) findViewById(R.id.d1);
		d2 = (TextView) findViewById(R.id.d2);
		textview1 = (TextView) findViewById(R.id.textview1);
		d3 = (TextView) findViewById(R.id.d3);
		d4 = (TextView) findViewById(R.id.d4);
		txt_reps = (TextView) findViewById(R.id.txt_reps);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		reps_minus = (TextView) findViewById(R.id.reps_minus);
		rd1 = (TextView) findViewById(R.id.rd1);
		rd2 = (TextView) findViewById(R.id.rd2);
		reps_plus = (TextView) findViewById(R.id.reps_plus);
		textview10 = (TextView) findViewById(R.id.textview10);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		bar1 = (LinearLayout) findViewById(R.id.bar1);
		bar2 = (LinearLayout) findViewById(R.id.bar2);
		bar3 = (LinearLayout) findViewById(R.id.bar3);
		bar4 = (LinearLayout) findViewById(R.id.bar4);
		bar5 = (LinearLayout) findViewById(R.id.bar5);
		bar6 = (LinearLayout) findViewById(R.id.bar6);
		bar7 = (LinearLayout) findViewById(R.id.bar7);
		bar8 = (LinearLayout) findViewById(R.id.bar8);
		bar9 = (LinearLayout) findViewById(R.id.bar9);
		bar10 = (LinearLayout) findViewById(R.id.bar10);
		bar11 = (LinearLayout) findViewById(R.id.bar11);
		bar12 = (LinearLayout) findViewById(R.id.bar12);
		bar13 = (LinearLayout) findViewById(R.id.bar13);
		bar14 = (LinearLayout) findViewById(R.id.bar14);
		bar15 = (LinearLayout) findViewById(R.id.bar15);
		bar16 = (LinearLayout) findViewById(R.id.bar16);
		bar17 = (LinearLayout) findViewById(R.id.bar17);
		bar18 = (LinearLayout) findViewById(R.id.bar18);
		bar19 = (LinearLayout) findViewById(R.id.bar19);
		bar20 = (LinearLayout) findViewById(R.id.bar20);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		dialogue = new AlertDialog.Builder(this);
		reqnet = new RequestNetwork(this);
		reqnet_fast = new RequestNetwork(this);
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialogue.setTitle("ביטול אימון");
				dialogue.setPositiveButton("כן", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						tw_reps.cancel();
						tw_range.cancel();
						_weight_off();
						intent0.setClass(getApplicationContext(), ChoosetypeActivity.class);
						startActivity(intent0);
						finish();
					}
				});
				dialogue.setNegativeButton("לא", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialogue.setMessage("האם לבטל את האימון?");
				dialogue.create().show();
			}
		});
		
		txt_reps.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (false) {
					if (!done_flag) {
						done_reps++;
						d1.setText(String.valueOf((long)(done_reps / 10)));
						d2.setText(String.valueOf((long)(done_reps - (Double.parseDouble(d1.getText().toString()) * 10))));
						if (done_reps < train_reps) {
							
						}
						else {
							done_flag = true;
							sharedpref.edit().putString("done_reps", String.valueOf((long)(done_reps))).commit();
							sharedpref.edit().putString("done_secs", String.valueOf((long)(train_time))).commit();
							sharedpref.edit().putString("done_resist", String.valueOf((long)((Double.parseDouble(rd1.getText().toString()) * 10) + Double.parseDouble(rd2.getText().toString())))).commit();
							intent0.setClass(getApplicationContext(), WorkSumActivity.class);
							tw_reps.cancel();
							delay = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											startActivity(intent0);
											finish();
										}
									});
								}
							};
							_timer.schedule(delay, (int)(2000));
						}
					}
				}
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
					_set_resist_during_set(resist);
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
					_set_resist_during_set(resist);
				}
			}
		});
		
		_reqnet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("how_many_reps") && !_response.equals("")) {
					done_reps = Double.parseDouble(_response);
				}
				if (!done_flag) {
					d1.setText(String.valueOf((long)(done_reps / 10)));
					d2.setText(String.valueOf((long)(done_reps - (Double.parseDouble(d1.getText().toString()) * 10))));
					if (done_reps < train_reps) {
						
					}
					else {
						done_flag = true;
						_weight_off();
						sharedpref.edit().putString("done_reps", String.valueOf((long)(done_reps))).commit();
						sharedpref.edit().putString("done_secs", String.valueOf((long)(train_time))).commit();
						sharedpref.edit().putString("done_resist", String.valueOf((long)((Double.parseDouble(rd1.getText().toString()) * 10) + Double.parseDouble(rd2.getText().toString())))).commit();
						intent0.setClass(getApplicationContext(), WorkSumActivity.class);
						tw_reps.cancel();
						tw_range.cancel();
						delay = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										startActivity(intent0);
										finish();
									}
								});
							}
						};
						_timer.schedule(delay, (int)(2000));
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_reqnet_fast_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("range_percent") && !_response.equals("")) {
					intrange = Double.parseDouble(_response);
					_bars(intrange);
				}
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
		intrange = 0;
		done_flag = false;
		linear12.setVisibility(View.GONE);
		done_reps = 0;
		train_reps = Double.parseDouble(sharedpref.getString("train_reps", ""));
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
		train_time = 0;
		d3.setText(String.valueOf((long)(Double.parseDouble(sharedpref.getString("train_reps", "")) / 10)));
		d4.setText(String.valueOf((long)(Double.parseDouble(sharedpref.getString("train_reps", "")) - (Double.parseDouble(d3.getText().toString()) * 10))));
		resist = Double.parseDouble(sharedpref.getString("train_resist", ""));
		_set_resist(resist);
		rd1.setText(String.valueOf((long)(resist / 10)));
		rd2.setText(String.valueOf((long)(resist - (Double.parseDouble(rd1.getText().toString()) * 10))));
		min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		tw_reps = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						train_time++;
						_timeformat(train_time);
						s1.setText(timeformatlist.get((int)(0)));
						s2.setText(timeformatlist.get((int)(1)));
						s3.setText(timeformatlist.get((int)(2)));
						s4.setText(String.valueOf((long)(train_time)).substring((int)(String.valueOf((long)(train_time)).length() - 1), (int)(String.valueOf((long)(train_time)).length())));
						txt_time.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));
						reqnet.startRequestNetwork(RequestNetworkController.GET, "http://".concat(sharedpref.getString("ip", "")).concat("/view_reps.php"), "how_many_reps", _reqnet_request_listener);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tw_reps, (int)(1000), (int)(1000));
		tw_range = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						reqnet_fast.startRequestNetwork(RequestNetworkController.GET, "http://".concat(sharedpref.getString("ip", "")).concat("/view_ran.php"), "range_percent", _reqnet_fast_request_listener);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tw_range, (int)(100), (int)(200));

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
	
	
	public void _set_resist (final double _weight) {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=")).concat(String.valueOf((long)((_weight * Double.parseDouble(sharedpref.getString("res_mul", ""))) / Double.parseDouble(sharedpref.getString("res_div", ""))))), "weight", _reqnet_request_listener);
	}
	
	
	public void _weight_off () {
		if (false) {
			reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=off")), "o", _reqnet_request_listener);
		}
		_set_resist(Double.parseDouble(sharedpref.getString("weight_off", "")));
	}
	
	
	public void _set_resist_during_set (final double _resist) {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=2&kg=")).concat(String.valueOf((long)((_resist * Double.parseDouble(sharedpref.getString("res_mul", ""))) / Double.parseDouble(sharedpref.getString("res_div", ""))))), "weight", _reqnet_request_listener);
	}
	
	
	public void _bars (final double _myrange) {
		if (_myrange > 0) {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 1) {
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 2) {
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 3) {
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 4) {
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 5) {
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 6) {
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 7) {
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 8) {
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 9) {
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 10) {
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 11) {
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 12) {
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 13) {
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 14) {
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFFFF0000));
		}
		else {
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 15) {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
		}
		else {
			bar16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 16) {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar17.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
		}
		else {
			bar17.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 17) {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar17.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar18.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
		}
		else {
			bar18.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 18) {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar17.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar18.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar19.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
		}
		else {
			bar19.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
		}
		if (_myrange > 19) {
			bar1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar14.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar17.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar18.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar19.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
			bar20.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, 0xFF5CE66C));
		}
		else {
			bar20.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFFFFFF, Color.TRANSPARENT));
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
