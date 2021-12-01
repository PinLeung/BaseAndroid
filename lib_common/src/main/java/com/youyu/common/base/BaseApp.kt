package com.youyu.common.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.drake.brv.utils.BRV
import com.jeremyliao.liveeventbus.LiveEventBus
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.youyu.common.BR
import com.youyu.common.BuildConfig
import com.youyu.common.http.RXHttpManager

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
open class BaseApp : Application() {

    companion object {
        lateinit var instance: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    private fun init() {
        //初始化ARouter
        if (!BuildConfig.isRelease) { // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()// 打印日志
            ARouter.openDebug()// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(instance)
        //初始化MMKV
        MMKV.initialize(instance)
        //刷新布局要求必须先初始化
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ -> MaterialHeader(context) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> ClassicsFooter(context) }
        //RXHttp初始化
        RXHttpManager.init()
        //LiveEventBus初始化
        LiveEventBus
            .config()
            .autoClear(true)
            .lifecycleObserverAlwaysActive(true)
    }

}