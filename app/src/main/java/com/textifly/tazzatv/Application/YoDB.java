package com.textifly.tazzatv.Application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.textifly.tazzatv.Utils.CustomPreference;

public class YoDB extends Application {
    private static Context mContext;
    private static CustomPreference sharedPreferences;

    public static final String CHANNEL_ID = "notification_channel";


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //AndroidNetworking.initialize(mContext);

        /*OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();*/

        createNotificationChannel();
    }

    public static CustomPreference getPref() {
        if (sharedPreferences == null) {
            sharedPreferences = new CustomPreference(mContext);
        }
        return sharedPreferences;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "notification channel",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}
