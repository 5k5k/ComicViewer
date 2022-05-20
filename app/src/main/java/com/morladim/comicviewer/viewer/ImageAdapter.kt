package com.morladim.comicviewer.viewer

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
 * Created 2022/5/19
 */
class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    var data: List<File> = ArrayList()

    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.imageItem)

        fun bind(file: File) {
            image.loadImage(file)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}