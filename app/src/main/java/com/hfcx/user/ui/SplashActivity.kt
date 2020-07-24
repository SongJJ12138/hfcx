package com.hfcx.user.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import cn.sinata.xldutils.utils.optInt
import cn.sinata.xldutils.utils.optString
import com.hfcx.user.R
import com.hfcx.user.network.Api
import com.hfcx.user.network.HttpManager
import com.hfcx.user.utils.request
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity: BaseActivity(){
    private var type = 0
    private var url = ""

    private var timer:CountDownTimer = object : CountDownTimer(4000, 1000){
        override fun onFinish() {
            startActivity<StartActivity>()
            finish()
        }

        override fun onTick(millisUntilFinished: Long) {
            tv_next.text = "${millisUntilFinished/1000}s 跳过"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        tv_next.setOnClickListener {
            startActivity<StartActivity>()
            timer.cancel()
            finish()
        }

        iv_splash.setOnClickListener {
            if (type == 2) {
                val u = Api.URL+url
                startActivity<H5Activity>("url" to u)
            } else if (type == 3) {
                startActivity<H5Activity>("url" to url)
            }
        }
        getAd()
        startTimer()
    }

    private fun startTimer(){
        tv_next.visibility = View.VISIBLE
        timer.start()
    }

    private fun getAd(){
        HttpManager.getAd().request(this){_,data->
            url = data?.optString("jumpUrl")?:""
            type = data?.optInt("jumpType")?:0
            iv_splash.setImageURI(data?.get("imgUrl")?.asString)
        }
    }
}
