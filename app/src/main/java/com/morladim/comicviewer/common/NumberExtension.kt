package com.morladim.comicviewer.common

import android.content.res.Resources

/**
 * @author 5k5k
 * Created 2022/5/19
 */
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Double.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()