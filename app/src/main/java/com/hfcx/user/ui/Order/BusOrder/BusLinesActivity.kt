package com.hfcx.user.ui.Order.BusOrder

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hfcx.user.R
import com.hfcx.user.adapter.BusLinesAdapter
import com.hfcx.user.beans.BusLines
import com.hfcx.user.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_order_details.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor

class BusLinesActivity : BaseActivity() {
    private var list=ArrayList<BusLines>()

    private val orderId by lazy {
        intent.getIntExtra("orderId",0)
    }

    private val adapter by lazy {
        BusLinesAdapter(list,object:BusLinesAdapter.onClickListener{
            override fun onClick(id: Int) {
                startActivity<BusDetialActivity>("id" to id)
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        initView()
        initClick()
        getData()
    }

    private fun getData() {
        list.add(BusLines())
        list.add(BusLines())
        list.add(BusLines())
        list.add(BusLines())
        adapter.notifyDataSetChanged()
    }

    private fun initView() {
       // var date=StringDate()
        rv_bus_details.layoutManager=LinearLayoutManager(applicationContext)
        rv_bus_details.adapter=adapter
    }

    private fun initClick() {
        bt_day1.onClick {
            clearTab()
            bt_day1.textColor=resources.getColor(R.color.bluediss)
            bt_day1.background=resources.getDrawable(R.drawable.bt_white)
            notifyData(1)
        }
        bt_day2.onClick {
            clearTab()
            bt_day1.textColor=resources.getColor(R.color.bluediss)
            bt_day2.background=resources.getDrawable(R.drawable.bt_white)
            notifyData(2)
        }
        bt_day3.onClick {
            clearTab()
            bt_day1.textColor=resources.getColor(R.color.bluediss)
            bt_day3.background=resources.getDrawable(R.drawable.bt_white)
            notifyData(3)
        }
        bt_day4.onClick {
            clearTab()
            bt_day1.textColor=resources.getColor(R.color.bluediss)
            bt_day4.background=resources.getDrawable(R.drawable.bt_white)
            notifyData(4)
        }
        bt_day5.onClick {
            clearTab()
            bt_day1.textColor=resources.getColor(R.color.bluediss)
            bt_day5.background=resources.getDrawable(R.drawable.bt_white)
            notifyData(5)
        }
        bt_day6.onClick {
            clearTab()
            bt_day1.textColor=resources.getColor(R.color.bluediss)
            bt_day6.background=resources.getDrawable(R.drawable.bt_white)
            notifyData(6)
        }
    }

    private fun notifyData(i: Int) {

    }

    private fun clearTab() {
        bt_day1.textColor=Color.WHITE
        bt_day2.textColor=Color.WHITE
        bt_day3.textColor=Color.WHITE
        bt_day4.textColor=Color.WHITE
        bt_day5.textColor=Color.WHITE
        bt_day6.textColor=Color.WHITE
        bt_day1.backgroundColor=resources.getColor(R.color.bluediss)
        bt_day2.backgroundColor=resources.getColor(R.color.bluediss)
        bt_day3.backgroundColor=resources.getColor(R.color.bluediss)
        bt_day4.backgroundColor=resources.getColor(R.color.bluediss)
        bt_day5.backgroundColor=resources.getColor(R.color.bluediss)
        bt_day6.backgroundColor=resources.getColor(R.color.bluediss)
    }
}
