package com.hfcx.user.ui.user

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.views.SwipeRefreshRecyclerLayout
import com.hfcx.user.R
import com.hfcx.user.network.Api
import com.hfcx.user.network.HttpManager
import com.hfcx.user.ui.H5Activity
import com.hfcx.user.ui.TranslateStatusBarActivity
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.request
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.startActivity

class MessageActivity : TranslateStatusBarActivity() {
    override fun setContentView() = R.layout.base_recyclerview_layout

    private var page = 1
    private val userId by lazy {
        SPUtils.instance().getInt(Const.User.USER_ID)
    }
    private val msgList = arrayListOf<com.hfcx.user.beans.Message>()
    private val adapter by lazy {
        com.hfcx.user.adapter.MessageAdapter(msgList)
    }

    override fun initClick() {
        adapter.setOnItemClickListener { _, position ->
            if (msgList[position].type == 1) {
                startActivity<H5Activity>("title" to "平台公告", "url" to Api.NOTICE_INFO + msgList[position].content)
            }
        }
    }

    private val recyclerLayout by lazy {
        findViewById<SwipeRefreshRecyclerLayout>(R.id.swipeRefreshLayout)
    }

    override fun initView() {
        title = "消息中心"
        titleBar.addRightButton("清空消息", onClickListener = View.OnClickListener {
            showDialog()
            HttpManager.clearMsg(userId).request(this) { _, _ ->
                page = 1
                getData()
            }
        })
        recyclerLayout.backgroundResource = R.color.gray
        recyclerLayout.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        recyclerLayout.setAdapter(adapter)
        recyclerLayout.setOnRefreshListener(object : SwipeRefreshRecyclerLayout.OnRefreshListener {
            override fun onRefresh() {
                page = 1
                getData()
            }

            override fun onLoadMore() {
                getData()
            }
        })
        getData()
    }

    private fun getData() {
        HttpManager.getMsg(userId, page).request(this) { _, data ->
            data?.let {
                recyclerLayout.isRefreshing = false
                if (page == 1)
                    msgList.clear()
                if (it.isNotEmpty()) {
                    page++
                    msgList.addAll(it)
                }else{
                    if (page == 1)
                        recyclerLayout.setLoadMoreText("暂无数据")
                    else
                        recyclerLayout.setLoadMoreText("没有更多")
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
}