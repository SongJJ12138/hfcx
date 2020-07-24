package com.hfcx.user.ui.user

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.hfcx.user.ui.TranslateStatusBarActivity
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.utils.SpanBuilder
import com.hfcx.user.views.SwipeRefreshRecyclerLayout
import com.hfcx.user.R
import com.hfcx.user.network.HttpManager
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.request
import kotlinx.android.synthetic.main.layout_score_head.view.*

class MyScoreActivity: TranslateStatusBarActivity() {
    override fun setContentView() = R.layout.base_recyclerview_layout

    private var page = 1

    override fun initClick() {
    }

    private val data= arrayListOf<com.hfcx.user.beans.ScoreRecored>()

    private val adapter by lazy {
        com.hfcx.user.adapter.ScoreAdapter(data)
    }

    private val userId by lazy {
        SPUtils.instance().getInt(Const.User.USER_ID)
    }

    private val headView by lazy {
        LayoutInflater.from(this).inflate(R.layout.layout_score_head,swipeRefreshLayout.mRecyclerView,false)
    }

    val swipeRefreshLayout: SwipeRefreshRecyclerLayout by lazy {
        findViewById<SwipeRefreshRecyclerLayout>(R.id.swipeRefreshLayout)
    }
    override fun initView() {
        title = "我的积分"
        swipeRefreshLayout.setLayoutManager(LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false))
        swipeRefreshLayout.setAdapter(adapter)
        adapter.setHeaderView(headView)
        swipeRefreshLayout.setOnRefreshListener(object :SwipeRefreshRecyclerLayout.OnRefreshListener{
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

    private fun getData(){
        HttpManager.getIntegral(userId,page).request(this){_,data->
            data?.let {
                swipeRefreshLayout.isRefreshing = false
                val s = "${it.integral}分"
                headView.tv_integral.text = SpanBuilder(s).size(s.length-1,s.length,16).build()
                headView.tv_describe.text = "历史总积分：${it.totalIntegral}分，购票抵扣${it.consumptionIntegral}积分"
                if (page == 1)
                    this.data.clear()
                if (it.integralRecordList.isEmpty()){
                    if (page == 1)
                        swipeRefreshLayout.setLoadMoreText("暂无数据")
                    else
                        swipeRefreshLayout.setLoadMoreText("没有更多")
                }else{
                    this.data.addAll(it.integralRecordList)
                    page++
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
}