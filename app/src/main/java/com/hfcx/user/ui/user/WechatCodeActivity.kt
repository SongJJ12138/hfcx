package com.hfcx.user.ui.user

import com.hfcx.user.ui.TranslateStatusBarActivity
import com.hfcx.user.R
import kotlinx.android.synthetic.main.activity_wechat_code.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class WechatCodeActivity : TranslateStatusBarActivity() {


    override fun setContentView()=R.layout.activity_wechat_code

    override fun initClick() {
        asd.onClick {  }
    }

    override fun initView() {
        title = "更多"
    }
}
