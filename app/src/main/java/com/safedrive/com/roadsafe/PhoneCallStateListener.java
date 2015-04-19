package com.safedrive.com.roadsafe;

import java.lang.reflect.Method;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

public class PhoneCallStateListener extends PhoneStateListener {

    private Context context;

    public PhoneCallStateListener(Context context){
        this.context = context;
    }
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {



            switch (state) {

                case TelephonyManager.CALL_STATE_RINGING:


                    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    //Turn ON the mute
                    audioManager.setStreamMute(AudioManager.STREAM_RING, true);
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                    try {
                        Class clazz = Class.forName(telephonyManager.getClass().getName());
                        Method method = clazz.getDeclaredMethod("getITelephony");
                        ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
                        if (true) {
                            //telephonyService.silenceRinger();//Security exception problem
                            telephonyService = (ITelephony) method.invoke(telephonyManager);
                            telephonyService.silenceRinger();
                            telephonyService.endCall();
                        }
                    } catch (Exception e) {

                    }
                    //Turn OFF the mute
                    audioManager.setStreamMute(AudioManager.STREAM_RING, false);
                    break;
                case PhoneStateListener.LISTEN_CALL_STATE:

            }
            super.onCallStateChanged(state, incomingNumber);

    }}