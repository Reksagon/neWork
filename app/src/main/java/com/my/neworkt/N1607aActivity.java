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
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;


public class N1607aActivity extends  Activity { 
	
	
	private double cnt = 0;
	private String key = "";
	private double device_id = 0;
	private HashMap<String, Object> dev = new HashMap<>();
	private String user_id = "";
	private String key_s = "";
	private String cal1 = "";
	private String cal2 = "";
	private String cal3 = "";
	private String cal4 = "";
	private String cal5 = "";
	private String cal6 = "";
	private String key_en = "";
	
	private ArrayList<String> lststr = new ArrayList<>();
	
	private LinearLayout linear11;
	private LinearLayout linear2;
	private LinearLayout linear16;
	private LinearLayout linear20;
	private ImageView imageview2;
	private LinearLayout linear19;
	private TextView title;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout t2;
	private LinearLayout t4;
	private LinearLayout t6;
	private LinearLayout ln2;
	private ImageView p2;
	private TextView n2;
	private TextView m2;
	private LinearLayout ln4;
	private ImageView p4;
	private TextView n4;
	private TextView m4;
	private LinearLayout ln6;
	private ImageView p6;
	private TextView n6;
	private TextView m6;
	private LinearLayout t1;
	private LinearLayout t3;
	private LinearLayout t5;
	private LinearLayout ln1;
	private ImageView p1;
	private TextView n1;
	private TextView m1;
	private LinearLayout ln3;
	private ImageView p3;
	private TextView n3;
	private TextView m3;
	private LinearLayout ln5;
	private ImageView p5;
	private TextView n5;
	private TextView m5;
	
