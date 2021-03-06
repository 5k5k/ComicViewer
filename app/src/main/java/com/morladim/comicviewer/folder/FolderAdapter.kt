package com.morladim.comicviewer.folder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.morladim.comicviewer.R
import com.morladim.comicviewer.folder.FolderAdapter.FolderViewHolder
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/18
 */
class FolderAdapter : RecyclerView.Adapter<FolderViewHolder>() {

    var data: List<File> = ArrayList()
    var listener: FolderAdapterListener? = null

    inner class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var text: TextView = itemView.findViewById(R.id.folderName)
        private var button: Button = itemView.findViewById(R.id.detailButton)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(data[layoutPosition])
            }
            button.setOnClickListener {
                listener?.onStartClick(data[layoutPosition])
            }
        }

        fun bind(file: File) {
            text.text = file.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false))
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface FolderAdapterListener {

        fun onItemClick(file: File)

        fun onStartClick(file: File)
    }
}