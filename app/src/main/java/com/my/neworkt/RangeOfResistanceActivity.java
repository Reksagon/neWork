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
    private boolean expand = false;
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
        binding.txtDeviceName5.setOnClickListener(ExpnadBar.clickDeviceName(this));
        binding.txtIpNumber5.setOnClickListener(ExpnadBar.clickIpNumber(this));
        binding.txtResistanceRatio5.setOnClickListener(ExpnadBar.clickResistanceRatio(this));
        binding.txtRangeOfResistance5.setOnClickListener(ExpnadBar.clickRangeOfResistance(this));
        binding.txtRangeOfMotion5.setOnClickListener(ExpnadBar.clickRangeOfMotion(this));
        binding.bttnCancel5.setOnClickListener(ExpnadBar.clickCancel(this));

        ExpnadBar.setSize(binding.txtDeviceName5, binding.txtIpNumber5,
                binding.txtResistanceRatio5, binding.txtRangeOfResistance5, binding.txtRangeOfMotion5);

        ExpnadBar.setStart(binding.barExpand5, binding.bttnCancel5, binding.txtDeviceName5, binding.txtIpNumber5,
                binding.txtResistanceRatio5, binding.txtRangeOfResistance5, binding.txtRangeOfMotion5, binding.arrow5);


        binding.bttnBar5.setOnClickListener(v->
        {
            if(!expand) {
                ExpnadBar.setExpand(binding.barExpand5, binding.bttnCancel5, binding.txtDeviceName5, binding.txtIpNumber5,
                        binding.txtResistanceRatio5, binding.txtRangeOfResistance5, binding.txtRangeOfMotion5, binding.arrow5);
                expand = true;
            }
            else
            {
                ExpnadBar.setHide(binding.barExpand5, binding.bttnCancel5, binding.txtDeviceName5, binding.txtIpNumber5,
                        binding.txtResistanceRatio5, binding.txtRangeOfResistance5, binding.txtRangeOfMotion5, binding.arrow5);
                expand = false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpnadBar.setHide(binding.barExpand5, binding.bttnCancel5, binding.txtDeviceName5, binding.txtIpNumber5,
                binding.txtResistanceRatio5, binding.txtRangeOfResistance5, binding.txtRangeOfMotion5, binding.arrow5);
        expand = false;
    }

}