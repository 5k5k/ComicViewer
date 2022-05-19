package com.morladim.comicoviewer.common

import android.content.res.Resources

/**
 * @author gongw
 * Created 2022/5/19 at 8:06
 */
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Double.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()