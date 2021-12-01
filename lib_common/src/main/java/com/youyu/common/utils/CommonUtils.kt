package com.youyu.common.utils

import android.view.View
import com.blankj.utilcode.util.ToastUtils

/**
 *@author YouYu
 *@date 2021/3/12
 *@description
 */

fun showToast(content: String) {
    ToastUtils.showShort(content)
}


fun  showLongToast(s:String){
    ToastUtils.showLong(s)
}


//防抖动点击
private var startTime = 0L
fun <T : View> T.setClickListener(time: Long = Constants.INTERVAL, block: (T) -> Unit) {
    setOnClickListener {
        val endTime = System.currentTimeMillis()
        if (endTime - startTime > time) {
            block(this)
            startTime = endTime
        }
    }
}