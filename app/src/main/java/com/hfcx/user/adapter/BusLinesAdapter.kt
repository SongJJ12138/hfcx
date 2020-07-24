package com.hfcx.user.adapter

import com.hfcx.user.R
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.beans.BusLines
import org.jetbrains.anko.sdk25.coroutines.onClick

class BusLinesAdapter (orders: ArrayList<BusLines>, var  listener: onClickListener): HFRecyclerAdapter<BusLines>(orders, R.layout.item_bus_line){
    interface onClickListener{
        fun onClick(id:Int)
    }
    override fun onBind(holder: ViewHolder, position: Int, data: BusLines) {
        holder.itemView.onClick {
            listener.onClick(1)
        }
    }
}