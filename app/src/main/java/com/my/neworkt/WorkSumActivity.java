package com.my.neworkt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.util.*;

import java.util.*;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.graphics.Typeface;

import androidx.appcompat.app.AppCompatActivity;

import com.my.neworkt.databinding.SecsTrain2Binding;
import com.my.neworkt.databinding.WorkSumBinding;

import me.tankery.lib.circularseekbar.CircularSeekBar;


public class WorkSumActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private String strmnts = "";
	private String strsecs = "";
	private String strscspa = "";
	private double done_time = 0;
	private double resist = 0;
	private double min_resist = 0;
	private double max_resist = 0;
	
	private ArrayList<String> timeformatlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear7;
	private LinearLayout linear15;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView txt_title;
	private LinearLayout linear12;
	private Button btn_OK;
	private TextView s1;
	private TextView s2;
	private TextView s3;
	private TextView s4;
	private TextView txt_time;
	private ImageView imageview1;
	private TextView textview11;
	private TextView d1;
	private TextView d2;
	private TextView textview12;
	private LinearLayout lin_resist;
	private TextView txt_reps;
	private TextView rd1;
	private TextView rd2;
	private TextView textview10;
	private LinearLayout linear13;
	private TextView txt_resist;
	private TextView textview13;
	
	private SharedPreferences sharedpref;
	private TimerTask timer0;
	WorkSumBinding binding;
	Handler handler = new Handler();
	Handler handler2 = new Handler();
	Handler handler3 = new Handler();
	Handler handler4 = new Handler();
	Handler handler5 = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			Intent intent0 = new Intent();
			intent0.setClass(getApplicationContext(), ChoosetypeActivity.class);
			startActivity(intent0);
			finish();
		}
	};

	int field = 0;
	int main_round = -1;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = WorkSumBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		setCircler(binding.lumeRound);
		setCircler(binding.rangeRound);
		setCircler(binding.powerRound);
		setCircler(binding.mainRound);

		binding.imgLogo.animate().scaleX(1.5f).setDuration(400).start();
		binding.imgLogo.animate().scaleY(1.5f).setDuration(400).start();
		handler.postDelayed(new Runnable() {
			@SuppressLint("UseCompatLoadingForDrawables")
			@Override
			public void run() {
				binding.imgLogo.animate().scaleX(1).setDuration(400).start();
				binding.imgLogo.animate().scaleY(1).setDuration(400).start();
			}
		}, 400);

		handler2.postDelayed(new Runnable() {
			@SuppressLint("UseCompatLoadingForDrawables")
			@Override
			public void run() {
				if(field == 0) {
					binding.imgLume.setVisibility(View.VISIBLE);
					binding.lumeScore.setVisibility(View.VISIBLE);
					binding.lumeTxt1.setVisibility(View.VISIBLE);
					binding.lumeTxt2.setVisibility(View.VISIBLE);
				}
				else if(field == 1) {
					binding.imgPower.setVisibility(View.VISIBLE);
					binding.powerScore.setVisibility(View.VISIBLE);
					binding.powerTxt2.setVisibility(View.VISIBLE);
					binding.powerTxt1.setVisibility(View.VISIBLE);
				}
				else if(field == 2) {
					binding.imgRange.setVisibility(View.VISIBLE);
					binding.rangeScore.setVisibility(View.VISIBLE);
					binding.rangeTxt1.setVisibility(View.VISIBLE);
					binding.rangeTxt2.setVisibility(View.VISIBLE);
				}



				if(field < 3) {
					field++;
					handler.postDelayed(this, 200);
				}
			}
		}, 200);

		handler3.postDelayed(new Runnable() {
			@SuppressLint("UseCompatLoadingForDrawables")
			@Override
			public void run() {
				if(main_round == -1)
				{
					binding.imgLogo2.setBackground(getResources().getDrawable(R.drawable.round_2_sum));
				}

				if(main_round >= -50)
				{
					binding.mainRound.setProgress(main_round);
					main_round--;
					handler3.postDelayed(this, 1);
				}
				else
				{
					main_round = -1;
					binding.rangeRound.setVisibility(View.VISIBLE);
					binding.lumeRound.setVisibility(View.VISIBLE);
					binding.powerRound.setVisibility(View.VISIBLE);
					int lume = sharedpref.getInt("lume", 0);
					int power = sharedpref.getInt("power", 0);
					int range = sharedpref.getInt("range", 0);
					final int[] i = {0, 0, 0};
					handler4.postDelayed(new Runnable() {
						@Override
						public void run() {
							boolean f = false;
							if (lume >= i[0]) {
								binding.lumeScore.setText(String.valueOf(i[0]));
								i[0]+= devide(lume);
								f = true;
							}
							if (power >= i[1]) {
								binding.powerScore.setText(String.valueOf(i[1]));
								f = true;
								i[1]+= devide(power);
							}
							if (range >= i[2]) {
								binding.rangeScore.setText(String.valueOf(i[2]));
								f = true;
								i[2]+= devide(range);
							}



							if(f)
								handler4.postDelayed(this, 1);

						}
					}, 2);
					handler3.post(new Runnable() {
						@Override
						public void run() {

							if(main_round >= -50)
							{
								binding.rangeRound.setProgress(main_round);
								binding.lumeRound.setProgress(main_round);
								binding.powerRound.setProgress(main_round);
								main_round--;
								handler3.postDelayed(this, 2);
							}


						}
					});
				}
			}
		}, 800);

		binding.cancel.setOnClickListener(v ->
		{
			Intent intent0 = new Intent();
			intent0.setClass(getApplicationContext(), ChoosetypeActivity.class);
			startActivity(intent0);
			finish();
			handler5.removeCallbacks(runnable);
		});

		handler5.postDelayed(runnable, 10000);
