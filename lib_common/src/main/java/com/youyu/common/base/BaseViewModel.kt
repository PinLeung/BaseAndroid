package com.youyu.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.youyu.common.http.ApiException
import com.youyu.common.http.DataNullException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import rxhttp.wrapper.exception.HttpStatusCodeException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
open class BaseViewModel : ViewModel() {

    //网络请求进度
    val showProcess = MutableLiveData<Boolean>()

    //网络异常
    val networkError = MutableLiveData<String>()

    //服务器异常
    val serviceError = MutableLiveData<String>()

    //请求数据为空
    val nullDataError = MutableLiveData<String>()

    //接口返回code!=200时
    val apiError = MutableLiveData<ApiException>()

    //请求状态
    val submitResult = MutableLiveData<Boolean>()

    //上拉加载状态
    val loadMoreResult = MutableLiveData<Boolean>()


    val subing=MutableLiveData<Boolean>()

    protected fun launch(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                e.printStackTrace()
                when (e) {
                    is CancellationException -> {

                    }
                    is ApiException -> {
                        apiError.value = e
                    }
                    is JsonSyntaxException -> {
                        apiError.value = ApiException(1000, "数据格式错误")
                    }
                    is HttpStatusCodeException -> {
                        serviceError.value = e.message
                    }
                    is DataNullException -> {
                        nullDataError.value = e.msg
                    }
                    is ConnectException -> {
                        networkError.value = "网络异常"
                        nullDataError.value = "网络异常"
                    }
                    is UnknownHostException -> {
                        networkError.value = "未知主机"
                        nullDataError.value = "网络异常"
                    }
                    is SocketTimeoutException -> {
                        networkError.value = "连接超时"
                        nullDataError.value = "网络异常"
                    }
                    else -> {

                    }
                }
            } finally {
                showProcess.value = false
                subing.value=false
            }
        }
    }

    companion object {
        const val TAG = "TAG"
    }

}

