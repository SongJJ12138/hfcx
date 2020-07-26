package com.hfcx.user.ui.Order.CarOrder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bigkoo.pickerview.OptionsPickerView
import com.bigkoo.pickerview.TimePickerView
import com.hfcx.user.R
import com.hfcx.user.dialogs.ArgeeDialog
import com.hfcx.user.ui.BaseActivity
import com.hfcx.user.ui.PayActivity
import com.hfcx.user.utils.date2String
import kotlinx.android.synthetic.main.activity_bus_detial.*
import kotlinx.android.synthetic.main.activity_car_main.*
import kotlinx.android.synthetic.main.activity_car_main.down_station
import kotlinx.android.synthetic.main.activity_car_main.tv_downstation
import kotlinx.android.synthetic.main.activity_car_main.tv_upstation
import kotlinx.android.synthetic.main.activity_car_main.up_station
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.collections.ArrayList

class CarMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_main)
        initClick()
    }

    private fun initClick() {
        tv_starttime.onClick {
            showTimePicker(tv_starttime)
        }
        tv_endtime.onClick {
            showTimePicker(tv_endtime)
        }
        up_station.onClick {

        }
        down_station.onClick {

        }
        bt_carsize.onClick {
            var list=ArrayList<String>()
            list.add("1")
            list.add("2")
            list.add("3")
            list.add("4")
            list.add("5")
            showOptionPicker(list,bt_carsize,"用车数量")
        }
        bt_carsize.onClick {
            var list=ArrayList<String>()
            list.add("1")
            list.add("2")
            list.add("3")
            list.add("4")
            list.add("5")
            showOptionPicker(list,bt_carsize,"用车数量")
        }
        bt_company.onClick {
            var list=ArrayList<String>()
            list.add("汽运一集团")
            list.add("汽运二集团")
            list.add("汽运三集团")
            list.add("汽运四集团")
            list.add("汽运五集团")
            showOptionPicker(list,bt_company,"选择公司")
        }
        bt_car.onClick {
            var list=ArrayList<String>()
            list.add("小轿车")
            list.add("小汽车")
            list.add("客运车")
            list.add("大巴车")
            list.add("火车")
            showOptionPicker(list,bt_car,"选择公司")
        }
        car_xieyi.onClick{
           var dialog= ArgeeDialog(this@CarMainActivity,"sdjsdhk","sjdsad")
            dialog.show()
        }
        car_guize.onClick {
            var dialog= ArgeeDialog(this@CarMainActivity,"sdjsdhk","sjdsad")
            dialog.show()
        }
        bt_next.onClick {
            startActivity<PayActivity>()
        }

    }
    private fun showTimePicker(view:TextView) {
        val startDate = Calendar.getInstance()
        startDate.set( startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH))
        val endDate = Calendar.getInstance()
        endDate.set(2050, 1, 1)
        val pvTime = TimePickerView.Builder(this@CarMainActivity,
                TimePickerView.OnTimeSelectListener { date, _ ->
                    val str= date2String(date)
                    view.text = str
                })
                .setRangDate(startDate,endDate)
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .build()
        pvTime.show()
    }
    private fun showOptionPicker(list:ArrayList<String>,view:TextView,title:String) {
        val pickerView =  OptionsPickerView.Builder(this) { options1, options2, options3, v ->
                view.text=list[options1]
        }.setCancelText("取消")
                .setSubmitText("确定")
                .setTitleText(title)
                .setSubmitColor(resources.getColor(R.color.bluediss))
                .setCancelColor(resources.getColor(R.color.gray))
                .build()
        pickerView.setPicker(list as List<String>?)
        pickerView.show()
    }
}
