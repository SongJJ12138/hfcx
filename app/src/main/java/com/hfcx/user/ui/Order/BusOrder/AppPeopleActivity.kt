package com.hfcx.user.ui.Order.BusOrder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hfcx.user.R
import com.hfcx.user.ui.BaseActivity
import com.hfcx.user.utils.isValidIdCard
import com.hfcx.user.utils.isValidPhone
import kotlinx.android.synthetic.main.activity_app_people.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class AppPeopleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_people)
        bt_commit.onClick {
            var name=ed_name.text.toString().trim()
            var phone=ed_phone.text.toString().trim()
            var card=ed_card.text.toString().trim()
            var intent=Intent()
            if (name.equals("")){
                toast("姓名为空")
                return@onClick
            }
            if (phone.equals("")){
                toast("手机号为空")
                return@onClick
            }
            if (card.equals("")){
                toast("身份证为空")
                return@onClick
            }
            if (!phone.isValidPhone()){
                toast("手机号格式错误")
                return@onClick
            }
            if (!card.isValidIdCard()){
                toast("身份证格式错误")
                return@onClick
            }
            intent.putExtra("name",name)
            intent.putExtra("phone",phone)
            intent.putExtra("card",card)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}
