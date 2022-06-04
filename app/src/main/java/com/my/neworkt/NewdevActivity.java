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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class NewdevActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear7;
	private LinearLayout linear15;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear16;
	private TextView txt_title;
	private ImageView imageview1;
	private LinearLayout linear12;
	private Button btn_OK;
	private TextView s1;
	private TextView s2;
	private TextView s3;
	private TextView s4;
	private TextView textview11;
	private TextView textview12;
	private TextView textview13;
	private LinearLayout linear13;
	private EditText edittext1;
	
	private SharedPreferences sharedpref;
	private Intent intent0 = new Intent();
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newdev);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		txt_title = (TextView) findViewById(R.id.txt_title);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		s1 = (TextView) findViewById(R.id.s1);
		s2 = (TextView) findViewById(R.id.s2);
		s3 = (TextView) findViewById(R.id.s3);
		s4 = (TextView) findViewById(R.id.s4);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview12 = (TextView) findViewById(R.id.textview12);
		textview13 = (TextView) findViewById(R.id.textview13);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				imageview1.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								imageview1.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
								finish();
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains("\n")) {
					textview11.setText(_charSeq.trim());
					if (_charSeq.trim().equals("admin1")) {
						intent0.setClass(getApplicationContext(), Ident0Activity.class);
						startActivity(intent0);
						finish();
					}
					else {
						intent0.setClass(getApplicationContext(), N1607aActivity.class);
						intent0.putExtra("theuser", _charSeq.trim());
						sharedpref.edit().putString("login_id", _charSeq.trim()).commit();
						startActivity(intent0);
						finish();
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		_always_on();
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview13.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		if (sharedpref.getString("train_init", "").equals("")) {
			sharedpref.edit().putString("train_type", "reps").commit();
			sharedpref.edit().putString("train_reps", "15").commit();
			sharedpref.edit().putString("train_resist", "40").commit();
			sharedpref.edit().putString("train_secs", "30").commit();
			sharedpref.edit().putString("train_init", "true").commit();
		}
		if (sharedpref.getString("setting_exist", "").equals("")) {
			sharedpref.edit().putString("setting_min_resist", "5").commit();
			sharedpref.edit().putString("setting_max_resist", "99").commit();
			sharedpref.edit().putString("setting_min_reps", "1").commit();
			sharedpref.edit().putString("setting_max_reps", "30").commit();
			sharedpref.edit().putString("setting_min_secs", "10").commit();
			sharedpref.edit().putString("setting_max_secs", "180").commit();
		}
		sharedpref.edit().putString("done_reps", "0").commit();
		sharedpref.edit().putString("done_secs", "0").commit();
		sharedpref.edit().putString("done_resist", "0").commit();
		sharedpref.edit().putString("train_type", "reps").commit();
		sharedpref.edit().putString("train_reps", "5").commit();
		sharedpref.edit().putString("train_resist", "36").commit();
		sharedpref.edit().putString("train_secs", "25").commit();
		sharedpref.edit().putString("train_init", "true").commit();
		sharedpref.edit().putString("user", "yarin").commit();
		if (0 == 1) {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							intent0.setClass(getApplicationContext(), ChoosetypeActivity.class);
							startActivity(intent0);
							finish();
						}
					});
				}
			};
			_timer.schedule(timer, (int)(3000));
		}
		((EditText)edittext1).selectAll();
		edittext1.requestFocus();
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