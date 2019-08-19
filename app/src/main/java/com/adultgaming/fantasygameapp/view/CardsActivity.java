package com.adultgaming.fantasygameapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adultgaming.fantasygameapp.R;
import com.adultgaming.fantasygameapp.common.AdLauncher;
import com.adultgaming.fantasygameapp.data.CardActivityInteractor;
import com.adultgaming.fantasygameapp.model.Card;
import com.adultgaming.fantasygameapp.model.CardUnit;
import com.adultgaming.fantasygameapp.presenter.CardActivityPresenter;
import com.adultgaming.fantasygameapp.utils.SharPrefManager;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class CardsActivity extends AppCompatActivity implements CardActivityInterface {

    private ViewPager viewPager;
    List<Card> cardList;
    Button mNextButton;
    Button mPreviousButton;
    View mColorView;
    CardActivityPresenter mCardPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mCardPresenter = new CardActivityPresenter(new CardActivityInteractor(), this);
        mCardPresenter.registerSubscribers();
        Intent intent = getIntent();
        mColorView = findViewById(R.id.cards_color_view);
        if (intent.hasExtra("color") && intent.getStringExtra("color").equals("red")){
            mColorView.setBackgroundColor(getResources().getColor(R.color.redDark));
            mCardPresenter.getColorCardsList(SharPrefManager.getInstance().grabUserLanguageShPref(), intent.getStringExtra("title"), intent.getStringExtra("color"));
        } else if (intent.hasExtra("color") && intent.getStringExtra("color").equals("yellow")){
            mColorView.setBackgroundColor(getResources().getColor(R.color.yellowDark));
            mCardPresenter.getColorCardsList(SharPrefManager.getInstance().grabUserLanguageShPref(), intent.getStringExtra("title"), intent.getStringExtra("color"));
        } else if (intent.hasExtra("color") && intent.getStringExtra("color").equals("green")){
            mColorView.setBackgroundColor(getResources().getColor(R.color.greenDark));
            mCardPresenter.getColorCardsList(SharPrefManager.getInstance().grabUserLanguageShPref(), intent.getStringExtra("title"), intent.getStringExtra("color"));
        } else {
            mColorView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            mCardPresenter.getCardsList(SharPrefManager.getInstance().grabUserLanguageShPref(), intent.getStringExtra("title"));
        }




        AdLauncher.getInstance().launchAd();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mCardPresenter.unregisterSubscribers();
    }

    @Override
    public void showCardsList(List<CardUnit> cardList) {
        viewPager = (ViewPager) findViewById(R.id.cards_view_pager);
        viewPager.setAdapter(new CardsViewPager(this, cardList));

        mNextButton = findViewById(R.id.card_button_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(nextItem(), true); //getItem(-1) for previous
            }
        });

        mPreviousButton = findViewById(R.id.card_button_previous);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(previousItem(), true);
            }
        });
    }

    private int nextItem() {
        return viewPager.getCurrentItem() + 1;
    }
    private int previousItem() {
        return viewPager.getCurrentItem() -1;
    }
}
