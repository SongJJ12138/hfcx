package com.hfcx.user.adapter

import android.widget.ImageView
import android.widget.RelativeLayout
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.R
import com.hfcx.user.beans.Coupon
import com.hfcx.user.utils.toTime
import org.jetbrains.anko.backgroundResource

class CouponAdapter(data:ArrayList<com.hfcx.user.beans.Coupon>, private val money:Double = 0.0): HFRecyclerAdapter<Coupon>(data, R.layout.item_coupon) {
    override fun onBind(holder: ViewHolder, position: Int, data: com.hfcx.user.beans.Coupon) {
        holder.setText(R.id.tv_title,data.getTitle())
        holder.setText(R.id.tv_deadline,"有效期至 ${data.expiryTime.toTime("yyyy-MM-dd")}")
        holder.setText(R.id.tv_describe,data.getDescribe())
        holder.setText(R.id.tv_money,data.money.toString())
        holder.bind<ImageView>(R.id.iv_button).setImageResource(if (data.isChecked) R.mipmap.ic_checked else R.mipmap.ic_unchecked)
        if (money<data.money)
            holder.bind<RelativeLayout>(R.id.rl_coupon).backgroundResource = R.mipmap.coupon_disable
        else
            holder.bind<RelativeLayout>(R.id.rl_coupon).backgroundResource = R.mipmap.coupon
    }
}