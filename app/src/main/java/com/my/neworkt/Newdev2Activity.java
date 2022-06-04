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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class Newdev2Activity extends  Activity { 
	
	
	private double train = 0;
	private double perc = 0;
	private double whol = 0;
	private HashMap<String, Object> dev = new HashMap<>();
	private double cnt = 0;
	private String key = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<String> lststr = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private EditText edittext1;
	private SeekBar seekbar1;
	private Spinner spinner1;
	
	private SharedPreferences sharedpref;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newdev2);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		listview1 = (ListView) findViewById(R.id.listview1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					seekbar1.setProgress((int)Double.parseDouble(_charSeq));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				edittext1.setText(String.valueOf((long)(_progressValue)));
				listmap.clear();
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("train", edittext1.getText().toString());
					listmap.add(_item);
				}
				
				listview1.setAdapter(new Listview1Adapter(listmap));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				liststring.clear();
				cnt = 1;
				for(int _repeat14 = 0; _repeat14 < (int)(6); _repeat14++) {
					key = String.valueOf((long)(_position + 1)).concat("_".concat(String.valueOf((long)(cnt))));
					if (dev.containsKey(key)) {
						liststring.add(dev.get(key).toString());
					}
					cnt++;
				}
				listview1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, liststring));
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		dev = new Gson().fromJson(sharedpref.getString("devices", ""), new TypeToken<HashMap<String, Object>>(){}.getType());
		cnt = 1;
		for(int _repeat44 = 0; _repeat44 < (int)(21); _repeat44++) {
			key = String.valueOf((long)(cnt));
			if (dev.containsKey(key)) {
				lststr.add(dev.get(key).toString());
			}
			cnt++;
		}
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, lststr));
		if (false) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("train", "1");
				listmap.add(_item);
			}
			
			listview1.setAdapter(new Listview1Adapter(listmap));
			seekbar1.setProgress((int)1);
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