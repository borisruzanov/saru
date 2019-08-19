package com.adultgaming.fantasygameapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adultgaming.fantasygameapp.model.GameUnit
import com.adultgaming.fantasygameapp.R
import com.adultgaming.fantasygameapp.model.Game
import com.bumptech.glide.Glide

class GamesAdapter (val mData: MutableList<Game>, context: Context) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    private val con: Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("test", "Inside viewHolder")
        holder.mTitleView.text = mData[position].title
        holder.mDescriptionView.text = mData[position].description
        Glide.with(con).load(mData[position].background).into(holder.mImageView)
        Glide.with(con).load(mData[position].tag).into(holder.mTagView)
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        internal var mTitleView: TextView
        internal var mDescriptionView: TextView
        internal var mImageView: ImageView
        internal var mTagView: ImageView
        internal var mWhiteView: View
        init {
            mTitleView = itemView.findViewById(R.id.item_game_title)
            mDescriptionView = itemView.findViewById(R.id.item_game_description)
            mImageView = itemView.findViewById(R.id.item_game_image)
            mTagView = itemView.findViewById(R.id.item_game_tag)
            mWhiteView = itemView.findViewById(R.id.item_game_white_top)
            mWhiteView.visibility = View.VISIBLE
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

    }
    fun getItem(id: Int): Game {
        return mData[id]
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}