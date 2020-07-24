package com.hfcx.user

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.util.Log
import cn.jpush.android.api.JPushInterface
import cn.sinata.rxnetty.NettyClient
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.hfcx.user.network.Api
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.SPUtils
import com.hfcx.user.utils.getUUID
import com.hfcx.user.utils.xldUtils
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.uuzuche.lib_zxing.activity.ZXingLibrary
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class YyApplication : Application(), Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activities.remove(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activities.add(activity)
    }

    private val activities = java.util.ArrayList<Activity?>()
    companion object {
        var lat = 0.0
        var lng = 0.0
        var city = "" //定位城市
    }

     fun getSPName(): String {
        return "yypw"
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        JPushInterface.init(this)
        UMConfigure.init(this, Const.UMENG_KEY, "", UMConfigure.DEVICE_TYPE_PHONE, "")
        PlatformConfig.setWeixin(Const.WX_APP_ID, Const.WX_SECRET)
        PlatformConfig.setQQZone(Const.QQ_APP_ID, Const.QQ_SECRET)
        PlatformConfig.setSinaWeibo(Const.SINA_APP_ID, Const.SINA_SECRET, "")
        initLocationOption()
        ZXingLibrary.initDisplayOpinion(this)
        NettyClient.getInstance().init(this, Api.SOCKET_SERVER, Api.SOCKET_PORT, false)

        NettyClient.getInstance().setOnConnectListener {
            //连接成功。发送一次心跳
            sendHeart()
        }
        xldUtils.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    private val aMapLocationClient by lazy {
        AMapLocationClient(this)
    }

    /**
     * 高德定位设置
     */
    private fun initLocationOption() {
        val option = AMapLocationClientOption()
        option.interval = 5 * 1000
        option.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        option.isMockEnable = false
        aMapLocationClient.setLocationOption(option)
    }

    /**
     * 设置定位监听回调
     */
    fun setLocationListener(listener: AMapLocationListener) {
        aMapLocationClient.setLocationListener(listener)
    }

    /**
     * 开始定位
     */
    fun startLocation() {
        if (aMapLocationClient.isStarted) {
            aMapLocationClient.stopLocation()
        }
        aMapLocationClient.startLocation()
    }

    /**
     * 停止定位
     */
    fun stopLocation() {
        if (aMapLocationClient.isStarted) {
            aMapLocationClient.stopLocation()
        }
    }

    private fun sendHeart() {
        val userId = SPUtils.instance().getInt(Const.User.USER_ID)
        NettyClient.getInstance().sendMessage("{\"con\":{\"userId\":$userId,\"type\":1,\"token\":\"${this.getUUID()}\"},\"method\":\"OK\",\"code\":\"0\",\"msg\":\"SUCCESS\"}")
    }
}