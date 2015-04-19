package com.safedrive.com.roadsafe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by Kishore on 4/19/15.
 */
public class PhoneCallReceiver extends BroadcastReceiver
{


    public void onReceive(Context context, Intent intent)
    {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        PhoneCallStateListener roadSafePhoneListener = new PhoneCallStateListener(context);
        telephony.listen(roadSafePhoneListener, PhoneCallStateListener.LISTEN_CALL_STATE);
    }

}