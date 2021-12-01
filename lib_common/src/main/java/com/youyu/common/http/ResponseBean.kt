package com.youyu.common.http

/**
 *@author YouYu
 *@date 2021/3/11
 *@description
 */
data class ResponseBean<T>(
    val code: Int,
    val message: String,
    val result: T,
    val success: Boolean,
    val timestamp: Long
)
