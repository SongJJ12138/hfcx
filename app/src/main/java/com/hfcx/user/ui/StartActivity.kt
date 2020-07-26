package com.hfcx.user.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.hfcx.user.utils.SPUtils
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationListener
import com.hfcx.user.ui.Order.BusOrder.BusMainActivity
import com.hfcx.user.R
import com.hfcx.user.dialogs.LoginDialog
import com.hfcx.user.ui.Order.CarOrder.CarMainActivity
import com.hfcx.user.ui.user.MessageActivity
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.StatusBarUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_new_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class StartActivity : BaseActivity(),  AMapLocationListener {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_main)
        StatusBarUtil.initStatus(window)
        initClick()
        RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CALL_PHONE
                , Manifest.permission.CAMERA).subscribe {
            if (it) {
                Handler(Handler.Callback {
                    requestLocation()
                    true
                }).sendEmptyMessageDelayed(1000, 500)
            }
        }
    }

    private fun initClick() {
        bt_mine.onClick {
            if(checkLogin()){
                val intent2 = Intent(this@StartActivity, MineActivity::class.java)
                startActivity(intent2)
            }
        }
        bt_notice.onClick {
            if (checkLogin()) {
                startActivity<MessageActivity>()
            }
        }
        bt_dingdan.onClick {

        }
        bt_kefu.onClick {

        }
        bt_order.onClick {
            startActivity<BusMainActivity>()
        }
        bt_taxi.onClick {
            startActivity<CarMainActivity>()

        }
        bt_wuliu.onClick {

        }
    }

    private val app by lazy {
        application as com.hfcx.user.YyApplication
    }
    private fun requestLocation() {
        app.setLocationListener(this)
        app.startLocation()
    }
    /**
     * 登录
     */
    private fun checkLogin(): Boolean {
        if (SPUtils.instance().getInt(Const.User.USER_ID) == -1) {
            val loginDialog = LoginDialog()
            loginDialog.setDialogListener { p, s -> //登录成功回调
            }
            loginDialog.show(supportFragmentManager, "login")
            return false
        }
        return true
    }


    override fun onLocationChanged(location: AMapLocation?) {
        location?.let {
            com.hfcx.user.YyApplication.city = it.city
            com.hfcx.user.YyApplication.lat = it.latitude
            com.hfcx.user.YyApplication.lng = it.longitude
            app.stopLocation()
        }
    }
}