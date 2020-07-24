package com.hfcx.user.ui.user

import cn.sinata.xldutils.fragment.RecyclerFragment
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.network.HttpManager
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.requestByF
import org.jetbrains.anko.bundleOf

class CouponFragment : RecyclerFragment() {
    private var page = 1
    companion object {
        fun getInstance(type:Int):CouponFragment{
            val couponFragment = CouponFragment()
            couponFragment.arguments = bundleOf("type" to type)
            return couponFragment
        }
    }

    private val coupons by lazy {
        arrayListOf<com.hfcx.user.beans.Coupon>()
    }

    private val type by lazy {
        arguments?.getInt("type")?:1
    }

    private val adapter by lazy {
        com.hfcx.user.adapter.MyCouponAdapter(coupons, object : com.hfcx.user.adapter.MyCouponAdapter.OnDeleteClick {
            override fun onDelete(position: Int) {
                delCoupon(coupons[position].id)
            }
        }, type)
    }

    override fun setAdapter() = adapter

    override fun onFirstVisibleToUser() {
        if (type == 1){
            refresh()
        }
    }

    private fun delCoupon(id:Int){
        HttpManager.delCoupon(id).requestByF(this){_,_->
            showDialog()
            page = 1
            getData()
        }
    }

    override fun pullDownRefresh() {
        page = 1
        getData()
    }

    override fun loadMore() {
        page++
        getData()
    }

    fun refresh(){
        mSwipeRefreshLayout.isRefreshing = true
        pullDownRefresh()
    }

    private fun getData(){
        HttpManager.getCoupons(page,SPUtils.instance().getInt(Const.User.USER_ID),type,MyCouponActivity.useType).requestByF(this){_,data->
            mSwipeRefreshLayout.isRefreshing = false
            if (page == 1) {
                coupons.clear()
            }
            data?.let {
                coupons.addAll(it)
                if (it.isEmpty()) {
                    if (page == 1) {
                        mSwipeRefreshLayout.setLoadMoreText("暂无数据")
                    } else {
                        mSwipeRefreshLayout.setLoadMoreText("没有更多")
                    }
                }
            }

            adapter.notifyDataSetChanged()
        }
    }
}