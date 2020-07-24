package com.hfcx.user.adapter

import android.widget.RatingBar
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.R
import com.hfcx.user.beans.Appraise
import com.hfcx.user.utils.toTime

/**
 * 我的评价adapter
 */
class AppraiseAdapter(mData:ArrayList<com.hfcx.user.beans.Appraise>): HFRecyclerAdapter<Appraise>(mData, R.layout.item_appraise_layout) {
    override fun onBind(holder: ViewHolder, position: Int, data: Appraise) {
        holder.setText(R.id.tv_time, data.createTime.toTime("yyyy-MM-dd"))
        holder.setText(R.id.tv_content,data.remark)
        val ratingBar = holder.bind<RatingBar>(R.id.rb_score)
        ratingBar.rating = data.score.toFloat()
    }
}