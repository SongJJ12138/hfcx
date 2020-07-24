package com.hfcx.user.adapter

import com.hfcx.user.R
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.beans.Order
import org.jetbrains.anko.sdk25.coroutines.onClick

class OrderAdapter(orders: ArrayList<Order>,var  listener: onClickListener):HFRecyclerAdapter<Order>(orders, R.layout.item_order){
    interface onClickListener{
        fun onClick(id:Int)
    }
    override fun onBind(holder: ViewHolder, position: Int, data: Order) {
//        holder.setText(R.id.tv_start,"")
//        holder.setText(R.id.tv_end,"")
        holder.itemView.onClick {
            listener.onClick(1)
        }
    }
}