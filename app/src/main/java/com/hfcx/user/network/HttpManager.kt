package com.hfcx.user.network

import android.text.TextUtils
import android.util.Log
import com.google.gson.JsonObject
import com.hfcx.user.beans.*
import com.hfcx.user.utils.Const
import com.hfcx.user.utils.DES
import com.hfcx.user.utils.defaultScheduler
import io.reactivex.Flowable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * 网络请求处理
 */
object HttpManager {

    const val PAGE_SIZE = 20
    const val encodeDES = true

    private class ParamsBuilder private constructor() {
        private val sb: StringBuilder = StringBuilder()

        fun build(): String {
            return sb.toString()
        }

        fun build(des: Boolean): String {
            return if (des) {
                Log.d("server:", sb.toString())
                DES.encryptDES(sb.toString())
            } else {
                sb.toString()
            }
        }

        fun append(key: String, value: String): ParamsBuilder {
            _append(key, value)
            return this
        }

        fun append(key: String, value: Int): ParamsBuilder {
            _append(key, value)
            return this
        }

        fun append(key: String, value: Double): ParamsBuilder {
            _append(key, value)
            return this
        }

        fun append(key: String, value: Float): ParamsBuilder {
            _append(key, value)
            return this
        }

        fun append(key: String, value: Long): ParamsBuilder {
            _append(key, value)
            return this
        }

        private fun _append(key: String, value: Any) {
            var value = value
            if (value is String) {

                if ("null" == value || TextUtils.isEmpty(value.toString())) {
                    value = ""
                }
            }
            if (sb.isEmpty()) {
                sb.append(key)
                sb.append(SPLIT)
                sb.append(value)
            } else {
                if (sb.contains(BEGIN)) {
                    sb.append(AND)
                    sb.append(key)
                    sb.append(SPLIT)
                    sb.append(value)
                } else {
                    sb.append(BEGIN)
                    sb.append(key)
                    sb.append(SPLIT)
                    sb.append(value)
                }
            }
        }

        companion object {
            const val SPLIT = "="
            const val AND = "&"
            const val BEGIN = "?"

            fun create(): ParamsBuilder {
                return ParamsBuilder()
            }
        }

    }

    /**
     * 发起请求方法
     */
    private fun request() =
            RRetrofit.instance().create(ApiService::class.java)


//    fun uploadFile(context: Context, file: File):Flowable<String>{
//        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
//        val credentialProvider = OSSPlainTextAKSKCredentialProvider(Const.OSS_AK, Const.OSS_AKS)
//        val oss = OSSClient(context.applicationContext, Api.OSS_END_POINT, credentialProvider)
//        val objectKey = "DaZhou/image/" + System.currentTimeMillis() + "." + file.suffix()
//        // 构造上传请求
//        val put = PutObjectRequest(Const.BUCKET_NAME, objectKey, file.path)
//        return Flowable.create(FlowableOnSubscribe<String> {
//            try {
//                oss.putObject(put)
//                val url = oss.presignPublicObjectURL(Const.BUCKET_NAME, objectKey)
//                it.onNext(url)
//                it.onComplete()
//            } catch (e: ClientException) {
//                e.printStackTrace()
//                it.onError(ResultException("上传失败！"))
//            } catch (e: ServiceException) {
//                e.printStackTrace()
//                it.onError(ResultException("上传失败！"))
//            }
//        }, BackpressureStrategy.DROP).defaultScheduler()
//    }
    /**
     * 登录
     */
    fun login(phone: String, passWord: String, type: Int, cityName: String): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.LOGIN).append("phone", phone).append("passWord", passWord).append("type", type).append("cityName", cityName)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 重置密码并登录
     */
    fun forgetPwd(phone: String, passWord: String, code: String): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.FORGET).append("phone", phone).append("passWord", passWord).append("code", code)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }
