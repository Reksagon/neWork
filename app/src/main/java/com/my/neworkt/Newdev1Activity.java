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
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;


public class Newdev1Activity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private double sets_num = 0;
	private HashMap<String, Object> h = new HashMap<>();
	private String user = "";
	private double ind = 0;
	private double train_resist = 0;
	private double train_reps = 0;
	private double setting_min_resist = 0;
	private double setting_max_resist = 0;
	private double setting_min_reps = 0;
	private double setting_max_reps = 0;
	private String train_type = "";
	private double resist_seek_max = 0;
	private double resiat_seek_progress = 0;
	private double bottom_seek_max = 0;
	private double bottom_seek_progress = 0;
	
	private ArrayList<Double> rep_s = new ArrayList<>();
	private ArrayList<Double> wh_s = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear19;
	private LinearLayout linear18;
	private TextView top_minus;
	private LinearLayout lin_resist;
	private TextView top_plus;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private LinearLayout linear11;
	private TextView txt_resist;
	private TextView resist;
	private TextView textview10;
	private SeekBar sb_top;
	private TextView bottom_minus;
	private LinearLayout lin_reps;
	private TextView bottom_plus;
	private TextView reps;
	private TextView txt_reps;
	private SeekBar sb_bottom;
	private TextView remset;
	private TextView set1;
	private TextView set2;
	private TextView set3;
	private TextView set4;
	private TextView set5;
	private TextView addset;
	private Button btn_OK;
	
	private TimerTask timer0;
	private SharedPreferences sharedpref;
	private AlertDialog.Builder dialog;
	private Intent int0 = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.newdev1);
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
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		top_minus = (TextView) findViewById(R.id.top_minus);
		lin_resist = (LinearLayout) findViewById(R.id.lin_resist);
		top_plus = (TextView) findViewById(R.id.top_plus);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		txt_resist = (TextView) findViewById(R.id.txt_resist);
		resist = (TextView) findViewById(R.id.resist);
		textview10 = (TextView) findViewById(R.id.textview10);
		sb_top = (SeekBar) findViewById(R.id.sb_top);
		bottom_minus = (TextView) findViewById(R.id.bottom_minus);
		lin_reps = (LinearLayout) findViewById(R.id.lin_reps);
		bottom_plus = (TextView) findViewById(R.id.bottom_plus);
		reps = (TextView) findViewById(R.id.reps);
		txt_reps = (TextView) findViewById(R.id.txt_reps);
		sb_bottom = (SeekBar) findViewById(R.id.sb_bottom);
		remset = (TextView) findViewById(R.id.remset);
		set1 = (TextView) findViewById(R.id.set1);
		set2 = (TextView) findViewById(R.id.set2);
		set3 = (TextView) findViewById(R.id.set3);
		set4 = (TextView) findViewById(R.id.set4);
		set5 = (TextView) findViewById(R.id.set5);
		addset = (TextView) findViewById(R.id.addset);
		btn_OK = (Button) findViewById(R.id.btn_OK);
		sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		
		top_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(top_minus);
				top_minus.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
				if (0 < resiat_seek_progress) {
					resiat_seek_progress--;
					sb_top.setProgress((int)resiat_seek_progress);
					resist.setText(String.valueOf((long)(sb_top.getProgress() + setting_min_resist)));
				}
				h = new HashMap<>();
				h.put("w", resist.getText().toString());
				h.put("r", reps.getText().toString());
				lm.set((int)(ind), h);
				
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		top_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(top_plus);
				top_plus.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
				if (resiat_seek_progress < sb_top.getMax()) {
					resiat_seek_progress++;
					sb_top.setProgress((int)resiat_seek_progress);
					resist.setText(String.valueOf((long)(sb_top.getProgress() + setting_min_resist)));
				}
				h = new HashMap<>();
				h.put("w", resist.getText().toString());
				h.put("r", reps.getText().toString());
				lm.set((int)(ind), h);
				
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("תרגיל לא נשמר");
				dialog.setMessage("האם לצאת מבלי לשמור את השינויים בתרגיל?");
				if (FileUtil.isExistFile(user)) {
					int0.setClass(getApplicationContext(), N1607aActivity.class);
					int0.putExtra("theuser", getIntent().getStringExtra("theuser"));
					startActivity(int0);
					finish();
				}
				else {
					int0.setClass(getApplicationContext(), N1607aActivity.class);
					int0.putExtra("theuser", getIntent().getStringExtra("theuser"));
					startActivity(int0);
					finish();
				}
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
				h = new HashMap<>();
				h.put("w", resist.getText().toString());
				h.put("r", reps.getText().toString());
				lm.set((int)(ind), h);
				
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		bottom_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(bottom_minus);
				bottom_minus.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
				if (0 < bottom_seek_progress) {
					bottom_seek_progress--;
					sb_bottom.setProgress((int)bottom_seek_progress);
					reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
				}
				h = new HashMap<>();
				h.put("w", resist.getText().toString());
				h.put("r", reps.getText().toString());
				lm.set((int)(ind), h);
				
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		bottom_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_buttgp(bottom_plus);
				bottom_plus.setTextColor(0xFFE0E0E0);
				timer0 = new TimerTask() {
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
				_timer.schedule(timer0, (int)(200));
				if (bottom_seek_progress < sb_bottom.getMax()) {
					bottom_seek_progress++;
					sb_bottom.setProgress((int)bottom_seek_progress);
					reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
				}
				h = new HashMap<>();
				h.put("w", resist.getText().toString());
				h.put("r", reps.getText().toString());
				lm.set((int)(ind), h);
				
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		sb_bottom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				bottom_seek_progress = _progressValue;
				reps.setText(String.valueOf((long)(_progressValue + setting_min_reps)));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				h = new HashMap<>();
				h.put("w", resist.getText().toString());
				h.put("r", reps.getText().toString());
				lm.set((int)(ind), h);
				
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
		
		remset.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				
				return true;
				}
			 });
		
		remset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				set1.setVisibility(View.GONE);
				set2.setVisibility(View.GONE);
				set3.setVisibility(View.GONE);
				set4.setVisibility(View.GONE);
				set5.setVisibility(View.GONE);
				lm.remove((int)(ind));
				sets_num = lm.size();
				if (sets_num > 0) {
					set1.setVisibility(View.VISIBLE);
					remset.setVisibility(View.GONE);
					addset.setVisibility(View.VISIBLE);
				}
				if (sets_num > 1) {
					set2.setVisibility(View.VISIBLE);
					remset.setVisibility(View.VISIBLE);
				}
				if (sets_num > 2) {
					set3.setVisibility(View.VISIBLE);
				}
				if (sets_num > 3) {
					set4.setVisibility(View.VISIBLE);
				}
				if (sets_num > 4) {
					set5.setVisibility(View.VISIBLE);
					addset.setVisibility(View.GONE);
				}
				_roundg(set1);
				_roundg(set2);
				_roundg(set3);
				_roundg(set4);
				_roundg(set5);
				_gsel(set1);
				ind = 0;
				h = lm.get((int)ind);
				train_resist = Double.parseDouble(h.get("w").toString());
				train_reps = Double.parseDouble(h.get("r").toString());
				resist.setText(h.get("w").toString());
				reps.setText(h.get("r").toString());
				resiat_seek_progress = train_resist - setting_min_resist;
				sb_top.setProgress((int)resiat_seek_progress);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
			}
		});
		
		set1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_roundg(set1);
				_roundg(set2);
				_roundg(set3);
				_roundg(set4);
				_roundg(set5);
				_gsel(set1);
				ind = 0;
				h = lm.get((int)ind);
				train_resist = Double.parseDouble(h.get("w").toString());
				train_reps = Double.parseDouble(h.get("r").toString());
				resist.setText(h.get("w").toString());
				reps.setText(h.get("r").toString());
				resiat_seek_progress = train_resist - setting_min_resist;
				sb_top.setProgress((int)resiat_seek_progress);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
			}
		});
		
		set2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_roundg(set1);
				_roundg(set2);
				_roundg(set3);
				_roundg(set4);
				_roundg(set5);
				_gsel(set2);
				ind = 1;
				h = lm.get((int)ind);
				train_resist = Double.parseDouble(h.get("w").toString());
				train_reps = Double.parseDouble(h.get("r").toString());
				resist.setText(h.get("w").toString());
				reps.setText(h.get("r").toString());
				resiat_seek_progress = train_resist - setting_min_resist;
				sb_top.setProgress((int)resiat_seek_progress);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
			}
		});
		
		set3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_roundg(set1);
				_roundg(set2);
				_roundg(set3);
				_roundg(set4);
				_roundg(set5);
				_gsel(set3);
				ind = 2;
				h = lm.get((int)ind);
				train_resist = Double.parseDouble(h.get("w").toString());
				train_reps = Double.parseDouble(h.get("r").toString());
				resist.setText(h.get("w").toString());
				reps.setText(h.get("r").toString());
				resiat_seek_progress = train_resist - setting_min_resist;
				sb_top.setProgress((int)resiat_seek_progress);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
			}
		});
		
		set4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_roundg(set1);
				_roundg(set2);
				_roundg(set3);
				_roundg(set4);
				_roundg(set5);
				_gsel(set4);
				ind = 3;
				h = lm.get((int)ind);
				train_resist = Double.parseDouble(h.get("w").toString());
				train_reps = Double.parseDouble(h.get("r").toString());
				resist.setText(h.get("w").toString());
				reps.setText(h.get("r").toString());
				resiat_seek_progress = train_resist - setting_min_resist;
				sb_top.setProgress((int)resiat_seek_progress);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
			}
		});
		
		set5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_roundg(set1);
				_roundg(set2);
				_roundg(set3);
				_roundg(set4);
				_roundg(set5);
				_gsel(set5);
				ind = 4;
				h = lm.get((int)ind);
				train_resist = Double.parseDouble(h.get("w").toString());
				train_reps = Double.parseDouble(h.get("r").toString());
				resist.setText(h.get("w").toString());
				reps.setText(h.get("r").toString());
				resiat_seek_progress = train_resist - setting_min_resist;
				sb_top.setProgress((int)resiat_seek_progress);
				bottom_seek_progress = train_reps - setting_min_reps;
				sb_bottom.setProgress((int)bottom_seek_progress);
			}
		});
		
		addset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				set1.setVisibility(View.GONE);
				set2.setVisibility(View.GONE);
				set3.setVisibility(View.GONE);
				set4.setVisibility(View.GONE);
				set5.setVisibility(View.GONE);
				lm.add(h);
				sets_num = lm.size();
				if (sets_num > 0) {
					set1.setVisibility(View.VISIBLE);
					remset.setVisibility(View.GONE);
					addset.setVisibility(View.VISIBLE);
				}
				if (sets_num > 1) {
					set2.setVisibility(View.VISIBLE);
					remset.setVisibility(View.VISIBLE);
					_press2();
				}
				if (sets_num > 2) {
					set3.setVisibility(View.VISIBLE);
					_press3();
				}
				if (sets_num > 3) {
					set4.setVisibility(View.VISIBLE);
					_press4();
				}
				if (sets_num > 4) {
					set5.setVisibility(View.VISIBLE);
					addset.setVisibility(View.GONE);
					_press5();
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
				timer0 = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_buttr(btn_OK);
								btn_OK.setTextColor(0xFFFFFFFF);
								FileUtil.writeFile(user, new Gson().toJson(lm));
								int0.setClass(getApplicationContext(), N1607aActivity.class);
								int0.putExtra("theuser", getIntent().getStringExtra("theuser"));
								startActivity(int0);
								finish();
							}
						});
					}
				};
				_timer.schedule(timer0, (int)(200));
			}
		});
	}
	
	private void initializeLogic() {
		_always_on();
		train_resist = Double.parseDouble(sharedpref.getString("train_resist", ""));
		train_reps = Double.parseDouble(sharedpref.getString("train_reps", ""));
		setting_min_resist = Double.parseDouble(sharedpref.getString("setting_min_resist", ""));
		setting_max_resist = Double.parseDouble(sharedpref.getString("setting_max_resist", ""));
		setting_min_reps = Double.parseDouble(sharedpref.getString("setting_min_reps", ""));
		setting_max_reps = Double.parseDouble(sharedpref.getString("setting_max_reps", ""));
		train_type = sharedpref.getString("train_type", "");
		_buttg(top_minus);
		_buttg(top_plus);
		_buttg(bottom_minus);
		_buttg(bottom_plus);
		_roundgg(lin_resist);
		_roundgg(lin_reps);
		_buttr(btn_OK);
		resist_seek_max = setting_max_resist - setting_min_resist;
		sb_top.setMax((int)resist_seek_max);
		resiat_seek_progress = train_resist - setting_min_resist;
		sb_top.setProgress((int)resiat_seek_progress);
		resist.setText(String.valueOf((long)(sb_top.getProgress() + setting_min_resist)));
		txt_reps.setTextColor(0xFFADADB8);
		reps.setTextColor(0xFFFF0000);
		bottom_seek_max = setting_max_reps - setting_min_reps;
		sb_bottom.setMax((int)bottom_seek_max);
		bottom_seek_progress = train_reps - setting_min_reps;
		sb_bottom.setProgress((int)bottom_seek_progress);
		reps.setText(String.valueOf((long)(sb_bottom.getProgress() + setting_min_reps)));
		sb_top.getProgressDrawable().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
		
		sb_top.getThumb().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
		
		sb_bottom.getProgressDrawable().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
		
		sb_bottom.getThumb().setColorFilter(Color.parseColor("white"), PorterDuff.Mode.SRC_IN);
		_roundgg(addset);
		_roundg(set1);
		_roundg(set2);
		_roundg(set4);
		_roundg(set3);
		_roundg(set5);
		set1.setVisibility(View.GONE);
		set2.setVisibility(View.GONE);
		set3.setVisibility(View.GONE);
		set4.setVisibility(View.GONE);
		set5.setVisibility(View.GONE);
		_roundgg(addset);
		_roundgg(remset);
		_roundg(set1);
		_roundg(set2);
		_roundg(set4);
		_roundg(set3);
		_roundg(set5);
		user = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(getIntent().getStringExtra("theuser")).concat("/sets.json"));
		h.put("r", "10");
		h.put("w", "20");
		lm.add(h);
		if (FileUtil.isExistFile(user)) {
			lm = new Gson().fromJson(FileUtil.readFile(user), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		else {
			if (false) {
				FileUtil.writeFile(user, new Gson().toJson(lm));
			}
		}
		ind = lm.size();
		if (ind > 0) {
			set1.setVisibility(View.VISIBLE);
			remset.setVisibility(View.GONE);
			addset.setVisibility(View.VISIBLE);
		}
		if (ind > 1) {
			set2.setVisibility(View.VISIBLE);
			remset.setVisibility(View.VISIBLE);
		}
		if (ind > 2) {
			set3.setVisibility(View.VISIBLE);
		}
		if (ind > 3) {
			set4.setVisibility(View.VISIBLE);
		}
		if (ind > 4) {
			set5.setVisibility(View.VISIBLE);
			addset.setVisibility(View.GONE);
		}
		sets_num = ind;
		_roundg(set1);
		_roundg(set2);
		_roundg(set3);
		_roundg(set4);
		_roundg(set5);
		_gsel(set1);
		ind = 0;
		h = lm.get((int)ind);
		train_resist = Double.parseDouble(h.get("w").toString());
		train_reps = Double.parseDouble(h.get("r").toString());
		resist.setText(h.get("w").toString());
		reps.setText(h.get("r").toString());
		resiat_seek_progress = train_resist - setting_min_resist;
		sb_top.setProgress((int)resiat_seek_progress);
		bottom_seek_progress = train_reps - setting_min_reps;
		sb_bottom.setProgress((int)bottom_seek_progress);
		top_minus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		top_plus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_resist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		resist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		bottom_minus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		bottom_plus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		reps.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		txt_reps.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		btn_OK.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		set1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		set2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		set3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		set4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		set5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		addset.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		remset.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rubikbold.ttf"), 0);
		
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
		gd.setColor(Color.parseColor("red"));//"#adadb8"));
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
	
	
	public void _gsel (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		float nnn=(float)30;
		int clr[] = new int[]{ Color.parseColor("#14141f"),Color.parseColor("#14141f"),Color.parseColor("#474752"),Color.parseColor("#1f1f2e")};//, Color.parseColor("black") };
		gd.setColors(clr);
		//gd.setColor(Color.parseColor("#80808090"));
		//gd.setCornerRadius((float)_radius);
		//gd.setbottomRightRadius((float)_radius);
		gd.setCornerRadii(new float[]{(float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn, (float)nnn});
		gd.setStroke(3, Color.parseColor("red"));
		//gd.setGradientType(android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT);
		//gd.setGradientRadius(500);
		
		_view.setBackground(gd);
	}
	
	
	public void _always_on () {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	public void _press5 () {
		_roundg(set1);
		_roundg(set2);
		_roundg(set3);
		_roundg(set4);
		_roundg(set5);
		_gsel(set5);
		ind = 4;
		h = lm.get((int)ind);
		train_resist = Double.parseDouble(h.get("w").toString());
		train_reps = Double.parseDouble(h.get("r").toString());
		resist.setText(h.get("w").toString());
		reps.setText(h.get("r").toString());
		resiat_seek_progress = train_resist - setting_min_resist;
		sb_top.setProgress((int)resiat_seek_progress);
		bottom_seek_progress = train_reps - setting_min_reps;
		sb_bottom.setProgress((int)bottom_seek_progress);
	}
	
	
	public void _press4 () {
		_roundg(set1);
		_roundg(set2);
		_roundg(set3);
		_roundg(set4);
		_roundg(set5);
		_gsel(set4);
		ind = 3;
		h = lm.get((int)ind);
		train_resist = Double.parseDouble(h.get("w").toString());
		train_reps = Double.parseDouble(h.get("r").toString());
		resist.setText(h.get("w").toString());
		reps.setText(h.get("r").toString());
		resiat_seek_progress = train_resist - setting_min_resist;
		sb_top.setProgress((int)resiat_seek_progress);
		bottom_seek_progress = train_reps - setting_min_reps;
		sb_bottom.setProgress((int)bottom_seek_progress);
	}
	
	
	public void _press3 () {
		_roundg(set1);
		_roundg(set2);
		_roundg(set3);
		_roundg(set4);
		_roundg(set5);
		_gsel(set3);
		ind = 2;
		h = lm.get((int)ind);
		train_resist = Double.parseDouble(h.get("w").toString());
		train_reps = Double.parseDouble(h.get("r").toString());
		resist.setText(h.get("w").toString());
		reps.setText(h.get("r").toString());
		resiat_seek_progress = train_resist - setting_min_resist;
		sb_top.setProgress((int)resiat_seek_progress);
		bottom_seek_progress = train_reps - setting_min_reps;
		sb_bottom.setProgress((int)bottom_seek_progress);
	}
	
	
	public void _press2 () {
		_roundg(set1);
		_roundg(set2);
		_roundg(set3);
		_roundg(set4);
		_roundg(set5);
		_gsel(set2);
		ind = 1;
		h = lm.get((int)ind);
		train_resist = Double.parseDouble(h.get("w").toString());
		train_reps = Double.parseDouble(h.get("r").toString());
		resist.setText(h.get("w").toString());
		reps.setText(h.get("r").toString());
		resiat_seek_progress = train_resist - setting_min_resist;
		sb_top.setProgress((int)resiat_seek_progress);
		bottom_seek_progress = train_reps - setting_min_reps;
		sb_bottom.setProgress((int)bottom_seek_progress);
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