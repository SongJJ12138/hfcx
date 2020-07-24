package com.hfcx.user.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.sinata.xldutils.fragment.BaseFragment
import com.hfcx.user.R
import com.hfcx.user.adapter.OrderAdapter
import com.hfcx.user.adapter.OrderCityAdapter
import com.hfcx.user.beans.Order
import com.hfcx.user.beans.OrderCity
import com.hfcx.user.ui.Order.BusOrder.BusLinesActivity
import com.hfcx.user.views.SwipeRefreshRecyclerLayout
import kotlinx.android.synthetic.main.fragment_busorder.*
import org.jetbrains.anko.support.v4.find
import kotlin.properties.Delegates

class BusFragment: BaseFragment() {
    private val orderCitys = ArrayList<OrderCity>()
    private val orders = ArrayList<Order>()
    var RefreshLayout_bus by Delegates.notNull<SwipeRefreshRecyclerLayout>()

    private val orderCityAdapter by lazy {
        OrderCityAdapter(orderCitys, object :OrderCityAdapter.OnItemClick {
            override fun onClick(id: Int) {
                orders.add(Order())
                orders.add(Order())
                orderAdapter.notifyDataSetChanged()
            }
        },0)
    }

    private val orderAdapter by lazy {
        OrderAdapter(orders, object :OrderAdapter.onClickListener {
            override fun onClick(id: Int) {
                var intent=Intent(activity, BusLinesActivity::class.java)
                intent.putExtra("orderId",id)
                startActivity(intent)
            }
        })
    }

    override fun contentViewId()= R.layout.fragment_busorder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RefreshLayout_bus=find(R.id.RefreshLayout_bus)
        RefreshLayout_city.setLayoutManager(LinearLayoutManager(context))
        RefreshLayout_city.setAdapter(orderCityAdapter)
        RefreshLayout_bus.setLayoutManager(LinearLayoutManager(context))
        RefreshLayout_bus.setOnRefreshListener(object : SwipeRefreshRecyclerLayout.OnRefreshListener {
            override fun onRefresh() {
                pullDownRefresh()
            }

            override fun onLoadMore() {
                loadMore()
            }
        })
        RefreshLayout_bus.setAdapter(orderAdapter)
    }

    /**
     * 下拉加载
     */
    private fun pullDownRefresh() {

    }

    /**
     * 上拉刷新
     */
    private fun loadMore() {

    }

    override fun onFirstVisibleToUser() {
        getData()
    }
    private fun getData() {
//        HttpManager.getOrderCity().requestByF(this,success = { _, data->
//            orderCitys.clear()
//
//            ordersAdapter.notifyDataSetChanged()
//        },error = {_,_->
//
//        })
        orderCitys.add(OrderCity("北京",1))
        orderCitys.add(OrderCity("上海",2))
        orderCitys.add(OrderCity("南京",3))
        orderCitys.add(OrderCity("广州",4))
        orderCitys.add(OrderCity("河北",5))
        orderCitys.add(OrderCity("唐山",6))
        orders.add(Order())
        orders.add(Order())
        orderCityAdapter.notifyDataSetChanged()
        orderAdapter.notifyDataSetChanged()
    }
}