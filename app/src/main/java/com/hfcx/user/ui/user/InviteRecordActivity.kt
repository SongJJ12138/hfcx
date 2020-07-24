package com.hfcx.user.ui.user

import android.support.v7.widget.LinearLayoutManager
import com.hfcx.user.ui.TranslateStatusBarActivity
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.views.SwipeRefreshRecyclerLayout
import com.hfcx.user.R
import com.hfcx.user.network.HttpManager
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.request
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.dip

class InviteRecordActivity : TranslateStatusBarActivity(), SwipeRefreshRecyclerLayout.OnRefreshListener {
    override fun onRefresh() {
        page = 1
        getData()
    }

    override fun onLoadMore() {
        page++
        getData()
    }

    override fun setContentView() = R.layout.base_recyclerview_layout

    private var page = 1
    private lateinit var swipeRefreshLayout: SwipeRefreshRecyclerLayout
    private val data = arrayListOf<com.hfcx.user.beans.InviteRecord>()
    private val adapter by lazy {
        com.hfcx.user.adapter.InviteRecordAdapter(data)
    }
    private val userId by lazy {
        SPUtils.instance().getInt(Const.User.USER_ID)
    }

    override fun initClick() {
    }

    override fun initView() {
        title = "邀请记录"
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.backgroundResource = R.color.gray
        swipeRefreshLayout.setPadding(0, dip(10), 0, 0)
        swipeRefreshLayout.setLayoutManager(LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false))
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setAdapter(adapter)
        showDialog()
        getData()
    }

    private fun getData(){
        HttpManager.getInviteRecord(userId,page).request(this,success ={_,data->
            swipeRefreshLayout.isRefreshing = false
            data?.let {
                if (page == 1)
                    this.data.clear()
                this.data.addAll(it)
                if (this.data.isEmpty())
                    swipeRefreshLayout.setLoadMoreText("暂无邀请记录")
                adapter.notifyDataSetChanged()
            }
        },error = {_,_->
            swipeRefreshLayout.isRefreshing = false
        } )
    }
}