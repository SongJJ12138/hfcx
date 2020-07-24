package com.hfcx.user.adapter

import com.hfcx.user.adapter.util.HFRecyclerAdapter
import com.hfcx.user.adapter.util.ViewHolder

import com.facebook.drawee.view.SimpleDraweeView
import com.hfcx.user.R
import com.hfcx.user.beans.InviteRecord
import com.hfcx.user.utils.hidePhone
import com.hfcx.user.utils.toTime

class InviteRecordAdapter(data:ArrayList<com.hfcx.user.beans.InviteRecord>): HFRecyclerAdapter<InviteRecord>(data, R.layout.item_invite) {
    override fun onBind(holder: ViewHolder, position: Int, data: com.hfcx.user.beans.InviteRecord) {
        holder.bind<SimpleDraweeView>(R.id.iv_head).setImageURI(data.imgUrl)
        holder.setText(R.id.tv_name,data.nickName)
        holder.setText(R.id.tv_phone,data.phone.hidePhone())
        holder.setText(R.id.tv_time,data.createTime.toTime("yyyy-MM-dd"))
    }
}