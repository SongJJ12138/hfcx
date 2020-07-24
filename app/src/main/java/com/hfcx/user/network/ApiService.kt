package com.hfcx.user.network

import com.google.gson.JsonObject
import com.hfcx.user.beans.Message
import com.hfcx.user.beans.ResultData
import io.reactivex.Flowable
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 *
 */
interface ApiService {

    @POST("app/server")
    fun stringRequest(@Query("key") key: String): Flowable<ResultData<String>>

    @POST("app/server")
    fun simpleRequest(@Query("key") key: String): Flowable<ResultData<JsonObject>>

    @POST("app/server")
    fun getBanner(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.Banner>>>

    @POST("app/server")
    fun getNearbyDriver(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.NearbyDriver>>>



    @POST("app/server")
    fun getPayInfo(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.PayInfo>>



    @POST("app/server")
    fun getRentOrderList(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.RentOrder>>>


    @POST("app/server")
    fun cancelCustomr(@Query("key") key: String):Flowable<ResultData<String>>
    @POST("app/server")
    fun refundCustomr(@Query("key") key: String):Flowable<ResultData<String>>
    @POST("app/server")
    fun sendSms(@Query("phone") server: String, @Query("phone") phone: String, @Query("type") type: Int): Flowable<ResultData<JsonObject>>

    @POST("app/server")
    fun getRentMain(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.RentMain>>



    @POST("app/server")
    fun getCarList(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.CarList>>>



    @POST("app/server")
    fun getCarLabels(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.CarLabel>>>

    @POST("app/server")
    fun getCarBrands(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.CarBrand>>>

    @POST("app/server")
    fun getCarDetail(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.CarDetail>>


    @POST("app/server")
    fun getCarSafe(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.CarSafe>>

    @POST("app/server")
    fun companyInfo(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.CompanyInfo>>

    @POST("app/server")
    fun rentOrderDetail(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.RentOrder>>


    @POST("app/server")
    fun companyPoint(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.CompanyPoint>>>

    @POST("app/server")
    fun getDriver(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.Driver>>>

    @POST("app/server")
    fun getCoupons(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.Coupon>>>

    @POST("app/server")
    fun getOpenCity(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.OpenCity>>>

    @POST("app/server")
    fun getEndCity(@Query("key") key: String): Flowable<ResultData<ArrayList<com.hfcx.user.beans.OpenProvince>>>


    @POST("app/server")
    fun getMessages(@Query("key") key: String): Flowable<ResultData<ArrayList<Message>>>


    @POST("app/server")
    fun getIntegral(@Query("key") key: String): Flowable<ResultData<com.hfcx.user.beans.IntegralData>>

    /**文件上传**/
    @Multipart
    @POST("app/public/uplaodImg")
    fun uploadFile(@Part filePart: MultipartBody.Part): Flowable<ResultData<JsonObject>>


    @POST("app/server")
    fun getInviteRecord(@Query("key") key:String):Flowable<ResultData<ArrayList<com.hfcx.user.beans.InviteRecord>>>



    @GET
    fun checkIdCard(@Url url:String, @Query("key") key: String, @Query("idcard") idcard: String, @Query("realname") realname: String) :Flowable<JsonObject>
}