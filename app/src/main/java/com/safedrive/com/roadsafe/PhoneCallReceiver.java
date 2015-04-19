package com.safedrive.com.roadsafe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import android.content.Context;

import android.media.AudioManager;
import android.app.Application;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;
/**
 * Created by Kishore on 4/19/15.
 */
public class PhoneCallReceiver extends BroadcastReceiver
{

    private double speed;
    private int speedLimit;
    private boolean appState = true;
    private Context mContext;

    ITelephony telephonyService;
    public void onReceive(Context context, Intent intent) {

        this.mContext = MainActivity.context;

        speed = MainActivity.speed;
        speedLimit = MainActivity.speedLimit;
        appState = MainActivity.appState;
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //int events = PhoneStateListener.LISTEN_CALL_STATE;
        //telephony.listen(phoneStateListener, events);
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        //Turn ON the mute
        audioManager.setStreamMute(AudioManager.STREAM_RING, true);
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        try {
            Class clazz = Class.forName(telephonyManager.getClass().getName());
            Method method = clazz.getDeclaredMethod("getITelephony");
            method.setAccessible(true);
            telephonyService = (ITelephony) method.invoke(telephonyManager);
            if (speed >= speedLimit && appState) {
                //telephonyService.silenceRinger();//Security exception problem
                telephonyService = (ITelephony) method.invoke(telephonyManager);
                telephonyService.silenceRinger();
                telephonyService.endCall();
            }
        } catch (Exception e) {

        }


}




}