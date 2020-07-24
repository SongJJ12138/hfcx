package com.hfcx.user.adapter

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.daimajia.swipe.SwipeLayout
import com.hfcx.user.R
import com.hfcx.user.beans.Coupon
import com.hfcx.user.utils.toTime
import com.hfcx.user.utils.visible
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.sdk25.coroutines.onClick

class MyCouponAdapter(data:ArrayList<Coupon>, private val callBack:OnDeleteClick, private val type:Int): HFRecyclerAdapter<Coupon>(data, R.layout.item_my_coupon) {
    override fun onBind(holder: ViewHolder, position: Int, data: com.hfcx.user.beans.Coupon) {
        holder.setText(R.id.tv_title,data.getTitle())
        holder.setText(R.id.tv_deadline,"有效期至 ${data.expiryTime.toTime("yyyy-MM-dd")}")
        holder.setText(R.id.tv_describe,data.getDescribe())
        holder.setText(R.id.tv_money,data.money.toString())
        val swipeLayout = holder.bind<SwipeLayout>(R.id.swipeLayout)
        swipeLayout.showMode = SwipeLayout.ShowMode.PullOut
        holder.bind<TextView>(R.id.tv_del).onClick {
            callBack.onDelete(position)
        }
        when(type){
            2->{
                holder.bind<RelativeLayout>(R.id.rl_coupon).backgroundResource = R.mipmap.coupon_disable
                holder.bind<View>(R.id.iv_used).visible()
            }
            3-> holder.bind<RelativeLayout>(R.id.rl_coupon).backgroundResource = R.mipmap.coupon_disable
        }
    }
    interface OnDeleteClick{
        fun onDelete(position: Int)
    }
}