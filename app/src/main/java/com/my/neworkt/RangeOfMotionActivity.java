package com.my.neworkt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.my.neworkt.databinding.ActivityRangeOfMotionBinding;
import com.my.neworkt.databinding.ActivityRangeOfResistanceBinding;

import java.util.HashMap;

public class RangeOfMotionActivity extends Activity {

    ActivityRangeOfMotionBinding binding;
    SharedPreferences sharedpref;
    RequestNetwork netreq;
    RequestNetwork.RequestListener _netreq_request_listener;
    private boolean expand = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRangeOfMotionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpref = getSharedPreferences("nwtsp0", Activity.MODE_PRIVATE);

        netreq = new RequestNetwork(this);
        binding.bttnStart.setOnClickListener(v->
        {
            netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/calcmd.php?bound=start")), "is_start", _netreq_request_listener);
        });

        binding.bttnEnd.setOnClickListener(v->
        {
            netreq.startRequestNetwork(RequestNetworkController.POST, "http://".concat(sharedpref.getString("ip", "").concat("/calcmd.php?bound=stop")), "is_stop", _netreq_request_listener);
        });

        binding.bttnSave.setOnClickListener(v->
        {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), ChoosetypeActivity.class);
            startActivity(intent);
            finish();
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

        binding.txtDeviceName4.setOnClickListener(ExpnadBar.clickDeviceName(this));
        binding.txtIpNumber4.setOnClickListener(ExpnadBar.clickIpNumber(this));
        binding.txtResistanceRatio4.setOnClickListener(ExpnadBar.clickResistanceRatio(this));
        binding.txtRangeOfResistance4.setOnClickListener(ExpnadBar.clickRangeOfResistance(this));
        binding.txtRangeOfMotion4.setOnClickListener(ExpnadBar.clickRangeOfMotion(this));
        binding.bttnCancel4.setOnClickListener(ExpnadBar.clickCancel(this));

        ExpnadBar.setSize(binding.txtDeviceName4, binding.txtIpNumber4,
                binding.txtResistanceRatio4, binding.txtRangeOfResistance4, binding.txtRangeOfMotion4);

        ExpnadBar.setStart(binding.barExpand4, binding.bttnCancel4, binding.txtDeviceName4, binding.txtIpNumber4,
                binding.txtResistanceRatio4, binding.txtRangeOfResistance4, binding.txtRangeOfMotion4, binding.arrow4);


        binding.bttnBar6.setOnClickListener(v->
        {
            if(!expand) {
                ExpnadBar.setExpand(binding.barExpand4, binding.bttnCancel4, binding.txtDeviceName4, binding.txtIpNumber4,
                        binding.txtResistanceRatio4, binding.txtRangeOfResistance4, binding.txtRangeOfMotion4, binding.arrow4);
                expand = true;
            }
            else
            {
                ExpnadBar.setHide(binding.barExpand4, binding.bttnCancel4, binding.txtDeviceName4, binding.txtIpNumber4,
                        binding.txtResistanceRatio4, binding.txtRangeOfResistance4, binding.txtRangeOfMotion4, binding.arrow4);
                expand = false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpnadBar.setHide(binding.barExpand4, binding.bttnCancel4, binding.txtDeviceName4, binding.txtIpNumber4,
                binding.txtResistanceRatio4, binding.txtRangeOfResistance4, binding.txtRangeOfMotion4, binding.arrow4);
        expand = false;
    }
}