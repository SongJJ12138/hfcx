package com.hfcx.user.adapter

import android.widget.ImageView
import com.hfcx.user.R
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.beans.Image
import com.hfcx.user.beans.People
import com.hfcx.user.utils.hideIdCard
import org.jetbrains.anko.sdk25.coroutines.onClick

class PelpleAdapter (orders: ArrayList<People>,var  listener: onDeleteListener): HFRecyclerAdapter<People>(orders, R.layout.item_people){
    interface onDeleteListener{
        fun onClick(position:Int)
    }
    override fun onBind(holder: ViewHolder, position: Int, data: People) {
        holder.setText(R.id.name,data.name)
        holder.setText(R.id.card_num,data.card.hideIdCard())
        holder.bind<ImageView>(R.id.delete).onClick {
            listener.onClick(position)
            notifyDataSetChanged()
        }
    }
}