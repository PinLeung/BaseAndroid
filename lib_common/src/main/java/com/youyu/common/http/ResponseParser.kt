package com.youyu.common.http

import okhttp3.Response
import okio.IOException
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.convert
import java.lang.reflect.Type

/**
 *@author YouYu
 *@date 2021/3/11
 *@description
 */
@Parser(name = "Response")
open class ResponseParser<T> : AbstractParser<T> {

    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: Response): T {
        val type: Type = ParameterizedTypeImpl(ResponseBean::class.java, mType)
        val data: ResponseBean<T> = response.convert(type)
        if (data.code == 200) {
            if (data.result == null) {
                throw DataNullException(data.code, data.message)
            } else {
                return data.result
            }
        } else {
            throw ApiException(data.code, data.message)
        }
    }

}