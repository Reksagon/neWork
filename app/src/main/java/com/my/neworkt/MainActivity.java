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
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import com.google.gson.Gson;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class MainActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> dev = new HashMap<>();
	private String default_en = "";
	
	private ArrayList<String> devs = new ArrayList<>();
	
	private LinearLayout linear2;
	private ImageView imageview1;
	
	private Intent intent0 = new Intent();
	private SharedPreferences sharedpref;
	private TimerTask timer;
	private RequestNetwork reqnet;
	private RequestNetwork.RequestListener _reqnet_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		reqnet = new RequestNetwork(this);
		
		imageview1.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				
				return true;
				}
			 });
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_reqnet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("test")) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
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
		if (sharedpref.getString("train_init", "").equals("")) {
			sharedpref.edit().putString("train_type", "reps").commit();
			sharedpref.edit().putString("train_reps", "15").commit();
			sharedpref.edit().putString("train_resist", "40").commit();
			sharedpref.edit().putString("train_secs", "30").commit();
			sharedpref.edit().putString("train_init", "true").commit();
		}
		if (sharedpref.getString("setting_exist", "").equals("")) {
			sharedpref.edit().putString("setting_max_resist", "99").commit();
			sharedpref.edit().putString("setting_min_reps", "1").commit();
			sharedpref.edit().putString("setting_max_secs", "180").commit();
			sharedpref.edit().putString("ip", "192.168.1.153").commit();
			sharedpref.edit().putString("res_mul", "29").commit();
			sharedpref.edit().putString("res_div", "20").commit();
			sharedpref.edit().putString("device_name", "מכשיר קשוח").commit();
			sharedpref.edit().putString("setting_exist", "true").commit();
			sharedpref.edit().putString("setting_max_reps", "30").commit();
			sharedpref.edit().putString("setting_min_secs", "10").commit();
			sharedpref.edit().putString("setting_min_resist", "15").commit();
		}
		if (sharedpref.getString("weight_off", "").equals("")) {
			sharedpref.edit().putString("weight_off", "10").commit();
		}
		if (sharedpref.getString("weight_sd", "").equals("")) {
			sharedpref.edit().putString("weight_sd", "5").commit();
			sharedpref.edit().putString("start_sd", "5").commit();
			sharedpref.edit().putString("end_sd", "0").commit();
		}
		sharedpref.edit().putString("done_reps", "0").commit();
		sharedpref.edit().putString("done_secs", "0").commit();
		sharedpref.edit().putString("done_resist", "0").commit();
		sharedpref.edit().putString("user", "free").commit();
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
		if (sharedpref.getString("enables_exist", "").equals("")) {
			_create_train_devices_list();
			sharedpref.edit().putString("devices", new Gson().toJson(dev)).commit();
			sharedpref.edit().putString("enables_exist", "true").commit();
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
	
	@Override
	public void onBackPressed() {
		
	}
	public void _buttg (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)6;
		int clr[] = new int[]{ Color.parseColor("gray"), Color.parseColor("black") };
		gd.setColors(clr);
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, 0,0,0,0});
		gd.setStroke(6, Color.parseColor("red"));
		gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _create_train_devices_list () {
		dev.put("1", "לחיצת רגליים");
		dev.put("1_1", "לחיצת רגליים, בישיבה");
		dev.put("2", "פשיטת/כפיפת ברכיים בישיבה");
		dev.put("2_1", "פשיטת ברכיים, בישיבה");
		dev.put("2_2", "כפיפת ברכיים, בישיבה");
		dev.put("3", "עליות תאומים");
		dev.put("3_1", "עליות תאומים, בעמידה");
		dev.put("4", "עליות סולאוס");
		dev.put("4_1", "עליות תאומים, בישיבה");
		dev.put("5", "כפיפת ברכיים בשכיבה על הבטן");
		dev.put("5_1", "כפיפת ברכיים, שכיבה על הבטן");
		dev.put("6", "קירוב/הרחקת ירך בישיבה");
		dev.put("6_1", "קירוב ירך, בישיבה");
		dev.put("6_2", "הרחקת ירך, בישיבה");
		dev.put("7", "פשיטת ירך בעמידה");
		dev.put("7_1", "פשיטת ירך, בעמידה - רגל ימין");
		dev.put("7_2", "פשיטת ירך, בעמידה - רגל שמאל");
		dev.put("8", "פולי עליון/תחתון");
		dev.put("8_1", "פולי עליון, בישיבה - אחיזה רחבה");
		dev.put("8_2", "פולי עליון, בישיבה - אחיזה מקבילה");
		dev.put("8_3", "פולי עליון, בישיבה - אחיזה הפוכה");
		dev.put("8_4", "פולי עליון, בישיבה - רוחב כתפיים");
		dev.put("8_5", "פולי עליון, בישיבה - מאחורי הגב");
		dev.put("8_6", "פולי תחתון, בישיבה - אחיזה מקבילה");
		dev.put("9", "חתירה רגילה/אופקית בישיבה");
		dev.put("9_1", "חתירה, בישיבה - אחיזה מקבילה");
		dev.put("9_2", "חתירה אופקית, בישיבה - אחיזה רחבה");
		dev.put("10", "פשיטת גב");
		dev.put("10_1", "פשיטת גב, בישיבה");
		dev.put("11", "פרפר בישיבה");
		dev.put("11_1", "פרפר, בישיבה");
		dev.put("12", "לחיצת חזה בישיבה/בשיפוע");
		dev.put("12_1", "לחיצת חזה, בישיבה - אחיזה רחבה");
		dev.put("12_2", "לחיצת חזה, בישיבה - אחיזה מקבילה");
		dev.put("12_3", "לחיצת חזה, בשיפוע חיובי - אחיזה רחבה");
		dev.put("12_4", "לחיצת חזה, בשיפוע חיובי - אחיזה מקבילה");
		dev.put("13", "קירוב/הרחקה אופקית בישיבה");
		dev.put("13_1", "קירוב אופקי, בישיבה");
		dev.put("13_2", "הרחקה אופקית, בישיבה");
		dev.put("14", "הרחקת כתפיים");
		dev.put("14_1", "הרחקת כתפיים, בישיבה");
		dev.put("15", "לחיצת כתפיים בישיבה");
		dev.put("15_1", "לחיצת כתפיים, בישיבה - אחיזה מקבילה");
		dev.put("15_2", "לחיצת כתפיים, בישיבה - אחיזה רחבה");
		dev.put("16", "כפיפות בטן בישיבה");
		dev.put("16_1", "כפיפות בטן, בישיבה");
		dev.put("17", "אלכסונים");
		dev.put("17_1", "אלכסונים, בישיבה - צד ימין");
		dev.put("17_2", "אלכסונים, בישיבה - צד שמאל");
		dev.put("18", "פשיטה/כפיפה בכיסא כומר");
		dev.put("18_1", "כפיפת מרפקים בכיסא כומר");
		dev.put("18_2", "כפיפת מרפקים בכיסא כומר - אחיזת פטישים");
		dev.put("18_3", "כפיפת מרפקים בכיסא כומר - אחיזה הפוכה");
		dev.put("18_4", "פשיטת מרפקים בכיסא כומר - אחיזת פטישים");
		dev.put("19", "כפיפת מרפקים בישיבה");
		dev.put("19_1", "כפיפת מרפקים, בישיבה");
		dev.put("20", "פשיטת מרפקים בישיבה");
		dev.put("20_1", "פשיטת מרפקים, בישיבה - מאחורי הראש");
		dev.put("21", "מקבילים בישיבה");
		dev.put("21_1", "מקבילים, בישיבה");
		dev.put("1-1", "רגליים");
		dev.put("2-1", "רגליים");
		dev.put("2-2", "רגליים");
		dev.put("3-1", "רגליים");
		dev.put("4-1", "רגליים");
		dev.put("5-1", "רגליים");
		dev.put("6-1", "רגליים");
		dev.put("6-2", "רגליים");
		dev.put("7-1", "רגליים");
		dev.put("7-2", "רגליים");
		dev.put("8-1", "גב");
		dev.put("8-2", "גב");
		dev.put("8-3", "גב");
		dev.put("8-4", "גב");
		dev.put("8-5", "גב");
		dev.put("8-6", "גב");
		dev.put("9-1", "גב");
		dev.put("10-1", "גב");
		dev.put("11-1", "חזה");
		dev.put("12-1", "חזה");
		dev.put("12-2", "חזה");
		dev.put("12-3", "חזה");
		dev.put("12-4", "חזה");
		dev.put("13-1", "חזה");
		dev.put("14-1", "כתפיים");
		dev.put("9-2", "כתפיים");
		dev.put("15-1", "כתפיים");
		dev.put("15-2", "כתפיים");
		dev.put("13-2", "כתפיים");
		dev.put("16-1", "בטן");
		dev.put("17-1", "בטן");
		dev.put("17-2", "בטן");
		dev.put("18-1", "יד קדמית");
		dev.put("18-2", "יד קדמית");
		dev.put("18-3", "יד קדמית");
		dev.put("19-1", "יד קדמית");
		dev.put("20-1", "יד אחורית");
		dev.put("21-1", "יד אחורית");
		dev.put("18-4", "יד אחורית");
		default_en = "no";
		dev.put("1e1", default_en);
		dev.put("2e1", default_en);
		dev.put("2e2", default_en);
		dev.put("3e1", default_en);
		dev.put("4e1", default_en);
		dev.put("5e1", default_en);
		dev.put("6e1", default_en);
		dev.put("6e2", default_en);
		dev.put("7e1", default_en);
		dev.put("7e2", default_en);
		dev.put("8e1", default_en);
		dev.put("8e2", default_en);
		dev.put("8e3", default_en);
		dev.put("8e4", default_en);
		dev.put("8e5", default_en);
		dev.put("8e6", default_en);
		dev.put("9e1", default_en);
		dev.put("10e1", default_en);
		dev.put("11e1", default_en);
		dev.put("12e1", default_en);
		dev.put("12e2", default_en);
		dev.put("12e3", default_en);
		dev.put("12e4", default_en);
		dev.put("13e1", default_en);
		dev.put("14e1", default_en);
		dev.put("9e2", default_en);
		dev.put("15e1", default_en);
		dev.put("15e2", default_en);
		dev.put("13e2", default_en);
		dev.put("16e1", default_en);
		dev.put("17e1", default_en);
		dev.put("17e2", default_en);
		dev.put("18e1", default_en);
		dev.put("18e2", default_en);
		dev.put("18e3", default_en);
		dev.put("19e1", default_en);
		dev.put("20e1", default_en);
		dev.put("21e1", default_en);
		dev.put("18e4", default_en);
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