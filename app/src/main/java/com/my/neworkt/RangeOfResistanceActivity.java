package com.my.neworkt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.my.neworkt.databinding.ActivityIpnumberBinding;
import com.my.neworkt.databinding.ActivityRangeOfResistanceBinding;

public class RangeOfResistanceActivity extends Activity {

    ActivityRangeOfResistanceBinding binding;
    SharedPreferences sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRangeOfResistanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);

        binding.editTextMinimum.setText(sharedpref.getString("setting_min_resist", ""));
        binding.editTextMaxmimum.setText(sharedpref.getString("setting_max_resist", ""));

        binding.bttnSave.setOnClickListener(v->
        {
            sharedpref.edit().putString("setting_min_resist", binding.editTextMinimum.getText().toString()).apply();
            sharedpref.edit().putString("setting_max_resist", binding.editTextMaxmimum.getText().toString()).apply();

            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), ChoosetypeActivity.class);
            startActivity(intent);
            finish();
        });

    }
}