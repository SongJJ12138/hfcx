package com.hfcx.user.utils

import android.app.Activity
import java.util.*
import kotlin.collections.ArrayList

object ActivityManager {
    private var mActivityStack= ArrayList<Activity>()

    /**
     * 添加一个activity到栈顶.
     *
     * @param activity 添加的activity
     */
     fun pushActivity(activity:Activity) {
        mActivityStack.add(activity)
    }


    /**
     * 结束当前的activity,在activity的onDestroy中调用.
     */
    fun popActivity(activity:Activity) {
        if (mActivityStack != null && !mActivityStack.isEmpty()) {
            mActivityStack.remove(activity)
        }
    }

    //移除所有的Activity
    fun removeAll(){
        for (activity in mActivityStack ) {
            activity?.finish()
        }
    }
}