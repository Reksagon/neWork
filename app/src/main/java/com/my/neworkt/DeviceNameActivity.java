package com.my.neworkt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.my.neworkt.databinding.ActivityDeviceNameBinding;
import com.my.neworkt.databinding.Choosetype2Binding;

public class DeviceNameActivity extends Activity {

    ActivityDeviceNameBinding binding;
    SharedPreferences sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeviceNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);

        binding.editTextDevice.setText(sharedpref.getString("device_name", ""));

        binding.bttnSave.setOnClickListener(v->
        {
            sharedpref.edit().putString("device_name", binding.editTextDevice.getText().toString()).apply();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), ChoosetypeActivity.class);
            startActivity(intent);
            finish();
        });

    }
}