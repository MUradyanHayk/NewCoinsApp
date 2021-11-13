package com.example.newcoinstatsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newcoinstatsapp.R
import com.example.newcoinstatsapp.database.Coin
import java.lang.ref.WeakReference

interface CoinsAdapterDelegate {
    fun onItemClick(identifier: String?)
}

class CoinsAdapter(val context: Context, var isInFavoriteScreen: Boolean, var coinList: MutableList<Coin>?) : RecyclerView.Adapter<ViewHolder>() {
    var delegate: WeakReference<CoinsAdapterDelegate>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.coin_item, parent, false)
        return ViewHolder(item, context, isInFavoriteScreen)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (coinList == null) {
            return
        }
        holder.configure(coinList!![position])
        holder.itemView.setOnClickListener {
            delegate?.get()?.onItemClick(coinList!![position].identifier)
        }
    }

    override fun getItemCount(): Int {
        return coinList?.size ?: 0
    }
}

class ViewHolder(itemView: View, val context: Context, val isInFavoriteScreen: Boolean = false) : RecyclerView.ViewHolder(itemView) {
    private var nameTextView: TextView? = null
    private var priceTextView: TextView? = null
    private var imageview: ImageView? = null
    private var checkBox: ImageView? = null

    var name: String = ""
        set(value) {
            field = value
            nameTextView?.text = value
        }
    var price: String = ""
        set(value) {
            field = value
            priceTextView?.text = value
        }
    var icon: String = ""
        set(value) {
            field = value
            if (imageview != null) {
                Glide.with(context).load(value).into(imageview!!)
            }
        }

    var isChecked: Boolean = false
        set(value) {
            field = value
            if (value) {
                checkBox?.setImageResource(R.drawable.ic_star_fill)
            } else {
                checkBox?.setImageResource(R.drawable.ic_star_border)
            }
        }


    init {
        nameTextView = itemView.findViewById(R.id.name_text_view)
        priceTextView = itemView.findViewById(R.id.price_text_view)
        imageview = itemView.findViewById(R.id.image_view)
        checkBox = itemView.findViewById(R.id.check_box)
    }

    fun configure(coin: Coin) {
        this.name = coin.name ?: ""
        this.price = coin.price ?: ""
        this.icon = coin.icon ?: ""
        this.isChecked = coin.isFavorite

        if (isInFavoriteScreen) {
            this.checkBox?.visibility = View.GONE
            itemView.setBackgroundResource(R.drawable.not_click_bg)
        } else {
            itemView.setBackgroundResource(R.drawable.item_click_background)
            this.checkBox?.visibility = View.VISIBLE
        }
    }
}