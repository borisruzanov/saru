package com.adultgaming.fantasygameapp.utils;

import android.util.Log;

import com.adultgaming.fantasygameapp.model.CardEvent;
import com.adultgaming.fantasygameapp.model.CardUnit;
import com.adultgaming.fantasygameapp.model.Game;
import com.adultgaming.fantasygameapp.model.GamesEvent;
import com.adultgaming.fantasygameapp.model.LanguageUn;
import com.adultgaming.fantasygameapp.model.LanguageEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FirebaseManager {

    private final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    private static FirebaseManager mInstance;

    private int res = 0;

    private FirebaseManager() {
    }

    /**
     * Getting instance of FirebaseManager singleton
     *
     * @return
     */
    public static FirebaseManager getInstance() {
        if (mInstance == null) {
            mInstance = new FirebaseManager();
        }
        return mInstance;
    }


    public void getGamesList(String lang) {
        DatabaseReference mReference = mDatabase.getReference("Games/" + lang);
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<Game> gameUnitList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Game game = postSnapshot.getValue(Game.class);
                    gameUnitList.add(new Game(game.getBackground(), game.getDescription(), game.getTag(), game.getTitle(), game.getType()));
                }
                EventBus.getDefault().post(new GamesEvent(gameUnitList));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("test", "Error " + databaseError.getMessage());

            }
        });
    }

    public void getCardList(String language, String gameTitle) {
        DatabaseReference mReference = mDatabase.getReference("Cards/" + language + "/" + gameTitle);
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<CardUnit> cardList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CardUnit card = postSnapshot.getValue(CardUnit.class);
                    cardList.add(new CardUnit(card.getTitle(), card.getDescription(), card.getImage(), card.getGame()));
//                    cardList.add(new CardUnit(card.getDescription(),card.getGame(),card.getImage(),card.getTitle()));
                }
                EventBus.getDefault().post(new CardEvent(cardList));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("test", "Error " + databaseError.getMessage());
            }
        });
    }

    public void getLanguages() {
        DatabaseReference mReference = mDatabase.getReference("Languages");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<LanguageUn> languageUnList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LanguageUn languageUn = postSnapshot.getValue(LanguageUn.class);
                    languageUnList.add(new LanguageUn(languageUn.getTitle(), languageUn.getImage(), languageUn.getShortLang()));
//                    cardList.add(new CardUnit(card.getDescription(),card.getGame(),card.getImage(),card.getTitle()));
                }
                EventBus.getDefault().post(new LanguageEvent(languageUnList));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("test", "Error " + databaseError.getMessage());
            }
        });
    }

    public void getColorCardList(String language, String gameTitle, String color) {
        DatabaseReference mReference = mDatabase.getReference("Cards/" + language + "/" + gameTitle + "/" + color);
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<CardUnit> cardList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CardUnit card = postSnapshot.getValue(CardUnit.class);
                    cardList.add(new CardUnit(card.getTitle(), card.getDescription(), card.getImage(), card.getGame()));
//                    cardList.add(new CardUnit(card.getDescription(),card.getGame(),card.getImage(),card.getTitle()));
                }
                EventBus.getDefault().post(new CardEvent(cardList));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("test", "Error " + databaseError.getMessage());
            }
        });
    }

    /**
     * Getting config with all settings from Firebase
     */
    public void getConfig() {
        DatabaseReference mReference = mDatabase.getReference("Settings");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    SharPrefManager.getInstance().setConfig(postSnapshot.getKey(), postSnapshot.getValue().toString());
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("test", "Error " + databaseError.getMessage());
            }
        });
    }
}
