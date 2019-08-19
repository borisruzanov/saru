package com.adultgaming.fantasygameapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adultgaming.fantasygameapp.R
import com.adultgaming.fantasygameapp.common.AdLauncher

class ColorsActivity: AppCompatActivity(), View.OnClickListener {

    lateinit var mRedTextView: TextView
    lateinit var mYellowTextView: TextView
    lateinit var mGreenTextView: TextView
    val RED: String = "red"
    val YELLOW: String = "yellow"
    val GREEN: String = "green"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        Log.d("test", "on create")

        mRedTextView = findViewById(R.id.colors_red_title)
        mYellowTextView = findViewById(R.id.colors_yellow_title)
        mGreenTextView = findViewById(R.id.colors_green_title)

        mRedTextView.setOnClickListener(this)
        mYellowTextView.setOnClickListener(this)
        mGreenTextView.setOnClickListener(this)
        AdLauncher.getInstance().launchAd()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.colors_red_title -> sendIntentToCardActivity(RED)
            R.id.colors_yellow_title -> sendIntentToCardActivity(YELLOW)
            R.id.colors_green_title -> sendIntentToCardActivity(GREEN)
        }
    }

    private fun sendIntentToCardActivity(color: String) {
        val intent = Intent(this, CardsActivity::class.java)
        intent.putExtra("color", color)
        var s = intent.getStringExtra("title")
        var intennt = getIntent()
        intent.putExtra("title", intennt.getStringExtra("title"))
        var ss =  intennt.getStringExtra("title")
        this.startActivity(intent)
    }
}