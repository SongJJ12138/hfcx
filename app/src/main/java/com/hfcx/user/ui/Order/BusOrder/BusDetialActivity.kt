package com.hfcx.user.ui.Order.BusOrder

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bigkoo.pickerview.OptionsPickerView
import com.hfcx.user.R
import com.hfcx.user.adapter.PelpleAdapter
import com.hfcx.user.beans.People
import com.hfcx.user.ui.BaseActivity
import com.hfcx.user.ui.PayActivity
import com.hfcx.user.utils.gone
import com.hfcx.user.utils.visible
import kotlinx.android.synthetic.main.activity_bus_detial.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult


class BusDetialActivity : BaseActivity() {
    private var list=ArrayList<People>()
    val REQUEST_MAP_JIE:Int=0x01
    val REQUEST_MAP_SONG:Int=0x02
    val REQUEST_PEOPLE:Int=0x03

    private val adapter by lazy {
        PelpleAdapter(list,object :PelpleAdapter.onDeleteListener{
            override fun onClick(position: Int) {
                list.removeAt(position)
            }
        })
    }

    private val lineId by lazy {
        intent.getIntExtra("id",0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_detial)
        initView()
        initClick()
        getData()
    }

    private fun getData() {

    }

    private fun initView() {
        rv_people.layoutManager=LinearLayoutManager(applicationContext)
        rv_people.adapter=adapter
    }

    private fun initClick() {
        check_jie.setOnCheckedChangeListener{_,isCheck ->
            if (isCheck){
                layout_jie.visibility=View.VISIBLE
                layout_price.visibility=View.VISIBLE

            }else{
                layout_jie.visibility=View.GONE
                if (layout_song.visibility!=View.VISIBLE){
                    layout_price.visibility=View.GONE
                }
            }
        }
        check_song.setOnCheckedChangeListener{buttonView,isCheck ->
            if (isCheck){
                layout_song.visibility=View.VISIBLE
                layout_price.visibility=View.VISIBLE
            }else{
                layout_song.visibility=View.GONE
                if (layout_jie.visibility!=View.VISIBLE){
                    layout_price.visibility=View.GONE
                }
            }
        }
        layout_jie.onClick {
            startActivityForResult<MapSelectActivity>(REQUEST_MAP_JIE)
        }
        layout_song.onClick {
            startActivityForResult<MapSelectActivity>(REQUEST_MAP_SONG)
        }
        bt_addPeople.onClick {
            startActivityForResult<AppPeopleActivity>(REQUEST_PEOPLE)
        }
        up_station.onClick {
            showOptionPicker(1,"上车站点")
        }
        down_station.onClick {
            showOptionPicker(2,"下车站点")
        }
        bt_allstation.onClick {
            showOptionPicker(3,"途经站点")
        }
        bt_pay.onClick {
            startActivity<PayActivity>("money" to 2.0,"size" to 2,"time" to System.currentTimeMillis(),"id" to 1,"type" to 2,"ticketId" to "1258012138")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_MAP_JIE ->{

            }
            REQUEST_MAP_SONG ->{

            }
            REQUEST_PEOPLE ->{
                if (data!=null){
                    var name=data.getStringExtra("name")
                    var phone=data.getStringExtra("phone")
                    var card=data.getStringExtra("card")
                    var people=People(name,phone,card)
                    list.add(people)
                    tv_people_toast.gone()
                    rv_people.visible()
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
    private fun showOptionPicker(type:Int,title:String) {
        val content = ArrayList<String>()
        content.add("北京")
        content.add("平谷")
        content.add("迁西")
        content.add("玉田")
        content.add("清东陵")
        content.add("北关")
        content.add("遵化市汽车站")
        val pickerView =  OptionsPickerView.Builder(this) { options1, options2, options3, v ->
            if (type==1){
                tv_upstation.text=content[options1]
            }else if(type==2){
                tv_downstation.text=content[options1]
            }else{}
        }.setCancelText("取消")
                .setSubmitText("确定")
                .setTitleText(title)
                .setSubmitColor(resources.getColor(R.color.bluediss))
                .setCancelColor(resources.getColor(R.color.gray))
                .build()
        pickerView.setPicker(content as List<String>?)
        pickerView.show()
    }
}