	private SharedPreferences sharedpref;
	private Intent int0 = new Intent();
	private AlertDialog.Builder dial1;
	private Calendar cal = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.n1607a);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
			}
			else {
				initializeLogic();
			}
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		title = (TextView) findViewById(R.id.title);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		t2 = (LinearLayout) findViewById(R.id.t2);
		t4 = (LinearLayout) findViewById(R.id.t4);
		t6 = (LinearLayout) findViewById(R.id.t6);
		ln2 = (LinearLayout) findViewById(R.id.ln2);
		p2 = (ImageView) findViewById(R.id.p2);
		n2 = (TextView) findViewById(R.id.n2);
		m2 = (TextView) findViewById(R.id.m2);
		ln4 = (LinearLayout) findViewById(R.id.ln4);
		p4 = (ImageView) findViewById(R.id.p4);
		n4 = (TextView) findViewById(R.id.n4);
		m4 = (TextView) findViewById(R.id.m4);
		ln6 = (LinearLayout) findViewById(R.id.ln6);
		p6 = (ImageView) findViewById(R.id.p6);
		n6 = (TextView) findViewById(R.id.n6);
		m6 = (TextView) findViewById(R.id.m6);
		t1 = (LinearLayout) findViewById(R.id.t1);
		t3 = (LinearLayout) findViewById(R.id.t3);
		t5 = (LinearLayout) findViewById(R.id.t5);
		ln1 = (LinearLayout) findViewById(R.id.ln1);
		p1 = (ImageView) findViewById(R.id.p1);
		n1 = (TextView) findViewById(R.id.n1);
		m1 = (TextView) findViewById(R.id.m1);
		ln3 = (LinearLayout) findViewById(R.id.ln3);
		p3 = (ImageView) findViewById(R.id.p3);
		n3 = (TextView) findViewById(R.id.n3);
		m3 = (TextView) findViewById(R.id.m3);
		ln5 = (LinearLayout) findViewById(R.id.ln5);
		p5 = (ImageView) findViewById(R.id.p5);
		n5 = (TextView) findViewById(R.id.n5);
		m5 = (TextView) findViewById(R.id.m5);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		dial1 = new AlertDialog.Builder(this);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dial1.setTitle("יציאה מאימון אישי");
				dial1.setMessage("האם ברצונך לצאת מהאימון האישי?");
				dial1.setPositiveButton("כן", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dial1.setNegativeButton("לא", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dial1.create().show();
			}
		});
		
		t2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), Newdev3Activity.class);
				int0.putExtra("theuser", user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("2")))));
				int0.putExtra("user_id", user_id);
				sharedpref.edit().putString("exercise", n2.getText().toString()).commit();
				startActivity(int0);
				finish();
			}
		});
		
		t4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), Newdev3Activity.class);
				int0.putExtra("theuser", user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("4")))));
				int0.putExtra("user_id", user_id);
				sharedpref.edit().putString("exercise", n4.getText().toString()).commit();
				startActivity(int0);
				finish();
			}
		});
		
		t6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), Newdev3Activity.class);
				int0.putExtra("theuser", user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("6")))));
				int0.putExtra("user_id", user_id);
				sharedpref.edit().putString("exercise", n6.getText().toString()).commit();
				startActivity(int0);
				finish();
			}
		});
		
		t1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), Newdev3Activity.class);
				int0.putExtra("theuser", user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("1")))));
				int0.putExtra("user_id", user_id);
				sharedpref.edit().putString("exercise", n1.getText().toString()).commit();
				startActivity(int0);
				finish();
			}
		});
		
		t3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), Newdev3Activity.class);
				int0.putExtra("theuser", user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("3")))));
				int0.putExtra("user_id", user_id);
				sharedpref.edit().putString("exercise", n3.getText().toString()).commit();
				startActivity(int0);
				finish();
			}
		});
		
		t5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), Newdev3Activity.class);
				int0.putExtra("theuser", user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("5")))));
				int0.putExtra("user_id", user_id);
				sharedpref.edit().putString("exercise", n5.getText().toString()).commit();
				startActivity(int0);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		user_id = sharedpref.getString("login_id", "");
		t1.setVisibility(View.INVISIBLE);
		t2.setVisibility(View.INVISIBLE);
		t3.setVisibility(View.INVISIBLE);
		t4.setVisibility(View.INVISIBLE);
		t5.setVisibility(View.INVISIBLE);
		t6.setVisibility(View.INVISIBLE);
		dev = new Gson().fromJson(sharedpref.getString("devices", ""), new TypeToken<HashMap<String, Object>>(){}.getType());
		cnt = 1;
		for(int _repeat19 = 0; _repeat19 < (int)(21); _repeat19++) {
			key = String.valueOf((long)(cnt));
			if (dev.containsKey(key)) {
				lststr.add(dev.get(key).toString());
			}
			cnt++;
		}
		if (lststr.contains(sharedpref.getString("device_name", ""))) {
			device_id = lststr.indexOf(sharedpref.getString("device_name", ""));
		}
		else {
			device_id = 0;
		}
		lststr.clear();
		cnt = 1;
		cal1 = user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("1"))));
		cal2 = user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("2"))));
		cal3 = user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("3"))));
		cal4 = user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("4"))));
		cal5 = user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("5"))));
		cal6 = user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("6"))));
		cal1 = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(cal1).concat("/last_date.json"));
		cal2 = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(cal2).concat("/last_date.json"));
		cal3 = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(cal3).concat("/last_date.json"));
		cal4 = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(cal4).concat("/last_date.json"));
		cal5 = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(cal5).concat("/last_date.json"));
		cal6 = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(cal6).concat("/last_date.json"));
		for(int _repeat74 = 0; _repeat74 < (int)(6); _repeat74++) {
			key = String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))));
			key_s = String.valueOf((long)(device_id + 1)).concat("-".concat(String.valueOf((long)(cnt))));
			key_en = String.valueOf((long)(device_id + 1)).concat("e".concat(String.valueOf((long)(cnt))));
			if (dev.containsKey(key)) {
				lststr.add(dev.get(key).toString());
				if (cnt == 1) {
					t1.setVisibility(View.VISIBLE);
					n1.setText(dev.get(key).toString());
					p1.setImageResource(getResources().getIdentifier("i_".concat(String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))))), "drawable", getPackageName()));
					m1.setText(dev.get(key_s).toString());
					if (dev.get(key_en).toString().equals("yes")) {
						t1.setVisibility(View.VISIBLE);
					}
					else {
						t1.setVisibility(View.INVISIBLE);
					}
				}
				if (cnt == 2) {
					n2.setText(dev.get(key).toString());
					p2.setImageResource(getResources().getIdentifier("i_".concat(String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))))), "drawable", getPackageName()));
					m2.setText(dev.get(key_s).toString());
					if (dev.get(key_en).toString().equals("yes")) {
						t2.setVisibility(View.VISIBLE);
					}
					else {
						t2.setVisibility(View.INVISIBLE);
					}
				}
				if (cnt == 3) {
					n3.setText(dev.get(key).toString());
					p3.setImageResource(getResources().getIdentifier("i_".concat(String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))))), "drawable", getPackageName()));
					m3.setText(dev.get(key_s).toString());
					if (dev.get(key_en).toString().equals("yes")) {
						t3.setVisibility(View.VISIBLE);
					}
					else {
						t3.setVisibility(View.INVISIBLE);
					}
				}
				if (cnt == 4) {
					n4.setText(dev.get(key).toString());
					p4.setImageResource(getResources().getIdentifier("i_".concat(String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))))), "drawable", getPackageName()));
					m4.setText(dev.get(key_s).toString());
					if (dev.get(key_en).toString().equals("yes")) {
						t4.setVisibility(View.VISIBLE);
					}
					else {
						t4.setVisibility(View.INVISIBLE);
					}
				}
				if (cnt == 5) {
					n5.setText(dev.get(key).toString());
					p5.setImageResource(getResources().getIdentifier("i_".concat(String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))))), "drawable", getPackageName()));
					m5.setText(dev.get(key_s).toString());
					if (dev.get(key_en).toString().equals("yes")) {
						t5.setVisibility(View.VISIBLE);
					}
					else {
						t5.setVisibility(View.INVISIBLE);
					}
				}
				if (cnt == 6) {
					n6.setText(dev.get(key).toString());
					p6.setImageResource(getResources().getIdentifier("i_".concat(String.valueOf((long)(device_id + 1)).concat("_".concat(String.valueOf((long)(cnt))))), "drawable", getPackageName()));
					m6.setText(dev.get(key_s).toString());
					if (dev.get(key_en).toString().equals("yes")) {
						t6.setVisibility(View.VISIBLE);
					}
					else {
						t6.setVisibility(View.INVISIBLE);
					}
				}
			}
			cnt++;
		}
		if (FileUtil.isFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("1"))))).concat("/sets.json")))) {
			_round_white(t1);
			n1.setTextColor(0xFFFFFFFF);
		}
		else {
			_round_gray(t1);
			n1.setTextColor(0xFF808090);
		}
		if (FileUtil.isFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("2"))))).concat("/sets.json")))) {
			_round_white(t2);
			n2.setTextColor(0xFFFFFFFF);
		}
		else {
			_round_gray(t2);
			n2.setTextColor(0xFF808090);
		}
		if (FileUtil.isFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("3"))))).concat("/sets.json")))) {
			_round_white(t3);
			n3.setTextColor(0xFFFFFFFF);
		}
		else {
			_round_gray(t3);
			n3.setTextColor(0xFF808090);
		}
		if (FileUtil.isFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("4"))))).concat("/sets.json")))) {
			_round_white(t4);
			n4.setTextColor(0xFFFFFFFF);
		}
		else {
			_round_gray(t4);
			n4.setTextColor(0xFF808090);
		}
		if (FileUtil.isFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("5"))))).concat("/sets.json")))) {
			_round_white(t5);
			n5.setTextColor(0xFFFFFFFF);
		}
		else {
			_round_gray(t5);
			n5.setTextColor(0xFF808090);
		}
		if (FileUtil.isFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(user_id.concat("/".concat(String.valueOf((long)(device_id + 1)).concat("/".concat("6"))))).concat("/sets.json")))) {
			_round_white(t6);
			n6.setTextColor(0xFFFFFFFF);
		}
		else {
			_round_gray(t6);
			n6.setTextColor(0xFF808090);
		}
		n1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		n2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		n3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		n4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		n5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		n6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		m1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		m2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		m3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		m4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		m5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		m6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		cal = Calendar.getInstance();
		if (FileUtil.isFile(cal1)) {
			if (FileUtil.readFile(cal1).equals(new SimpleDateFormat("dd/MM/yy").format(cal.getTime()))) {
				_round_red(t1);
				n1.setTextColor(0xFFFF0000);
			}
		}
		if (FileUtil.isFile(cal2)) {
			if (FileUtil.readFile(cal2).equals(new SimpleDateFormat("dd/MM/yy").format(cal.getTime()))) {
				_round_red(t2);
				n2.setTextColor(0xFFFF0000);
			}
		}
		if (FileUtil.isFile(cal3)) {
			if (FileUtil.readFile(cal3).equals(new SimpleDateFormat("dd/MM/yy").format(cal.getTime()))) {
				_round_red(t3);
				n3.setTextColor(0xFFFF0000);
			}
		}
		if (FileUtil.isFile(cal4)) {
			if (FileUtil.readFile(cal4).equals(new SimpleDateFormat("dd/MM/yy").format(cal.getTime()))) {
				_round_red(t4);
				n4.setTextColor(0xFFFF0000);
			}
		}
		if (FileUtil.isFile(cal5)) {
			if (FileUtil.readFile(cal5).equals(new SimpleDateFormat("dd/MM/yy").format(cal.getTime()))) {
				_round_red(t5);
				n5.setTextColor(0xFFFF0000);
			}
		}
		if (FileUtil.isFile(cal6)) {
			if (FileUtil.readFile(cal6).equals(new SimpleDateFormat("dd/MM/yy").format(cal.getTime()))) {
				_round_red(t6);
				n6.setTextColor(0xFFFF0000);
			}
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
	public void _round_gray (final View _view) {
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
	
	
	public void _round_red (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("#474752")};//, Color.parseColor("black") };
		//gd.setColors(clr);
		//gd.setColor(Color.parseColor("#80808090"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		gd.setStroke(3, Color.parseColor("red"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _round_white (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		//int clr[] = new int[]{ Color.parseColor("#474752")};//, Color.parseColor("black") };
		//gd.setColors(clr);
		//gd.setColor(Color.parseColor("#80808090"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		gd.setStroke(3, Color.parseColor("white"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _map_images (final double _dev) {
		if (true) {
			
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