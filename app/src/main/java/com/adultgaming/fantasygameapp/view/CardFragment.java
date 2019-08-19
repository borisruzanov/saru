package com.adultgaming.fantasygameapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adultgaming.fantasygameapp.R;

import androidx.fragment.app.Fragment;

public class CardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_card, container, false);
        Log.d("zzz","CardFragmetn");
        return rootView;
    }
}
