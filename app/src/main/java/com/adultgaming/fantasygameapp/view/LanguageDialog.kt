package com.adultgaming.fantasygameapp.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adultgaming.fantasygameapp.MyApp
import com.adultgaming.fantasygameapp.R
import com.adultgaming.fantasygameapp.adapter.LanguageAdapter
import com.adultgaming.fantasygameapp.model.GamesEvent
import com.adultgaming.fantasygameapp.model.LanguageEvent
import com.adultgaming.fantasygameapp.model.LanguageUn
import com.adultgaming.fantasygameapp.utils.FirebaseManager
import com.adultgaming.fantasygameapp.utils.SharPrefManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LanguageDialog(context: Context) : Dialog(context), LanguageAdapter.ItemClickListener {


    private val mRussianFlagLink = "https://cdn3.iconfinder.com/data/icons/142-mini-country-flags-16x16px/32/flag-russia2x.png"
    private val mEnglishFlagLink = "https://cdn3.iconfinder.com/data/icons/142-mini-country-flags-16x16px/32/flag-united-kingdom2x.png"

    private lateinit var mLanguageAdapter: LanguageAdapter
    private lateinit var mButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_languages)
        mButton = findViewById(R.id.language_submit_button)
        mButton.isEnabled = false
        mButton.setOnClickListener(View.OnClickListener {
            FirebaseManager.getInstance().getGamesList(SharPrefManager.getInstance().grabUserLanguageShPref())
            dismiss()
        })
        //TODO Replace with list from firebase
        val mLanguageUnList: MutableList<LanguageUn> = mutableListOf(LanguageUn("Russian", mRussianFlagLink, "ru"), LanguageUn("English", mEnglishFlagLink, "eng"))
        FirebaseManager.getInstance().getLanguages()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun inflateLanguagesList(event: LanguageEvent) {
        val mRecyclerView = findViewById<RecyclerView>(R.id.recycle_languages)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mLanguageAdapter = LanguageAdapter(event.languageUns, context)
        mLanguageAdapter.setClickListener(this)
        mRecyclerView.adapter = mLanguageAdapter
    }

    override fun onItemClick(view: View, position: Int) {
        mButton.setTextColor(MyApp.getAppContext().resources.getColor(android.R.color.white))
        mButton.setBackgroundColor(MyApp.getAppContext().resources.getColor(R.color.colorAccent))
        mButton.setText(mLanguageAdapter.getItem(position).title)

        var language = mLanguageAdapter.getItem(position).shortLang
        SharPrefManager.getInstance().setUserLanguageShPref(mLanguageAdapter.getItem(position).shortLang)
        mButton.isEnabled = true
    }

}
