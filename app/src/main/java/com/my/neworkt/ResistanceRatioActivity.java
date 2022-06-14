package com.my.neworkt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.my.neworkt.databinding.ActivityDeviceNameBinding;
import com.my.neworkt.databinding.ActivityResistanceRatioBinding;

import java.util.HashMap;

public class ResistanceRatioActivity extends Activity {

    ActivityResistanceRatioBinding binding;
    SharedPreferences sharedpref;
    RequestNetwork netreq;
    RequestNetwork.RequestListener _netreq_request_listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResistanceRatioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);

        binding.editTextMultiplier.setText(sharedpref.getString("res_mul", ""));
        binding.editTextDevider.setText(sharedpref.getString("res_div", ""));

        binding.bttnSave.setOnClickListener(v->
        {
            sharedpref.edit().putString("res_mul", binding.editTextMultiplier.getText().toString()).apply();
            sharedpref.edit().putString("res_div", binding.editTextDevider.getText().toString()).apply();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), ChoosetypeActivity.class);
            startActivity(intent);
            finish();
        });

        netreq = new RequestNetwork(this);
        binding.bttnResiste.setOnClickListener(v->
        {
            netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/wr_kg_sec.php?cont=1&kg=")).concat(String.valueOf((long)((Double.parseDouble(binding.editTextReferenE.getText().toString()) * Double.parseDouble(binding.editTextMultiplier.getText().toString())) / Double.parseDouble(binding.editTextDevider.getText().toString())))), "is_ref", _netreq_request_listener);
        });


        _netreq_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
                final String _tag = _param1;
                final String _response = _param2;
                final HashMap<String, Object> _responseHeaders = _param3;

            }

            @Override
            public void onErrorResponse(String _param1, String _param2) {
                final String _tag = _param1;
                final String _message = _param2;

            }
        };
    }
}