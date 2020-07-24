package com.hfcx.user.adapter

import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.hfcx.user.R
import com.hfcx.user.beans.Message
import com.hfcx.user.utils.gone
import com.hfcx.user.utils.interval
import com.hfcx.user.utils.visible

class MessageAdapter(data:ArrayList<com.hfcx.user.beans.Message>): HFRecyclerAdapter<Message>(data, R.layout.item_message) {
    override fun onBind(holder: ViewHolder, position: Int, data: com.hfcx.user.beans.Message) {
        holder.setText(R.id.tv_title,if (data.type == 1) "平台公告" else "系统消息")
        holder.setText(R.id.tv_time,data.createTime.interval())
        holder.setText(R.id.tv_content,if (data.type == 1) data.title else data.content)
        val draweeView = holder.bind<SimpleDraweeView>(R.id.iv_img)
        if (data.type == 1){
            draweeView.visible()
            draweeView.setImageURI(data.imgUrl)
        }else
            draweeView.gone()
    }
}