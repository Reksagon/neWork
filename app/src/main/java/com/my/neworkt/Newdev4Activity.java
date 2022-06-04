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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import java.text.DecimalFormat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;


public class Newdev4Activity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private String strmnts = "";
	private String strsecs = "";
	private String strscspa = "";
	private String user = "";
	private double ind = 0;
	private double weight = 0;
	private double repstodo = 0;
	private double train = 0;
	private double perc = 0;
	private double whol = 0;
	private double curr_w = 0;
	private double curr_s = 0;
	private double curr_r = 0;
	private double total_r = 0;
	private double sum_w = 0;
	private boolean trainenable = false;
	private String rankpath = "";
	private String lastweightpath = "";
	private String lastrepspath = "";
	private double setting_max_resist = 0;
	private double setting_min_resist = 0;
	private double response_done_reps = 0;
	private double temp = 0;
	private boolean cont_prsd = false;
	
	private ArrayList<String> timeformatlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear20;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private LinearLayout linear17;
	private LinearLayout linear16;
	private LinearLayout linear15;
	private ImageView imageview2;
	private ListView listview1;
	private SeekBar sb_top;
	private LinearLayout linear21;
	private LinearLayout lin_resist;
	private LinearLayout linear23;
	private TextView d1;
	private TextView d2;
	private TextView textview14;
	private TextView d3;
	private TextView d4;
	private TextView txt_reps;
	private LinearLayout linear19;
	private LinearLayout linear22;
	private LinearLayout linear24;
	private TextView d1h;
	private TextView d2h;
	private TextView textview1;
	private TextView d3h;
	private TextView d4h;
	private TextView txt_set;
	private TextView textview11;
	private TextView reps_minus;
	private TextView rd1;
	private TextView rd2;
	private TextView reps_plus;
	private TextView textview10;
	private LinearLayout linear13;
	private LinearLayout linear25;
	private TextView txt_resist;
	private Button btn_OK;
	private TextView device;
	
	private TimerTask timer0;
	private ProgressDialog y;
	private Intent intent0 = new Intent();
	private AlertDialog.Builder dial1;
	private SharedPreferences sharedpref;
	private TimerTask timer_reps_update;
	private RequestNetwork reqnet;
	private RequestNetwork.RequestListener _reqnet_request_listener;
	private TimerTask timer1;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newdev4);
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
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		listview1 = (ListView) findViewById(R.id.listview1);
		sb_top = (SeekBar) findViewById(R.id.sb_top);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		lin_resist = (LinearLayout) findViewById(R.id.lin_resist);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		d1 = (TextView) findViewById(R.id.d1);
		d2 = (TextView) findViewById(R.id.d2);
		textview14 = (TextView) findViewById(R.id.textview14);
		d3 = (TextView) findViewById(R.id.d3);
		d4 = (TextView) findViewById(R.id.d4);
		txt_reps = (TextView) findViewById(R.id.txt_reps);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		linear24 = (LinearLayout) findViewById(R.id.linear24);
		d1h = (TextView) findViewById(R.id.d1h);
		d2h = (TextView) findViewById(R.id.d2h);
		textview1 = (TextView) findViewById(R.id.textview1);
		d3h = (TextView) findViewById(R.id.d3h);
		d4h = (TextView) findViewById(R.id.d4h);
		txt_set = (TextView) findViewById(R.id.txt_set);
		textview11 = (TextView) findViewById(R.id.textview11);
		reps_minus = (TextView) findViewById(R.id.reps_minus);
		rd1 = (TextView) findViewById(R.id.rd1);
		rd2 = (TextView) findViewById(R.id.rd2);
		reps_plus = (TextView) findViewById(R.id.reps_plus);
		textview10 = (TextView) findViewById(R.id.textview10);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear25 = (LinearLayout) findViewById(R.id.linear25);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		device = (TextView) findViewById(R.id.device);
		dial1 = new AlertDialog.Builder(this);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		reqnet = new RequestNetwork(this);
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (trainenable && cont_prsd) {
					curr_w = weight;
					total_r++;
					sum_w = sum_w + curr_w;
					curr_r++;
					_update_data();
					if (curr_r == repstodo) {
						if (curr_s == ind) {
							trainenable = false;
							_weight_off();
							intent0.setClass(getApplicationContext(), Newdev5Activity.class);
							intent0.putExtra("theuser", getIntent().getStringExtra("theuser"));
							intent0.putExtra("avg_w_0", FileUtil.readFile(lastweightpath));
							FileUtil.writeFile(lastweightpath, String.valueOf(sum_w / total_r));
							intent0.putExtra("avg_w_1", FileUtil.readFile(lastweightpath));
							intent0.putExtra("tot_r_0", FileUtil.readFile(lastrepspath));
							FileUtil.writeFile(lastrepspath, String.valueOf((long)(total_r)));
							intent0.putExtra("tot_r_1", FileUtil.readFile(lastrepspath));
							FileUtil.writeFile(rankpath, String.valueOf((long)(Double.parseDouble(FileUtil.readFile(rankpath)) + 1)));
							startActivity(intent0);
							finish();
						}
						else {
							curr_r = 0;
							curr_s++;
							repstodo = Double.parseDouble(lm.get((int)curr_s - 1).get("r").toString());
							weight = Double.parseDouble(lm.get((int)curr_s - 1).get("w").toString());
							cont_prsd = false;
							timer0 = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											_update_data();
											btn_OK.setVisibility(View.VISIBLE);
										}
									});
								}
							};
							_timer.schedule(timer0, (int)(1000));
							if (false) {
								_set_resist(weight);
								timer0 = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												_update_data();
											}
										});
									}
								};
								_timer.schedule(timer0, (int)(1000));
							}
						}
					}
					else {
						
					}
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dial1.setTitle("יציאה מאימון אישי");
				dial1.setMessage("האם ברצונך לצאת מהאימון האישי?");
				dial1.setPositiveButton("כן", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						intent0.setClass(getApplicationContext(), N1607aActivity.class);
						startActivity(intent0);
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
		
		txt_reps.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		reps_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(reps_minus);
				reps_minus.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
				if (weight > setting_min_resist) {
					weight--;
					rd1.setText(String.valueOf((long)(weight / 10)));
					rd2.setText(String.valueOf((long)(weight - (Double.parseDouble(rd1.getText().toString()) * 10))));
					if (cont_prsd && trainenable) {
						_set_resist_during_set(weight);
					}
				}
			}
		});
		
		reps_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(reps_plus);
				reps_plus.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
				if (weight < setting_max_resist) {
					weight++;
					rd1.setText(String.valueOf((long)(weight / 10)));
					rd2.setText(String.valueOf((long)(weight - (Double.parseDouble(rd1.getText().toString()) * 10))));
					if (cont_prsd && trainenable) {
						_set_resist_during_set(weight);
					}
				}
			}
		});
		
		btn_OK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cont_prsd = true;
				_set_resist(weight);
				timer1 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_update_data();
								btn_OK.setVisibility(View.GONE);
							}
						});
					}
				};
				_timer.schedule(timer1, (int)(1000));
				_buttrp(btn_OK);
				btn_OK.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		_reqnet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("how_many_reps")) {
					response_done_reps = Double.parseDouble(_response);
				}
				temp = response_done_reps - curr_r;
				if (trainenable) {
					curr_w = weight;
					sum_w = sum_w + (curr_w * temp);
					total_r = total_r + temp;
					curr_r = curr_r + temp;
					_update_data();
					if ((curr_r == repstodo) || (curr_r > repstodo)) {
						if (curr_s == ind) {
							trainenable = false;
							_weight_off();
							intent0.setClass(getApplicationContext(), Newdev5Activity.class);
							intent0.putExtra("theuser", getIntent().getStringExtra("theuser"));
							intent0.putExtra("avg_w_0", FileUtil.readFile(lastweightpath));
							FileUtil.writeFile(lastweightpath, String.valueOf(sum_w / total_r));
							intent0.putExtra("avg_w_1", FileUtil.readFile(lastweightpath));
							intent0.putExtra("tot_r_0", FileUtil.readFile(lastrepspath));
							FileUtil.writeFile(lastrepspath, String.valueOf((long)(total_r)));
							intent0.putExtra("tot_r_1", FileUtil.readFile(lastrepspath));
							FileUtil.writeFile(rankpath, String.valueOf((long)(Double.parseDouble(FileUtil.readFile(rankpath)) + 1)));
							startActivity(intent0);
							finish();
						}
						else {
							curr_r = 0;
							curr_s++;
							repstodo = Double.parseDouble(lm.get((int)curr_s - 1).get("r").toString());
							weight = Double.parseDouble(lm.get((int)curr_s - 1).get("w").toString());
							cont_prsd = false;
							timer0 = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											_update_data();
											btn_OK.setVisibility(View.VISIBLE);
										}
									});
								}
							};
							_timer.schedule(timer0, (int)(1000));
							if (false) {
								_set_resist(weight);
								timer0 = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												_update_data();
											}
										});
									}
								};
								_timer.schedule(timer0, (int)(1000));
							}
						}
					}
					else {
						
					}
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
		btn_OK.setVisibility(View.GONE);
		cont_prsd = true;
		setting_min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		setting_max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		trainenable = true;
		total_r = 0;
		sum_w = 0;
		rankpath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/rank.json"));
		lastweightpath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/last_avg_weight.json"));
		lastrepspath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/last_reps_count.json"));
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("train", FileUtil.readFile(rankpath));
			listmap.add(_item);
		}
		
		listview1.setAdapter(new Listview1Adapter(listmap));
		_roundgg(txt_set);
		_roundgg(txt_reps);
		_roundgg(txt_resist);
		_roundg(d1h);
		_roundg(d2h);
		_roundg(d3h);
		_roundg(d4h);
		_roundg(d1);
		_roundg(d2);
		_roundg(d3);
		_roundg(d4);
		_roundg(rd1);
		_roundg(rd2);
		_buttg(reps_plus);
		_buttg(reps_minus);
		user = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/sets.json"));
		if (FileUtil.isExistFile(user)) {
			lm = new Gson().fromJson(FileUtil.readFile(user), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			ind = lm.size();
			repstodo = Double.parseDouble(lm.get((int)0).get("r").toString());
			weight = Double.parseDouble(lm.get((int)0).get("w").toString());
			weight = Double.parseDouble(getIntent().getStringExtra("startweight"));
			_set_resist(weight);
			curr_w = weight;
			curr_s = 1;
			curr_r = 0;
			d1h.setText(String.valueOf((long)(curr_s / 10)));
			d2h.setText(String.valueOf((long)(curr_s - (Double.parseDouble(d1h.getText().toString()) * 10))));
			d3h.setText(String.valueOf((long)(ind / 10)));
			d4h.setText(String.valueOf((long)(ind - (Double.parseDouble(d3h.getText().toString()) * 10))));
			d1.setText(String.valueOf((long)(curr_r / 10)));
			d2.setText(String.valueOf((long)(curr_r - (Double.parseDouble(d1.getText().toString()) * 10))));
			d3.setText(String.valueOf((long)(repstodo / 10)));
			d4.setText(String.valueOf((long)(repstodo - (Double.parseDouble(d3.getText().toString()) * 10))));
			rd1.setText(String.valueOf((long)(curr_w / 10)));
			rd2.setText(String.valueOf((long)(curr_w - (Double.parseDouble(rd1.getText().toString()) * 10))));
			if (FileUtil.isExistFile(lastweightpath)) {
				
			}
			else {
				FileUtil.writeFile(lastweightpath, "0");
			}
			if (FileUtil.isExistFile(lastrepspath)) {
				
			}
			else {
				FileUtil.writeFile(lastrepspath, "0");
			}
			timer_reps_update = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (cont_prsd) {
								reqnet.startRequestNetwork(RequestNetworkController.GET, "http://".concat(sharedpref.getString("ip", "")).concat("/view_reps.php"), "how_many_reps", _reqnet_request_listener);
							}
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(timer_reps_update, (int)(1000), (int)(1000));
		}
		else {
			finish();
		}
		d1h.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d2h.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d3h.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d4h.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_set.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		d4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_reps.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_set.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps_minus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		rd1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		rd2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps_plus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_resist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		device.setText(sharedpref.getString("exercise", ""));
		device.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		btn_OK.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		_buttr(btn_OK);
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
	public void _timeformat (final double _seconds) {
		strmnts = String.valueOf((long)(_seconds / 60));
		strsecs = String.valueOf((long)((Double.parseDouble(String.valueOf(_seconds / 60)) - Double.parseDouble(String.valueOf((long)(_seconds / 60)))) * 60));
		strscspa = String.valueOf((Double.parseDouble(String.valueOf(_seconds / 60)) - Double.parseDouble(String.valueOf((long)(_seconds / 60)))) * 60);
		timeformatlist.add((int)(0), String.valueOf((long)(Double.parseDouble(strmnts) / 10)));
		timeformatlist.add((int)(1), String.valueOf((long)((Double.parseDouble(String.valueOf(Double.parseDouble(strmnts) / 10)) - Double.parseDouble(String.valueOf((long)(Double.parseDouble(strmnts) / 10)))) * 10)));
		timeformatlist.add((int)(2), String.valueOf((long)((Double.parseDouble(strscspa) / 9.9d) + 0)));
		timeformatlist.add((int)(3), String.valueOf((long)((Double.parseDouble(String.valueOf(Double.parseDouble(strscspa) / 9.9d)) * 9.9d) - (Double.parseDouble(String.valueOf((long)(Double.parseDouble(strscspa) / 9.9d))) * 9.9d))));
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
	
	
	public void _update_data () {
		d1h.setText(String.valueOf((long)(curr_s / 10)));
		d2h.setText(String.valueOf((long)(curr_s - (Double.parseDouble(d1h.getText().toString()) * 10))));
		d3h.setText(String.valueOf((long)(ind / 10)));
		d4h.setText(String.valueOf((long)(ind - (Double.parseDouble(d3h.getText().toString()) * 10))));
		d1.setText(String.valueOf((long)(curr_r / 10)));
		d2.setText(String.valueOf((long)(curr_r - (Double.parseDouble(d1.getText().toString()) * 10))));
		d3.setText(String.valueOf((long)(repstodo / 10)));
		d4.setText(String.valueOf((long)(repstodo - (Double.parseDouble(d3.getText().toString()) * 10))));
		rd1.setText(String.valueOf((long)(weight / 10)));
		rd2.setText(String.valueOf((long)(weight - (Double.parseDouble(rd1.getText().toString()) * 10))));
	}
	
	
	public void _always_on () {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	public void _weight_off () {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=off")), "o", _reqnet_request_listener);
	}
	
	
	public void _set_resist (final double _weight) {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=")).concat(String.valueOf((long)((_weight * Double.parseDouble(sharedpref.getString("res_mul", ""))) / Double.parseDouble(sharedpref.getString("res_div", ""))))), "weight", _reqnet_request_listener);
	}
	
	
	public void _set_resist_during_set (final double _resist) {
		reqnet.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=2&kg=")).concat(String.valueOf((long)((_resist * Double.parseDouble(sharedpref.getString("res_mul", ""))) / Double.parseDouble(sharedpref.getString("res_div", ""))))), "weight", _reqnet_request_listener);
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