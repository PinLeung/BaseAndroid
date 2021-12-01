package com.youyu.common.http

import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.annotation.Domain

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
object Url {

    //正式线上
//    const val H5URL = "http://web.cqydhome.com/#/"
//    const val BaseFileUrl = "http://www.cqydhome.com"
//    const val BaseUrl = "http://gateway.cqydhome.com"

//    测试线上
      const val H5URL = "http://123.60.17.240:8065/#/"
    const val BaseFileUrl = "http://123.60.17.240:8099"
    const val BaseUrl = "http://123.60.17.240:8099"



    //本地
//      const val H5URL = "http://192.168.1.5:8202/#/"
//    const val BaseFileUrl = "http://192.168.1.28:8099"
//    const val BaseUrl = "http://192.168.1.28:8099"


    @DefaultDomain
//    const val baseUrl = "http://192.168.1.28:8083"
    const val baseUrl = "${BaseUrl}"
//    const val baseUrl = "http://47.108.162.58:8099"

    @Domain(name = "BaseUrlLogin")
//    const val baseUrlLogin = "http://192.168.1.28:8073"
    const val baseUrlLogin = "${BaseUrl}"
//    const val baseUrlLogin = "http://47.108.162.58:8099"


}