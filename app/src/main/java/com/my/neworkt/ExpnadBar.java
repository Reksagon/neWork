package com.my.neworkt;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class ExpnadBar {

    private static final int anim_distance = -2500;
    private static final int time_animate = 550;

    public static void setSize(TextView txtDeviceName, TextView txtIpNumber,
                               TextView txtResistanceRatio, TextView txtRangeOfResistance, TextView txtRangeOfMotion)
    {
        txtDeviceName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        txtIpNumber.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        txtResistanceRatio.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        txtRangeOfResistance.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        txtRangeOfMotion.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
    }

    public static View.OnClickListener clickCancel(Context context)
    {
        return new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent();
                intent0.setClass(context, ChoosetypeActivity.class);
                context.startActivity(intent0);
            }
        };
    }

    public static View.OnClickListener clickDeviceName(Context context)
    {
       return new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent();
                intent0.setClass(context, DeviceNameActivity.class);
                context.startActivity(intent0);
            }
        };
    }

    public static View.OnClickListener clickIpNumber(Context context)
    {
        return new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent();
                intent0.setClass(context, IPNumberActivity.class);
                context.startActivity(intent0);
            }
        };
    }

    public static View.OnClickListener clickResistanceRatio(Context context)
    {
        return new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent();
                intent0.setClass(context, ResistanceRatioActivity.class);
                context.startActivity(intent0);
            }
        };
    }

    public static View.OnClickListener clickRangeOfResistance(Context context)
    {
        return new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent();
                intent0.setClass(context, RangeOfResistanceActivity.class);
                context.startActivity(intent0);
            }
        };
    }

    public static View.OnClickListener clickRangeOfMotion(Context context)
    {
        return new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent();
                intent0.setClass(context, RangeOfMotionActivity.class);
                context.startActivity(intent0);
            }
        };
    }

    public static void setStart(View view1, View view2, View view3, View view4, View view5, View view6, View view7, View view8 )
    {
        view1.setTranslationX(anim_distance);
        view2.setTranslationX(anim_distance);
        view3.setTranslationX(anim_distance);
        view4.setTranslationX(anim_distance);
        view5.setTranslationX(anim_distance);
        view6.setTranslationX(anim_distance);
        view7.setTranslationX(anim_distance);
        view8.setTranslationX(anim_distance);
    }

    public static void setExpand(View view1, View view2, View view3, View view4, View view5, View view6, View view7, View view8 )
    {
        view1.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view2.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view3.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view4.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view5.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view6.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view7.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view8.animate().translationX(0).setDuration(time_animate).setInterpolator(new LinearInterpolator());
    }

    public static void setHide(View view1, View view2, View view3, View view4, View view5, View view6, View view7, View view8 )
    {
        view1.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view2.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view3.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view4.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view5.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view6.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view7.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
        view8.animate().translationX(anim_distance).setDuration(time_animate).setInterpolator(new LinearInterpolator());
    }
}
