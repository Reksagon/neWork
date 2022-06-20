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
    private boolean expand = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeviceNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);

        binding.editTextDevice.setText(sharedpref.getString("device_name", ""));

        binding.txtDeviceName2.setOnClickListener(ExpnadBar.clickDeviceName(this));
        binding.txtIpNumber2.setOnClickListener(ExpnadBar.clickIpNumber(this));
        binding.txtResistanceRatio2.setOnClickListener(ExpnadBar.clickResistanceRatio(this));
        binding.txtRangeOfResistance2.setOnClickListener(ExpnadBar.clickRangeOfResistance(this));
        binding.txtRangeOfMotion2.setOnClickListener(ExpnadBar.clickRangeOfMotion(this));
        binding.bttnCancel2.setOnClickListener(ExpnadBar.clickCancel(this));

        ExpnadBar.setSize(binding.txtDeviceName2, binding.txtIpNumber2,
                binding.txtResistanceRatio2, binding.txtRangeOfResistance2, binding.txtRangeOfMotion2);

        ExpnadBar.setStart(binding.barExpand2, binding.bttnCancel2, binding.txtDeviceName2, binding.txtIpNumber2,
                binding.txtResistanceRatio2, binding.txtRangeOfResistance2, binding.txtRangeOfMotion2, binding.arrow2);


        binding.bttnBar2.setOnClickListener(v->
        {
            if(!expand) {
                ExpnadBar.setExpand(binding.barExpand2, binding.bttnCancel2, binding.txtDeviceName2, binding.txtIpNumber2,
                        binding.txtResistanceRatio2, binding.txtRangeOfResistance2, binding.txtRangeOfMotion2, binding.arrow2);
                expand = true;
            }
            else
            {
                ExpnadBar.setHide(binding.barExpand2, binding.bttnCancel2, binding.txtDeviceName2, binding.txtIpNumber2,
                        binding.txtResistanceRatio2, binding.txtRangeOfResistance2, binding.txtRangeOfMotion2, binding.arrow2);
                expand = false;
            }
        });

        binding.bttnSave.setOnClickListener(v->
        {
            sharedpref.edit().putString("device_name", binding.editTextDevice.getText().toString()).apply();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), ChoosetypeActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpnadBar.setHide(binding.barExpand2, binding.bttnCancel2, binding.txtDeviceName2, binding.txtIpNumber2,
                binding.txtResistanceRatio2, binding.txtRangeOfResistance2, binding.txtRangeOfMotion2, binding.arrow2);
        expand = false;
    }
}