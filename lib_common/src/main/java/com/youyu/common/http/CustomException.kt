package com.youyu.common.http

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
class ApiException(var code: Int, var msg: String) : RuntimeException()

class DataNullException(var code: Int, var msg: String) : RuntimeException()