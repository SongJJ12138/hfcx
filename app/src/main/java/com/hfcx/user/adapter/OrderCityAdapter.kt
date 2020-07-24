package com.hfcx.user.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import com.hfcx.user.R
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.beans.OrderCity
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textColor

class OrderCityAdapter(data:ArrayList<OrderCity>, private val callBack: OrderCityAdapter.OnItemClick, private var pos:Int) : HFRecyclerAdapter<OrderCity>(data, R.layout.item_order_city) {
    interface OnItemClick{
        fun onClick(id: Int)
    }
    override fun onBind(holder: ViewHolder, position: Int, data: OrderCity) {
        var city=holder.bind<TextView>(R.id.order_city)
        city.text=data.name
        if (pos==position){
            city.typeface= Typeface.defaultFromStyle(Typeface.BOLD)
            city.textColor=context.resources.getColor(R.color.bluediss)
        }else{
            city.typeface= Typeface.defaultFromStyle(Typeface.NORMAL)
            city.textColor=context.resources.getColor(R.color.gray)
        }
        holder.itemView.onClick {
            pos=position
            callBack.onClick(data.id)
            notifyDataSetChanged()
        }
    }
}