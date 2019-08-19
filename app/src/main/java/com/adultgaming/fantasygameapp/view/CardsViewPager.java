package com.adultgaming.fantasygameapp.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.adultgaming.fantasygameapp.R;
import com.adultgaming.fantasygameapp.common.AdLauncher;
import com.adultgaming.fantasygameapp.model.Card;
import com.adultgaming.fantasygameapp.model.CardUnit;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

public class CardsViewPager extends PagerAdapter {

    Context context;
    List<CardUnit> cardList;
    LayoutInflater layoutInflater;


    public CardsViewPager(Context context, List<CardUnit> cards) {
        this.context = context;
        this.cardList = cards;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ScrollView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item_card, container, false);

        TextView mTitle = itemView.findViewById(R.id.item_card_title);
        mTitle.setText(cardList.get(position).getTitle());

        TextView mDescription = itemView.findViewById(R.id.item_card_description);
        mDescription.setText(cardList.get(position).getDescription());

        ImageView imageView = itemView.findViewById(R.id.item_card_image);
        Glide.with(context).load(cardList.get(position).getImage()).into(imageView);

        container.addView(itemView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });
        AdLauncher.getInstance().launchAd();
        Log.d("test", "->>> " + cardList.get(position).getTitle());
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ScrollView)object);
    }
}

