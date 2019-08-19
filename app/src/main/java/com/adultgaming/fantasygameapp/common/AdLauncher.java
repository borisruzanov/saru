package com.adultgaming.fantasygameapp.common;

import android.content.Intent;
import android.util.Log;

import com.adultgaming.fantasygameapp.MyApp;
import com.adultgaming.fantasygameapp.utils.SharPrefManager;
import com.adultgaming.fantasygameapp.view.AdsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Checking if needed to call Ad view
 */
public class AdLauncher {

    private static final java.lang.String PATTERN = "EEE MMM dd HH:mm:ss z yyyy";

    private static AdLauncher mInstance;

    private AdLauncher() {
    }

    public static AdLauncher getInstance() {
        if (mInstance == null) {
            mInstance = new AdLauncher();
        }
        return mInstance;
    }


    public void launchAd() {

        if (SharPrefManager.getInstance().grabTriggertTime().equals("")) {
            //First time launching and setting trigger time
            settingTriggerTimeFirstOpen();
        } else {
            //Grabbing adTimeLeft and if <= 0 show Ad
            try {
                if (!SharPrefManager.getInstance().grabTriggertTime().equals("")) {
                    Date triggerDate = new SimpleDateFormat(PATTERN).parse(SharPrefManager.getInstance().grabTriggertTime());
//                    if (getCurrentTime().after(triggerDate)) {
                    if (Integer.valueOf(SharPrefManager.getInstance().grabAdTimeLeft()) <= 0) {
                        showAdDialog();
                    }
//                    } else {
                        Log.d("zzz", "time before " + triggerDate.toString());
//                    }
                } else {
                    SharPrefManager.getInstance().setTriggerTime(getTriggerTime().toString());
                }

            } catch (ParseException e) {
                //TODO catch
                e.printStackTrace();
            }
        }



    }

    private void settingTriggerTimeFirstOpen() {
        SharPrefManager.getInstance().setTriggerTime(AdLauncher.getInstance().getTriggerTime().toString());
    }

    /**
     * Getting trigger time which is current time plus delay time
     *
     * @return
     */
    public Date getTriggerTime() {
        Calendar date = Calendar.getInstance();
        date.setTime(getCurrentTime());
        date.add(Calendar.MINUTE, Integer.valueOf(SharPrefManager.getInstance().grabAdIntervalTime()));
        Date triggerTime = date.getTime();
        return triggerTime;
    }

    /**
     * Returning current time
     *
     * @return
     */
    public Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    public String getCurrentTimeAsString() {
        return Calendar.getInstance().getTime().toString();
    }

    private void showAdDialog() {
        SharPrefManager.getInstance().setTriggerTime(getTriggerTime().toString());
        Intent myIntent = new Intent(MyApp.getAppContext(), AdsActivity.class);
        MyApp.getAppContext().startActivity(myIntent);
    }

}

