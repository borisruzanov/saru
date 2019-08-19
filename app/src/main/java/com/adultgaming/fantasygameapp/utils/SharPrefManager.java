package com.adultgaming.fantasygameapp.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.adultgaming.fantasygameapp.MyApp;
import com.adultgaming.fantasygameapp.common.AdLauncher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class SharPrefManager {

    private SharedPreferences prefs = MyApp.getAppContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    private static SharPrefManager mInstance;
    private static final String AD_INTERVAL = "ad_interval";
    private static final String AD_TIME_LEFT = "ad_time_left";
    private final static String PREF_NAME = "preferences";
    private final static String LANGUAGE = "language";
    private final static String TRIGGER_TIME = "trigger_time";

    private SharPrefManager() {
    }

    /**
     * Getting instance of SharPref singleton
     * @return
     */
    public static SharPrefManager getInstance(){
        if (mInstance == null){
            mInstance = new SharPrefManager();
        }
        return mInstance;
    }

    /**
     * Set user language
     */
    public void setUserLanguageShPref(String language) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LANGUAGE, language);
        editor.apply();
    }

    /**
     * Get user language
     */
    public String grabUserLanguageShPref() {
        String result = "";
        if (prefs.getString(LANGUAGE, null) != null) {
            result =  prefs.getString(LANGUAGE, null);
        }
        return result;
    }

    public void setTriggerTime(String time) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TRIGGER_TIME, time);
        editor.apply();
    }

    public String grabTriggertTime() {
        String result = "";
        if (prefs.getString(TRIGGER_TIME, null) != null) {
            result =  prefs.getString(TRIGGER_TIME, null);
        }
//        else {
//            result = AdLauncher.getInstance().getTriggerTime().toString();
//        }
        return result;
    }

    public String grabAdTimeLeft() {
        String result = "";
        String triggerTime = grabTriggertTime();
        String currentTime = AdLauncher.getInstance().getCurrentTimeAsString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date triggerTimeDate = null;
        try {
            triggerTimeDate = simpleDateFormat.parse(triggerTime);
            Date currentTimeDate = simpleDateFormat.parse(currentTime);
            long difference = triggerTimeDate.getTime() - currentTimeDate.getTime();
            long days = (int) (difference / (1000*60*60*24));
            long hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            result = String.valueOf(min);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void setConfig(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set interval time from Firebase
     */
    public void setAdIntervalTime() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(AD_INTERVAL, String.valueOf(grabAdIntervalTime()));
        editor.apply();
    }

    public int grabAdIntervalTime() {
        String result = "";
        if (prefs.getString(AD_INTERVAL, null) != null) {
            result =  prefs.getString(AD_INTERVAL, null);
        }
        return Integer.valueOf(result);
    }

    public void setAdTriggerTime() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(AD_TIME_LEFT, String.valueOf(AdLauncher.getInstance().getTriggerTime()));
        editor.apply();
    }


}