//    /**
//     * 验证验证码
//     */
//    fun checkSms(phone: String, type: Int, code: String) =
//            request().checkSms(phone, type, code).defaultScheduler()

    /**
     * 发送验证码
     * @param type 类型【1=用户登录，2=用户更换手机，3=用户忘记密码，4=司机注册，5=司机更换手机，6=司机忘记密码】
     */
    fun sendSms(phone: String, type: Int): Flowable<ResultData<String>> {
        val request = ParamsBuilder.create().append("server", Api.SEND_MSM).append("phone", phone).append("type", type)
        return request().stringRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 验证验证码
     * @param type 类型【1=用户登录，2=用户更换手机，3=用户忘记密码，4=司机注册，5=司机更换手机，6=司机忘记密码】
     */
    fun checkCode(phone: String, code: String, type: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.CHECK_CODE).append("phone", phone).append("code", code).append("type", type)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 修改手机号
     */
    fun modifyPhone(id: Int, phone: String, code: String): Flowable<ResultData<String>> {
        val request = ParamsBuilder.create().append("server", Api.MODIFY_PHONE).append("id", id).append("phone", phone).append("code", code)
        return request().stringRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 修改密码
     */
    fun modifyPwd(id: Int, passWord: String): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.SET_PWD).append("id", id).append("passWord", passWord)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 广告页
     */
    fun getAd(): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.GET_AD)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }


    /**
     * 是否有新消息
     */
    fun hasNewMsg(id: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.HAS_NEW_MSG)
                .append("id", id).append("peoType", 1)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 获取消息
     */
    fun getMsg(id: Int, page: Int): Flowable<ResultData<ArrayList<Message>>> {
        val request = ParamsBuilder.create().append("server", Api.GET_MSG_LIST)
                .append("id", id).append("peoType", 1).append("page", page).append("rows", PAGE_SIZE)
        return request().getMessages(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 清空消息
     */
    fun clearMsg(id: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.CLEAR_MSG)
                .append("id", id).append("peoType", 1)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * Banner
     */
    fun getBanner(): Flowable<ResultData<ArrayList<com.hfcx.user.beans.Banner>>> {
        val request = ParamsBuilder.create().append("server", Api.GET_BANNER)
        return request().getBanner(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 可用优惠券数量
     * @param type 使用类型(1=票务，2=租车，3=包车，4=快车，5=专车)
     */
    fun couponNum(userId: Int, money: Double, type: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.COUPONS_NUM).append("userId", userId).append("money", money).append("type", type)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 删除优惠券
     */
    fun delCoupon(id: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.DEL_COUPON).append("id", id)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 投诉
     */
    fun complaints(orderId: Int, complaintReason: String, complaintRemark: String?): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.COMPLAINT)
                .append("orderId", orderId).append("complaintReason", complaintReason)
        if (complaintRemark != null)
            request.append("complaintRemark", complaintRemark)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 评价
     */
    fun doEvaluate(orderId: Int, score: Int, remark: String?): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.DO_EVALUATE)
                .append("orderId", orderId).append("score", score)
        if (!remark.isNullOrEmpty())
            request.append("remark", remark!!)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 支付信息
     */
    fun getPayInfo(id: Int, type: Int, orderType: Int): Flowable<ResultData<com.hfcx.user.beans.PayInfo>> {
        val request = ParamsBuilder.create().append("server", Api.GET_PAY_INFO)
                .append("id", id).append("type", type).append("orderType", orderType)
        return request().getPayInfo(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 公司信息
     */
    fun getCompanyInfo(id: Int, lon: Double = 0.0, lat: Double = 0.0): Flowable<ResultData<com.hfcx.user.beans.CompanyInfo>> {
        val request = ParamsBuilder.create().append("server", Api.COMPANY_INFO)
                .append("id", id).append("lon", lon).append("lat", lat)
        return request().companyInfo(request.build(encodeDES)).defaultScheduler()
    }


    /**
     * 添加乘车人
     */
    fun addPerson(userId: Int, name: String, idCards: String, licenseOrNot: Int, licenseNum: String = "", phone: String = "", id: Int = 0): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.ADD_DRIVER)
                .append("userId", userId).append("name", name).append("idCards", idCards)
                .append("licenseOrNot", licenseOrNot).append("licenseNum", licenseNum).append("phone", phone)
                .append("id", id)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 获取乘车人
     */
    fun getPerson(userId: Int, licenseOrNot: Int = 0): Flowable<ResultData<ArrayList<com.hfcx.user.beans.Driver>>> {
        val request = ParamsBuilder.create().append("server", Api.GET_DRIVER)
                .append("userId", userId).append("licenseOrNot", licenseOrNot)
        return request().getDriver(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 获取优惠券
     * type 1=未使用，2=已使用，3=已过期
     * useType 1=票务，2=租车，3=包车，4=快车，5=专车
     */
    fun getCoupons(page: Int, userId: Int, type: Int, useType: Int = 0): Flowable<ResultData<ArrayList<com.hfcx.user.beans.Coupon>>> {
        val request = ParamsBuilder.create().append("server", Api.COUPONS)
                .append("userId", userId).append("page", page).append("rows", PAGE_SIZE)
                .append("type", type).append("useType", useType)
        return request().getCoupons(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 计算距离
     */
    fun getDistance(startLon: Double, startLat: Double, endLon: Double, endLat: Double): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.GET_DISTANCE)
                .append("startLon", startLon).append("startLat", startLat).append("endLon", endLon).append("endLat", endLat)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 上传图片
     */
    fun uploadFile(file: File): Flowable<ResultData<JsonObject>> {
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val part = MultipartBody.Part.createFormData("myfile", file.name, requestFile)
        return request().uploadFile(part).defaultScheduler()
    }

    /**
     * 反馈
     */
    fun feedback(userId: Int, content: String): Flowable<ResultData<JsonObject>> {
        val replace = content.replace("%", "%25").replace("&", "%26")
                .replace("+", "%2B").replace("=", "2D")
                .replace("#", "%23")
        val request = ParamsBuilder.create().append("server", Api.FEEDBACK)
                .append("type", 1).append("userId", userId).append("content", replace)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 积分
     */
    fun getIntegral(id: Int, page: Int): Flowable<ResultData<com.hfcx.user.beans.IntegralData>> {
        val request = ParamsBuilder.create().append("server", Api.INTEGRAL)
                .append("rows", PAGE_SIZE).append("id", id).append("page", page)
        return request().getIntegral(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 修改用户信息
     */
    fun updateUserInfo(id: Int, imgUrl: String, nickName: String, sex: Int, birthDay: String): Flowable<ResultData<JsonObject>> {
        val replace = nickName.replace("%", "%25").replace("&", "%26")
                .replace("+", "%2B").replace("=", "2D")
                .replace("#", "%23")
        val request = ParamsBuilder.create().append("server", Api.UPDATE_USER_INFO)
                .append("imgUrl", imgUrl).append("id", id).append("nickName", replace)
                .append("sex", sex).append("birthDay", birthDay)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }


    /**
     * 评价
     */
    fun evaluateTicket(orderId: Int, userId: Int, hygiene: Int, facilities: Int, punctuality: Int,
                       serviceScore: Int, attitudeScore: Int, content: String): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.TICKET_EVALUATE)
                .append("orderId", orderId).append("userId", userId).append("hygiene", hygiene).append("facilities", facilities)
                .append("punctuality", punctuality).append("serviceScore", serviceScore).append("attitudeScore", attitudeScore)
                .append("content", content)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 邀请情况
     */
    fun getInviteData(userId: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.INVITE_DATA)
                .append("userId", userId)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 邀请记录
     */
    fun getInviteRecord(userId: Int, page: Int): Flowable<ResultData<ArrayList<com.hfcx.user.beans.InviteRecord>>> {
        val request = ParamsBuilder.create().append("server", Api.INVITE_RECORD)
                .append("userId", userId).append("page", page).append("rows", PAGE_SIZE)
        return request().getInviteRecord(request.build(encodeDES)).defaultScheduler()
    }

    /**
     * 获取服务热线
     */
    fun getServicePhone(): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.GET_SERVICE)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }


    /**
     * 验证身份证
     */
    fun checkIdCard(name: String,num:String): Flowable<JsonObject> {
        return request().checkIdCard(Api.JUHE_URL, Const.JUHE_KEY, num,name).defaultScheduler()
    }


    /**
     * 验证身份证
     */
    fun deleteTicket(id: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.DELETE_TICKET)
                .append("id", id)
        return HttpManager.request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }

    fun addTicket(id: Int, number: Int): Flowable<ResultData<JsonObject>> {
        val request = ParamsBuilder.create().append("server", Api.ADD_TICKET)
                .append("id", id).append("number", number)
        return request().simpleRequest(request.build(encodeDES)).defaultScheduler()
    }



}

