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
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;


public class Newdev5Activity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private double current_weight = 0;
	private double former_weight = 0;
	private double current_reps = 0;
	private double former_reps = 0;
	private String rankpath = "";
	private double train = 0;
	private double perc = 0;
	private double whol = 0;
	private String date_path = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear20;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private LinearLayout linear28;
	private LinearLayout linear17;
	private LinearLayout linear16;
	private LinearLayout linear15;
	private ListView listview1;
	private SeekBar sb_top;
	private ImageView imageview3;
	private LinearLayout linear34;
	private LinearLayout linear35;
	private LinearLayout linear29;
	private TextView textview19;
	private TextView percentage;
	private TextView textview21;
	private ImageView arrow;
	private TextView l1;
	private TextView l2;
	private TextView textview1;
	private TextView l3;
	private TextView textview10;
	private LinearLayout linear26;
	private TextView c1;
	private TextView c2;
	private TextView textview14;
	private TextView c3;
	private TextView textview20;
	private LinearLayout linear27;
	private LinearLayout linear25;
	private LinearLayout linear13;
	private TextView textview15;
	private TextView txt_resist;
	
	private TimerTask timer0;
	private Intent int0 = new Intent();
	private SharedPreferences sharedpref;
	private Calendar cal = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newdev5);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			|| checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear28 = (LinearLayout) findViewById(R.id.linear28);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		listview1 = (ListView) findViewById(R.id.listview1);
		sb_top = (SeekBar) findViewById(R.id.sb_top);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		linear34 = (LinearLayout) findViewById(R.id.linear34);
		linear35 = (LinearLayout) findViewById(R.id.linear35);
		linear29 = (LinearLayout) findViewById(R.id.linear29);
		textview19 = (TextView) findViewById(R.id.textview19);
		percentage = (TextView) findViewById(R.id.percentage);
		textview21 = (TextView) findViewById(R.id.textview21);
		arrow = (ImageView) findViewById(R.id.arrow);
		l1 = (TextView) findViewById(R.id.l1);
		l2 = (TextView) findViewById(R.id.l2);
		textview1 = (TextView) findViewById(R.id.textview1);
		l3 = (TextView) findViewById(R.id.l3);
		textview10 = (TextView) findViewById(R.id.textview10);
		linear26 = (LinearLayout) findViewById(R.id.linear26);
		c1 = (TextView) findViewById(R.id.c1);
		c2 = (TextView) findViewById(R.id.c2);
		textview14 = (TextView) findViewById(R.id.textview14);
		c3 = (TextView) findViewById(R.id.c3);
		textview20 = (TextView) findViewById(R.id.textview20);
		linear27 = (LinearLayout) findViewById(R.id.linear27);
		linear25 = (LinearLayout) findViewById(R.id.linear25);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		textview15 = (TextView) findViewById(R.id.textview15);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		
		linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				int0.setClass(getApplicationContext(), N1607aActivity.class);
				startActivity(int0);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		_always_on();
		rankpath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/rank.json"));
		date_path = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/last_date.json"));
		cal = Calendar.getInstance();
		FileUtil.writeFile(date_path, new SimpleDateFormat("dd/MM/yy").format(cal.getTime()));
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("train", FileUtil.readFile(rankpath));
			listmap.add(_item);
		}
		
		listview1.setAdapter(new Listview1Adapter(listmap));
		current_weight = Double.parseDouble(getIntent().getStringExtra("avg_w_1"));
		former_weight = Double.parseDouble(getIntent().getStringExtra("avg_w_0"));
		current_reps = Double.parseDouble(getIntent().getStringExtra("tot_r_1"));
		former_reps = Double.parseDouble(getIntent().getStringExtra("tot_r_0"));
		c1.setText(String.valueOf((long)(current_weight / 10)));
		c2.setText(String.valueOf((long)(current_weight - (Double.parseDouble(c1.getText().toString()) * 10))));
		c3.setText(String.valueOf((long)((current_weight - Double.parseDouble(String.valueOf((long)(current_weight)))) * 10)));
		l1.setText(String.valueOf((long)(former_weight / 10)));
		l2.setText(String.valueOf((long)(former_weight - (Double.parseDouble(l1.getText().toString()) * 10))));
		l3.setText(String.valueOf((long)((former_weight - Double.parseDouble(String.valueOf((long)(former_weight)))) * 10)));
		if (((former_weight == 0) || (former_reps == 0)) || !(current_reps == former_reps)) {
			
		}
		else {
			if (current_weight == former_weight) {
				percentage.setText("+".concat("0").concat("%"));
				percentage.setTextColor(0xFFFFFFFF);
				c1.setTextColor(0xFFFFFFFF);
				c2.setTextColor(0xFFFFFFFF);
				c3.setTextColor(0xFFFFFFFF);
			}
			else {
				if (current_weight > former_weight) {
					arrow.setImageResource(R.drawable.uppp);
					percentage.setText("+".concat(String.valueOf((long)(((current_weight - former_weight) / current_weight) * 100))).concat("%"));
					percentage.setTextColor(0xFF5CE66C);
					c1.setTextColor(0xFF5CE66C);
					c2.setTextColor(0xFF5CE66C);
					c3.setTextColor(0xFF5CE66C);
				}
				else {
					arrow.setImageResource(R.drawable.down);
					percentage.setText("-".concat(String.valueOf((long)(((former_weight - current_weight) / current_weight) * 100))).concat("%"));
					percentage.setTextColor(0xFFFDC02F);
					c1.setTextColor(0xFFFDC02F);
					c2.setTextColor(0xFFFDC02F);
					c3.setTextColor(0xFFFDC02F);
				}
			}
		}
		textview19.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview21.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		l1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		l2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		l3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		c1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		c2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		c3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		percentage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		_roundg(l1);
		_roundg(l2);
		_roundg(l3);
		_roundg(c1);
		_roundg(c2);
		_roundg(c3);
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
	
	
	public void _always_on () {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
				_view = _inflater.inflate(R.layout.rating, null);
			}
			
			final LinearLayout linear10 = (LinearLayout) _view.findViewById(R.id.linear10);
			final LinearLayout linear11 = (LinearLayout) _view.findViewById(R.id.linear11);
			final ProgressBar progressbar1 = (ProgressBar) _view.findViewById(R.id.progressbar1);
			final LinearLayout linear12 = (LinearLayout) _view.findViewById(R.id.linear12);
			final LinearLayout linear13 = (LinearLayout) _view.findViewById(R.id.linear13);
			final LinearLayout linear14 = (LinearLayout) _view.findViewById(R.id.linear14);
			final ImageView sag = (ImageView) _view.findViewById(R.id.sag);
			final ImageView l1 = (ImageView) _view.findViewById(R.id.l1);
			final ImageView l2 = (ImageView) _view.findViewById(R.id.l2);
			final ImageView l3 = (ImageView) _view.findViewById(R.id.l3);
			final TextView tperc = (TextView) _view.findViewById(R.id.tperc);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView twhol = (TextView) _view.findViewById(R.id.twhol);
			
			train = Double.parseDouble(listmap.get((int)_position).get("train").toString());
			if (train < 46) {
				sag.setImageResource(R.drawable.saga1);
				tperc.setTextColor(0xFFF1453D);
				progressbar1.getProgressDrawable().setColorFilter(Color.parseColor("#f1453d"), PorterDuff.Mode.SRC_IN);
				
				
			}
			else {
				if (train < 136) {
					sag.setImageResource(R.drawable.saga2);
					tperc.setTextColor(0xFFFDC02F);
					progressbar1.getProgressDrawable().setColorFilter(Color.parseColor("#fdc02f"), PorterDuff.Mode.SRC_IN);
					
					
				}
				else {
					if (train < 271) {
						sag.setImageResource(R.drawable.saga3);
						tperc.setTextColor(0xFF5CE66C);
						progressbar1.getProgressDrawable().setColorFilter(Color.parseColor("#5ce66c"), PorterDuff.Mode.SRC_IN);
						
						
					}
					else {
						if (train < 451) {
							sag.setImageResource(R.drawable.saga4);
							tperc.setTextColor(0xFF00D3FF);
							progressbar1.getProgressDrawable().setColorFilter(Color.parseColor("#00d3ff"), PorterDuff.Mode.SRC_IN);
							
							
						}
						else {
							sag.setImageResource(R.drawable.saga5);
							tperc.setTextColor(0xFFB139CC);
							progressbar1.getProgressDrawable().setColorFilter(Color.parseColor("#b139cc"), PorterDuff.Mode.SRC_IN);
							
							
						}
					}
				}
			}
			perc = 0;
			whol = 1;
			l1.setImageResource(R.drawable.saga1star0);
			l2.setImageResource(R.drawable.saga1star0);
			l3.setImageResource(R.drawable.saga1star0);
			if (train > 0) {
				l1.setImageResource(R.drawable.saga1star1);
				l2.setImageResource(R.drawable.saga1star0);
				l3.setImageResource(R.drawable.saga1star0);
				perc = train - 1;
				whol = 3 - 1;
			}
			if (train > 2) {
				l1.setImageResource(R.drawable.saga1star1);
				l2.setImageResource(R.drawable.saga1star1);
				l3.setImageResource(R.drawable.saga1star0);
				perc = train - 3;
				whol = 6 - 3;
			}
			if (train > 5) {
				l1.setImageResource(R.drawable.saga1diamond0);
				l2.setImageResource(R.drawable.saga1diamond0);
				l3.setImageResource(R.drawable.saga1diamond0);
				perc = train - 6;
				whol = 10 - 6;
			}
			if (train > 9) {
				l1.setImageResource(R.drawable.saga1diamond1);
				l2.setImageResource(R.drawable.saga1diamond0);
				l3.setImageResource(R.drawable.saga1diamond0);
				perc = train - 10;
				whol = 15 - 10;
			}
			if (train > 14) {
				l1.setImageResource(R.drawable.saga1diamond1);
				l2.setImageResource(R.drawable.saga1diamond1);
				l3.setImageResource(R.drawable.saga1diamond0);
				perc = train - 15;
				whol = 21 - 15;
			}
			if (train > 20) {
				l1.setImageResource(R.drawable.saga1royal0);
				l2.setImageResource(R.drawable.saga1royal0);
				l3.setImageResource(R.drawable.saga1royal0);
				perc = train - 21;
				whol = 28 - 21;
			}
			if (train > 27) {
				l1.setImageResource(R.drawable.saga1royal1);
				l2.setImageResource(R.drawable.saga1royal0);
				l3.setImageResource(R.drawable.saga1royal0);
				perc = train - 28;
				whol = 36 - 28;
			}
			if (train > 35) {
				l1.setImageResource(R.drawable.saga1royal1);
				l2.setImageResource(R.drawable.saga1royal1);
				l3.setImageResource(R.drawable.saga1royal0);
				perc = train - 36;
				whol = 45 - 36;
			}
			if (train > 44) {
				l1.setImageResource(R.drawable.saga2star0);
				l2.setImageResource(R.drawable.saga2star0);
				l3.setImageResource(R.drawable.saga2star0);
				perc = train - 45;
				whol = 47 - 45;
			}
			if (train > 46) {
				l1.setImageResource(R.drawable.saga2star1);
				l2.setImageResource(R.drawable.saga2star0);
				l3.setImageResource(R.drawable.saga2star0);
				perc = train - 47;
				whol = 51 - 47;
			}
			if (train > 50) {
				l1.setImageResource(R.drawable.saga2star1);
				l2.setImageResource(R.drawable.saga2star1);
				l3.setImageResource(R.drawable.saga2star0);
				perc = train - 51;
				whol = 57 - 51;
			}
			if (train > 56) {
				l1.setImageResource(R.drawable.saga2diamond0);
				l2.setImageResource(R.drawable.saga2diamond0);
				l3.setImageResource(R.drawable.saga2diamond0);
				perc = train - 57;
				whol = 65 - 57;
			}
			if (train > 64) {
				l1.setImageResource(R.drawable.saga2diamond1);
				l2.setImageResource(R.drawable.saga2diamond0);
				l3.setImageResource(R.drawable.saga2diamond0);
				perc = train - 65;
				whol = 75 - 65;
			}
			if (train > 74) {
				l1.setImageResource(R.drawable.saga2diamond1);
				l2.setImageResource(R.drawable.saga2diamond1);
				l3.setImageResource(R.drawable.saga2diamond0);
				perc = train - 75;
				whol = 87 - 75;
			}
			if (train > 86) {
				l1.setImageResource(R.drawable.saga2royal0);
				l2.setImageResource(R.drawable.saga2royal0);
				l3.setImageResource(R.drawable.saga2royal0);
				perc = train - 87;
				whol = 101 - 87;
			}
			if (train > 100) {
				l1.setImageResource(R.drawable.saga2royal1);
				l2.setImageResource(R.drawable.saga2royal0);
				l3.setImageResource(R.drawable.saga2royal0);
				perc = train - 101;
				whol = 117 - 101;
			}
			if (train > 116) {
				l1.setImageResource(R.drawable.saga2royal1);
				l2.setImageResource(R.drawable.saga2royal1);
				l3.setImageResource(R.drawable.saga2royal0);
				perc = train - 117;
				whol = 135 - 117;
			}
			if (train > 134) {
				l1.setImageResource(R.drawable.saga3star0);
				l2.setImageResource(R.drawable.saga3star0);
				l3.setImageResource(R.drawable.saga3star0);
				perc = train - 135;
				whol = 138 - 135;
			}
			if (train > 137) {
				l1.setImageResource(R.drawable.saga3star1);
				l2.setImageResource(R.drawable.saga3star0);
				l3.setImageResource(R.drawable.saga3star0);
				perc = train - 138;
				whol = 144 - 138;
			}
			if (train > 143) {
				l1.setImageResource(R.drawable.saga3star1);
				l2.setImageResource(R.drawable.saga3star1);
				l3.setImageResource(R.drawable.saga3star0);
				perc = train - 144;
				whol = 153 - 144;
			}
			if (train > 152) {
				l1.setImageResource(R.drawable.saga3diamond0);
				l2.setImageResource(R.drawable.saga3diamond0);
				l3.setImageResource(R.drawable.saga3diamond0);
				perc = train - 153;
				whol = 165 - 153;
			}
			if (train > 164) {
				l1.setImageResource(R.drawable.saga3diamond1);
				l2.setImageResource(R.drawable.saga3diamond0);
				l3.setImageResource(R.drawable.saga3diamond0);
				perc = train - 165;
				whol = 180 - 165;
			}
			if (train > 179) {
				l1.setImageResource(R.drawable.saga3diamond1);
				l2.setImageResource(R.drawable.saga3diamond1);
				l3.setImageResource(R.drawable.saga3diamond0);
				perc = train - 180;
				whol = 198 - 180;
			}
			if (train > 197) {
				l1.setImageResource(R.drawable.saga3royal0);
				l2.setImageResource(R.drawable.saga3royal0);
				l3.setImageResource(R.drawable.saga3royal0);
				perc = train - 198;
				whol = 219 - 198;
			}
			if (train > 218) {
				l1.setImageResource(R.drawable.saga3royal1);
				l2.setImageResource(R.drawable.saga3royal0);
				l3.setImageResource(R.drawable.saga3royal0);
				perc = train - 219;
				whol = 243 - 219;
			}
			if (train > 242) {
				l1.setImageResource(R.drawable.saga3royal1);
				l2.setImageResource(R.drawable.saga3royal1);
				l3.setImageResource(R.drawable.saga3royal0);
				perc = train - 243;
				whol = 270 - 243;
			}
			if (train > 269) {
				l1.setImageResource(R.drawable.saga4star0);
				l2.setImageResource(R.drawable.saga4star0);
				l3.setImageResource(R.drawable.saga4star0);
				perc = train - 270;
				whol = 274 - 270;
			}
			if (train > 273) {
				l1.setImageResource(R.drawable.saga4star1);
				l2.setImageResource(R.drawable.saga4star0);
				l3.setImageResource(R.drawable.saga4star0);
				perc = train - 274;
				whol = 282 - 274;
			}
			if (train > 281) {
				l1.setImageResource(R.drawable.saga4star1);
				l2.setImageResource(R.drawable.saga4star1);
				l3.setImageResource(R.drawable.saga4star0);
				perc = train - 282;
				whol = 294 - 282;
			}
			if (train > 293) {
				l1.setImageResource(R.drawable.saga4diamond0);
				l2.setImageResource(R.drawable.saga4diamond0);
				l3.setImageResource(R.drawable.saga4diamond0);
				perc = train - 294;
				whol = 310 - 294;
			}
			if (train > 309) {
				l1.setImageResource(R.drawable.saga4diamond1);
				l2.setImageResource(R.drawable.saga4diamond0);
				l3.setImageResource(R.drawable.saga4diamond0);
				perc = train - 310;
				whol = 330 - 310;
			}
			if (train > 329) {
				l1.setImageResource(R.drawable.saga4diamond1);
				l2.setImageResource(R.drawable.saga4diamond1);
				l3.setImageResource(R.drawable.saga4diamond0);
				perc = train - 330;
				whol = 354 - 330;
			}
			if (train > 353) {
				l1.setImageResource(R.drawable.saga4royal0);
				l2.setImageResource(R.drawable.saga4royal0);
				l3.setImageResource(R.drawable.saga4royal0);
				perc = train - 354;
				whol = 382 - 354;
			}
			if (train > 381) {
				l1.setImageResource(R.drawable.saga4royal1);
				l2.setImageResource(R.drawable.saga4royal0);
				l3.setImageResource(R.drawable.saga4royal0);
				perc = train - 382;
				whol = 414 - 382;
			}
			if (train > 413) {
				l1.setImageResource(R.drawable.saga4royal1);
				l2.setImageResource(R.drawable.saga4royal1);
				l3.setImageResource(R.drawable.saga4royal0);
				perc = train - 414;
				whol = 450 - 414;
			}
			if (train > 449) {
				l1.setImageResource(R.drawable.saga5star0);
				l2.setImageResource(R.drawable.saga5star0);
				l3.setImageResource(R.drawable.saga5star0);
				perc = train - 450;
				whol = 455 - 450;
			}
			if (train > 454) {
				l1.setImageResource(R.drawable.saga5star1);
				l2.setImageResource(R.drawable.saga5star0);
				l3.setImageResource(R.drawable.saga5star0);
				perc = train - 455;
				whol = 465 - 455;
			}
			if (train > 464) {
				l1.setImageResource(R.drawable.saga5star1);
				l2.setImageResource(R.drawable.saga5star1);
				l3.setImageResource(R.drawable.saga5star0);
				perc = train - 465;
				whol = 480 - 465;
			}
			if (train > 479) {
				l1.setImageResource(R.drawable.saga5diamond0);
				l2.setImageResource(R.drawable.saga5diamond0);
				l3.setImageResource(R.drawable.saga5diamond0);
				perc = train - 480;
				whol = 500 - 480;
			}
			if (train > 499) {
				l1.setImageResource(R.drawable.saga5diamond1);
				l2.setImageResource(R.drawable.saga5diamond0);
				l3.setImageResource(R.drawable.saga5diamond0);
				perc = train - 500;
				whol = 525 - 500;
			}
			if (train > 524) {
				l1.setImageResource(R.drawable.saga5diamond1);
				l2.setImageResource(R.drawable.saga5diamond1);
				l3.setImageResource(R.drawable.saga5diamond0);
				perc = train - 525;
				whol = 555 - 525;
			}
			if (train > 554) {
				l1.setImageResource(R.drawable.saga5royal0);
				l2.setImageResource(R.drawable.saga5royal0);
				l3.setImageResource(R.drawable.saga5royal0);
				perc = train - 555;
				whol = 590 - 555;
			}
			if (train > 589) {
				l1.setImageResource(R.drawable.saga5royal1);
				l2.setImageResource(R.drawable.saga5royal0);
				l3.setImageResource(R.drawable.saga5royal0);
				perc = train - 590;
				whol = 630 - 590;
			}
			if (train > 629) {
				l1.setImageResource(R.drawable.saga5royal1);
				l2.setImageResource(R.drawable.saga5royal1);
				l3.setImageResource(R.drawable.saga5royal0);
				perc = train - 630;
				whol = 675 - 630;
			}
			if (train > 674) {
				l1.setImageResource(R.drawable.saga5royal1);
				l2.setImageResource(R.drawable.saga5royal1);
				l3.setImageResource(R.drawable.saga5royal1);
				perc = train - 630;
				whol = 675 - 630;
			}
			progressbar1.setProgress((int)Double.parseDouble(String.valueOf((long)((perc * 100) / whol))));
			tperc.setText(String.valueOf((long)(perc)));
			twhol.setText(String.valueOf((long)(whol)));
			
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