package com.my.neworkt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.my.neworkt.databinding.ActivityDeviceNameBinding;
import com.my.neworkt.databinding.ActivityIpnumberBinding;

public class IPNumberActivity extends Activity {

    ActivityIpnumberBinding binding;
    SharedPreferences sharedpref;
    private boolean expand = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIpnumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);

        binding.editTextIpnumber.setText(sharedpref.getString("ip", ""));

        binding.bttnSave.setOnClickListener(v->
        {
            sharedpref.edit().putString("ip", binding.editTextIpnumber.getText().toString()).apply();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), ChoosetypeActivity.class);
            startActivity(intent);
            finish();
        });

        binding.txtDeviceName3.setOnClickListener(ExpnadBar.clickDeviceName(this));
        binding.txtIpNumber3.setOnClickListener(ExpnadBar.clickIpNumber(this));
        binding.txtResistanceRatio3.setOnClickListener(ExpnadBar.clickResistanceRatio(this));
        binding.txtRangeOfResistance3.setOnClickListener(ExpnadBar.clickRangeOfResistance(this));
        binding.txtRangeOfMotion3.setOnClickListener(ExpnadBar.clickRangeOfMotion(this));
        binding.bttnCancel3.setOnClickListener(ExpnadBar.clickCancel(this));

        ExpnadBar.setSize(binding.txtDeviceName3, binding.txtIpNumber3,
                binding.txtResistanceRatio3, binding.txtRangeOfResistance3, binding.txtRangeOfMotion3);

        ExpnadBar.setStart(binding.barExpand3, binding.bttnCancel3, binding.txtDeviceName3, binding.txtIpNumber3,
                binding.txtResistanceRatio3, binding.txtRangeOfResistance3, binding.txtRangeOfMotion3, binding.arrow3);


        binding.bttnBar3.setOnClickListener(v->
        {
            if(!expand) {
                ExpnadBar.setExpand(binding.barExpand3, binding.bttnCancel3, binding.txtDeviceName3, binding.txtIpNumber3,
                        binding.txtResistanceRatio3, binding.txtRangeOfResistance3, binding.txtRangeOfMotion3, binding.arrow3);
                expand = true;
            }
            else
            {
                ExpnadBar.setHide(binding.barExpand3, binding.bttnCancel3, binding.txtDeviceName3, binding.txtIpNumber3,
                        binding.txtResistanceRatio3, binding.txtRangeOfResistance3, binding.txtRangeOfMotion3, binding.arrow3);
                expand = false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpnadBar.setHide(binding.barExpand3, binding.bttnCancel3, binding.txtDeviceName3, binding.txtIpNumber3,
                binding.txtResistanceRatio3, binding.txtRangeOfResistance3, binding.txtRangeOfMotion3, binding.arrow3);
        expand = false;
    }
}