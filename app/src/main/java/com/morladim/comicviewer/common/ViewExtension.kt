package com.morladim.comicviewer.common

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.morladim.comicviewer.R
import com.morladim.comicviewer.ui.FolderDividerDecoration
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/19 at 8:56
 */
fun RecyclerView.addVerticalDecorator() {
    val dividerDrawable = ContextCompat.getDrawable(this.context, R.drawable.folder_divider)
    dividerDrawable?.let {
        this.addItemDecoration(FolderDividerDecoration(context = this.context, drawable = dividerDrawable))
    }
}

fun ImageView.loadImage(file: File) {
    Glide.with(this)
//        .asBitmap()
        .load(file)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
        .format(DecodeFormat.PREFER_RGB_565)
//        .skipMemoryCache(true)
        .into(this)
//        .clearOnDetach()

}