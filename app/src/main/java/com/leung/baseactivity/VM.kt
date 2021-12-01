package com.leung.baseactivity

import androidx.lifecycle.MutableLiveData
import com.youyu.common.base.BaseViewModel
import com.youyu.common.utils.MKUtils
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 *@author  pin_leung
 *@date 2021/12/1
 *@description
 */
class VM : BaseViewModel() {
    val baseData = MutableLiveData<BaseData>()

    //获取数据
    fun loadInfo(data1:String,data2:String) {
        launch {
            val data =
                RxHttp.get("url")
                    .add("data1",data1)
                    .add("data2",data2)
                    .toResponse<BaseData>()
                    .await()
            //MMKV存储
            MKUtils.encode("data1", data.data1)
            MKUtils.encode("data2", data.data2)
            baseData.value = data
        }
    }
}