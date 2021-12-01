package com.leung.baseactivity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 *@author  pin_leung
 *@date 2021/12/1
 *@description
 */
@Keep
@Parcelize
data class BaseData(
    val data1:String?,
    val data2:String?,
): Parcelable