//		initialize(_savedInstanceState);
//		initializeLogic();
	}

	private int devide(int num)
	{
		if(num %9 == 0)
			return 9;
		else if(num %8 == 0)
			return 8;
		else if(num %7 == 0)
			return 7;
		else if(num %6 == 0)
			return 6;
		else if(num %5 == 0)
			return 5;
		else if(num %4 == 0)
			return 4;
		else if(num %3 == 0)
			return 3;
		else if(num %2 == 0)
			return 2;
		else
			return 1;
	}


	private void setCircler(CircularSeekBar circularSeekBar)
	{
		circularSeekBar.setCircleStrokeWidth(3);
		circularSeekBar.setCircleProgressColor(Color.parseColor("#DF2323"));
		circularSeekBar.setNegativeEnabled(true);
		circularSeekBar.setEnabled(false);
		circularSeekBar.setMax(50);
		//circularSeekBar.setProgress(-100);
		circularSeekBar.setPointerStrokeWidth(1);
	}
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		txt_title = (TextView) findViewById(R.id.txt_title);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		s1 = (TextView) findViewById(R.id.s1);
		s2 = (TextView) findViewById(R.id.s2);
		s3 = (TextView) findViewById(R.id.s3);
		s4 = (TextView) findViewById(R.id.s4);
		txt_time = (TextView) findViewById(R.id.txt_time);
		imageview1 = (ImageView) findViewById(R.id.intro);
		textview11 = (TextView) findViewById(R.id.textview11);
		d1 = (TextView) findViewById(R.id.d1);
		d2 = (TextView) findViewById(R.id.d2);
		textview12 = (TextView) findViewById(R.id.textview12);
		lin_resist = (LinearLayout) findViewById(R.id.lin_resist);
		txt_reps = (TextView) findViewById(R.id.txt_reps);
		rd1 = (TextView) findViewById(R.id.rd1);
		rd2 = (TextView) findViewById(R.id.rd2);
		textview10 = (TextView) findViewById(R.id.textview10);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		textview13 = (TextView) findViewById(R.id.textview13);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		
		linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (false) {
					finish();
				}
			}
		});
	}
	
	private void initializeLogic() {
		_always_on();
		textview13.setVisibility(View.INVISIBLE);
		_roundgg(txt_resist);
		_roundgg(txt_reps);
		_roundg(d1);
		_roundg(d2);
		_roundg(rd1);
		_roundg(rd2);
		d1.setText(String.valueOf((long)(Double.parseDouble(sharedpref.getString("done_reps", "")) / 10)));
		d2.setText(String.valueOf((long)(Double.parseDouble(sharedpref.getString("done_reps", "")) - (Double.parseDouble(d1.getText().toString()) * 10))));
		resist = Double.parseDouble(sharedpref.getString("done_resist", ""));
		rd1.setText(String.valueOf((long)(resist / 10)));
		rd2.setText(String.valueOf((long)(resist - (Double.parseDouble(rd1.getText().toString()) * 10))));
		min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		done_time = Double.parseDouble(sharedpref.getString("done_secs", ""));
		_timeformat(done_time);
		s1.setText(timeformatlist.get((int)(0)));
		s2.setText(timeformatlist.get((int)(1)));
		s3.setText(timeformatlist.get((int)(2)));
		s4.setText(String.valueOf((long)(done_time)).substring((int)(String.valueOf((long)(done_time)).length() - 1), (int)(String.valueOf((long)(done_time)).length())));
		txt_time.setText(s1.getText().toString().concat(s2.getText().toString().concat(":".concat(s3.getText().toString().concat(s4.getText().toString())))));

		if (sharedpref.getString("user", "").equals("yarin")) {
			textview13.setVisibility(View.VISIBLE);
		}
		sharedpref.edit().putString("user", "free").commit();
		timer0 = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						finish();
					}
				});
			}
		};
		_timer.schedule(timer0, (int)(5000));
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
		gd.setColor(Color.parseColor("#adadb8"));
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
