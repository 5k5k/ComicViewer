package com.morladim.comicviewer.common

import android.util.Log

/**
 * 日志工具类
 *
 * @Author 5k5k
 * @Date 2021/11/13-16:01
 */
object LogUtils {

    private lateinit var appTag: String

    private const val CURRENT_STACK = 5 + 1
    private const val PREVIOUS_STACK = 4 + 1

    /**
     * 初始化注入app的tag，应该区别于app的包名，属于逻辑上的tag
     */
    fun init(appTag: String) {
        this.appTag = appTag
    }

    fun trace(message: String) {
        trace(message, "")
    }

    fun trace(message: String, tag: String) {
        Log.d(appTag + tag, message + getTrackInfo())
    }

    fun log(f: (tag: String?, message: String) -> Unit, tag: String?, message: String) {
        f(appTag + tag, message)
    }

    /**
     * 获取调用栈信息
     */
    private fun getTrackInfo(): String {
        return try {
            val elements = Thread.currentThread().stackTrace
            if (elements.size < CURRENT_STACK) {
                ""
            } else {
                val clazz = elements[PREVIOUS_STACK].className.substring(
                    elements[PREVIOUS_STACK].className.lastIndexOf(".") + 1
                )
                val method = elements[PREVIOUS_STACK].methodName + "()"
                val line =
                    " at (" + elements[PREVIOUS_STACK].className + ".java:" + elements[PREVIOUS_STACK].lineNumber.toString() + ")";
                " $clazz $method$line"
            }
        } catch (e: Exception) {
            ""
        }
    }
}