package com.youyu.common.base

import android.app.Activity
import android.os.Process
import java.util.*

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
object ActivityCollector {

    private val activities: MutableList<Activity> = ArrayList()

    @JvmStatic
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    @JvmStatic
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }


    @JvmStatic
    fun finishAll(ac:Activity) {
        for (activity in activities) {
            if (!activity.isFinishing&&ac!=Activity()) {
                activity.finish()
            }
        }
    }





    @JvmStatic
    fun retrunMain() {

        var  count=0;
            for (ac in activities){
                count++;
                if (count>1){
                    if (!ac.isFinishing){
                        ac.finish()
                    }
                }


        }
    }


    /**
     * 退出应用程序
     */
    @JvmStatic
    fun appExit() {
        try {
            Process.killProcess(Process.myPid())
        } catch (e: Exception) {
        }
    }



}