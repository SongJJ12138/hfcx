package com.hfcx.user.ui.user

import android.app.Activity
import cn.jpush.android.api.JPushInterface
import com.hfcx.user.ui.TranslateStatusBarActivity
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.R
import com.hfcx.user.dialogs.TipDialog
import com.hfcx.user.network.Api
import com.hfcx.user.ui.H5Activity
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.callPhone
import kotlinx.android.synthetic.main.activity_more.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class MoreActivity : TranslateStatusBarActivity() {
    override fun setContentView() = R.layout.activity_more

    override fun initClick() {
        tv_logout.setOnClickListener {
            val tipDialog = TipDialog()
            tipDialog.arguments = bundleOf("msg" to "是否退出登录?","ok" to "确定","cancel" to "取消")
            tipDialog.setDialogListener { p, s ->
                SPUtils.instance().remove(Const.User.USER_ID).apply()
                JPushInterface.deleteAlias(this,0)
                setResult(Activity.RESULT_OK)
                finish()
            }
            tipDialog.show(supportFragmentManager,"logout")
        }
        tv_about.setOnClickListener {
            startActivity<H5Activity>("title" to "关于我们", "url" to Api.ABOUT)
        }
        tv_rule.setOnClickListener {
            startActivity<H5Activity>("title" to "平台协议", "url" to Api.PLATFORM_RULE)
        }
        tv_wechat_code.setOnClickListener {
            startActivity<WechatCodeActivity>()
        }
        tv_safety.setOnClickListener {
            startActivity<SafetyActivity>()
        }
        tv_feedback.onClick {
            startActivity<FeedbackActivity>()
        }
        tv_passengers.onClick {
        }
        ll_contact.onClick {
            callPhone(phone)
        }
    }
    private val phone by lazy {
        SPUtils.instance().getString(Const.SERVICE_PHONE)
    }
    override fun initView() {
        title = "更多"
        tv_phone.text = phone
    }
}