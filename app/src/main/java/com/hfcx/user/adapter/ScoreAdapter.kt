package com.hfcx.user.adapter

import android.widget.TextView
import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.hfcx.user.R
import com.hfcx.user.beans.ScoreRecored
import com.hfcx.user.utils.toTime
import org.jetbrains.anko.textColorResource

class ScoreAdapter(data:ArrayList<com.hfcx.user.beans.ScoreRecored>): HFRecyclerAdapter<ScoreRecored>(data, R.layout.item_score_recored) {
    override fun onBind(holder: ViewHolder, position: Int, data: com.hfcx.user.beans.ScoreRecored) {
        holder.setText(R.id.tv_name,data.title)
        holder.setText(R.id.tv_time,data.createTime.toTime("yyyy-MM-dd HH:mm"))
        if (data.state == 1){
            holder.setText(R.id.tv_score,"+${data.integral}")
            holder.bind<TextView>(R.id.tv_score).textColorResource = R.color.black_text
        }else{
            holder.setText(R.id.tv_score,"-${data.integral}")
            holder.bind<TextView>(R.id.tv_score).textColorResource = R.color.black_text
        }
    }
}