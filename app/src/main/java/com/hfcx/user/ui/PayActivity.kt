package com.hfcx.user.ui

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.widget.Toast
import com.hfcx.user.R
import com.hfcx.user.beans.PayInfo
import com.hfcx.user.dialogs.TipDialog
import com.hfcx.user.interfaces.PayListener
import com.hfcx.user.network.HttpManager
import com.hfcx.user.utils.*
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.layout_pay.*
import okhttp3.Call
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class PayActivity : BaseActivity() , PayListener, OkHttpUtils.NetCall {
    var payWay=1


    private val money by lazy {
        intent.getDoubleExtra("money", 0.0)
    }
    private val size by lazy {
        intent.getStringExtra("size")
    }
    private val time by lazy {
        intent.getLongExtra("time", 0)
    }
    private val id by lazy {
        intent.getIntExtra("id", 0)
    }
    private val type by lazy {
        //1包车 //2班线 //3私人订制
        intent.getIntExtra("type", 0)
    }
    private val ticketId by lazy {
        //下单页面过来
        intent.getStringExtra("ticketId")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        initView()
        initClick()
    }

    private fun initView() {
        tv_money.text = String.format("应支付：￥%.2f",money)
        if (type == 1) {
            title = "支付订金"
            tv_time.gone()
        } else {
            title = if (type == 0) "支付" else "支付方式"
            object : CountDownTimer((if (type == 2||type==3) 5 else 30) * 60 * 1000 + time - System.currentTimeMillis(), 1000) {
                override fun onFinish() {
                    if(!isDestroy){
                        toast("订单已失效")
                        startActivity<StartActivity>()
                        finish()
                    }
                }
                override fun onTick(p0: Long) {
                    tv_time.text = SpanBuilder("${p0.toTime("mm分ss秒")}后取消订单").build()
                }
            }.start()
        }
        PayUtil.addPayListener(this)
    }
    fun initClick() {
//        rl_ali.setOnClickListener {
//            getPayInfo(1)
//            payWay=1
//        }
        rl_ali.setOnClickListener {
            startActivity<PaySuccessActivity>()
        }
        rl_wx.setOnClickListener {
            PayUtil.initWeChatPay(this, Const.WX_APP_ID)
            if (!PayUtil.checkSupportWeChat(this)) {
                toast("请先安装微信，再进行支付")
                return@setOnClickListener
            }
            payWay=20
            getPayInfo(2)
        }
    }
    override fun onPaySuccess() {
        setResult(Activity.RESULT_OK, intent.putExtra("id", id))
        finish()
    }
    //1=支付宝,2 = 微信
    private fun getPayInfo(payWay: Int) {
        showDialog()
        if (type==3){
            var hashMap = mapOf("quantity" to size, "otaOrderId" to ticketId, "type" to payWay.toString())
            val http = OkHttpUtils.getInstance()
            http.postDataAsyn(Constans.WXHOST + Constans.GETPAYINFO, hashMap as MutableMap<String, String>?, this)
        }else{
            HttpManager.getPayInfo(id, payWay, when (type) {
                0 -> 2
                1 -> 3
                else -> 4
            }).request(this) { it, data ->
                data?.let {
                    when (payWay) {
                        //支付宝
                        1 -> PayUtil.aliPay(this, it.orderInfo)
                        //微信
                        2 -> PayUtil.weChatPay(it)
                        else -> {
                        }
                    }
                }
            }
        }
    }
    override fun onBackPressed() {
        val tipDialog = TipDialog()
        tipDialog.setCancelDialogListener { _, _ ->
            finish()
        }
        tipDialog.arguments = bundleOf("cancel" to "确认离开", "ok" to "继续支付", "msg" to "超过支付时效后订单将被取消，请尽快完成支付!")
        tipDialog.show(supportFragmentManager, "tip")
    }

    override fun failed(call: Call?, e: IOException?) {
        Looper.prepare()
        Toast.makeText(applicationContext,"订单获取失败，请稍后再试！",Toast.LENGTH_SHORT).show()
        Looper.loop()
        dismissDialog()
    }

    override fun success(call: Call?, response: String?) {
        val jsonObject = JSONObject(response)
        val payInfo = PayInfo()
        try {
            if (jsonObject != null && jsonObject.getJSONObject("data") != null) {
                if(payWay==1){
                    PayUtil.aliPay(this, jsonObject.getJSONObject("data").getString("orderInfo"))
                }else if(payWay==2){
                    payInfo.setAppId(jsonObject.getJSONObject("data").getString("appid"))
                    payInfo.nonceStr = jsonObject.getJSONObject("data").getString("nonceStr")
                    payInfo.packageString = jsonObject.getJSONObject("data").getString("packageStr")
                    payInfo.partnerId = jsonObject.getJSONObject("data").getString("partnerId")
                    payInfo.prepayId = jsonObject.getJSONObject("data").getString("prepayId")
                    payInfo.sign = jsonObject.getJSONObject("data").getString("sign")
                    payInfo.timeStamp = jsonObject.getJSONObject("data").getString("timeStamp")
                    PayUtil.weChatPay(payInfo)
                }else{
                    val url = jsonObject.getJSONObject("data").getString("url")
                    if (url != "") {
//                        val intent = Intent(this@PayActivity, WebViewActivity::class.java)
                        intent.putExtra("url", url)
                        startActivity(intent)
                    }
                }
            }
        } catch (ex: JSONException) {
            ex.printStackTrace()
            Looper.prepare()
            Toast.makeText(applicationContext,"订单获取失败，请稍后再试！", Toast.LENGTH_SHORT).show()
            Looper.loop()
        }finally {
            dismissDialog()
        }
    }
}
