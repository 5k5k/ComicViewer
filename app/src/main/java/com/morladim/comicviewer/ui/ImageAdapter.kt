package com.morladim.comicviewer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.morladim.comicviewer.R
import com.morladim.comicviewer.common.loadImage
import java.io.File




/**
 * @author 5k5k
 * Created 2022/5/19 at 9:49
 */
class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    var list: List<File>? = null

    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.imageItem)

        fun bind(file: File) {
            image.loadImage(file)
//                image.setImageURI(Uri.fromFile(file))
//            Glide.with(image.context).load("https://csdnimg.cn/release/blogv2/dist/pc/img/original.png").into(image)

//            image.setImageResource(R.drawable.test)
//            Glide.with(image.context)
//                .load("https://csdnimg.cn/release/blogv2/dist/pc/img/original.png")
//                .listener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        return false
//                    }
//                })
//                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        if (list != null) {
            holder.bind(list!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }
}