package com.adultgaming.fantasygameapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adultgaming.fantasygameapp.*
import com.adultgaming.fantasygameapp.adapter.GamesAdapter
import com.adultgaming.fantasygameapp.common.AdLauncher
import com.adultgaming.fantasygameapp.data.MainInteractor
import com.adultgaming.fantasygameapp.model.Game
import com.adultgaming.fantasygameapp.presenter.MainPresenter
import com.adultgaming.fantasygameapp.utils.SharPrefManager

class MainActivity : AppCompatActivity(), MainInterface, GamesAdapter.ItemClickListener {

    private val mMainPresenter = MainPresenter(this, MainInteractor())
    private lateinit var mGamesAdapter: GamesAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTimeLeft: TextView
//    private val mAd: RewardedVideoAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById<RecyclerView>(R.id.recycle_games)
        mTimeLeft = findViewById<TextView>(R.id.main_minutes)
        mTimeLeft.text = mMainPresenter.grabAdTimeLeft()
        mMainPresenter.registerSubscribers()
        mMainPresenter.checkingLanguage()
        mMainPresenter.getGameList(SharPrefManager.getInstance().grabUserLanguageShPref())
        Handler().postDelayed({
            AdLauncher.getInstance().launchAd()
        }, 5000)

    }



    override fun onStop() {
        super.onStop()
        mMainPresenter.unregisterSubscribers()
    }

    /**
     * Showing the list of the games
     */
    override fun showGamesList(language: MutableList<Game>) {
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mGamesAdapter = GamesAdapter(language, this)
        mGamesAdapter.setClickListener(this)
        mRecyclerView.adapter = mGamesAdapter
    }

    /**
     * Calling languageUns dialog
     */
    override fun callLanguageDialog() {
        val mDialog = LanguageDialog(this)
        mDialog.setCancelable(false)
        mDialog.show()
    }

    /**
     * Sending explicit intent to color activity if game have colors
     */
    override fun sendToColorActivity(title: String) {
        val intent = Intent(this, ColorsActivity::class.java)
        var s = title
        intent.putExtra("title", title)
        startActivity(intent)
    }

    /**
     * Sending explicit intent to color activity if game DON'T have colors
     */
    override fun sendToCardsActivity(title: String) {
        val intent = Intent(this, CardsActivity::class.java)
        intent.putExtra("title", title)
        startActivity(intent)
    }


    /**
     * Games list click listener
     */
    override fun onItemClick(view: View, position: Int) {
        mMainPresenter.intentToNextActivity(mGamesAdapter.getItem(position).type, mGamesAdapter.getItem(position).title)
    }
}
