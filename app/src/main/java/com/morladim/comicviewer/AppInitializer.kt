package com.morladim.comicviewer

import android.content.Context
import androidx.startup.Initializer
import com.morladim.comicviewer.common.LogUtils

/**
 * @author 5k5k
 * Created 2022/5/20 at 9:54
 */
class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LogUtils.init("===M##ComicViewer##")

    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}