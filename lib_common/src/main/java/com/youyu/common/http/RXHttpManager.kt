package com.youyu.common.http

import android.util.Log
import com.youyu.common.utils.MKUtils
import okhttp3.OkHttpClient
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.ssl.HttpsUtils
import java.util.concurrent.TimeUnit

/**
 *@author YouYu
 *@date 2021/3/12
 *@description
 */
object RXHttpManager {

    private const val TAG = "TAG"

    fun init() {
        //初始化
        val sslParams = HttpsUtils.getSslSocketFactory()
        val ok = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .build()
        RxHttp.init(ok, true)
        //设置公共参数
        RxHttp.setOnParamAssembly { p ->
            val method = p.method
            val token = MKUtils.decodeString("token")
            if (method.isGet) {
                Log.e(TAG, "----------GET----------")
            } else if (method.isPost) {
                Log.e(TAG, "----------POST----------")
            }
            p.addHeader("X-Access-Token", token)
        }
    }

}