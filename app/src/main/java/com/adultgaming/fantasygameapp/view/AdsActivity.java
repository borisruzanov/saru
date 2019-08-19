package com.adultgaming.fantasygameapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adultgaming.fantasygameapp.R;
import com.adultgaming.fantasygameapp.utils.SharPrefManager;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdsActivity extends AppCompatActivity {

    private RewardedVideoAd mRewardedVideoAd;
    private Button mWatchVideoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        mWatchVideoButton = findViewById(R.id.watch_video);
        mWatchVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                } else {
                    Toast.makeText(getBaseContext(),
                            "Please check online connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                SharPrefManager.getInstance().setAdTriggerTime();
                finish();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRewardedVideoAd.resume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRewardedVideoAd.pause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRewardedVideoAd.destroy(this);
    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }
    }

    private void doSomething() {
    }

    private boolean shouldAllowBack() {
        return true;
    }
}
