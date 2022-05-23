package com.morladim.comicviewer.common.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

/**
 * @author 5k5k
 * Created 2022/5/19
 */
class FolderDividerDecoration(drawable: Drawable?, context: Context) : RecyclerView.ItemDecoration() {

    private val divider: Drawable

    private val attrs = intArrayOf(android.R.attr.listDivider)

    init {
        if (drawable == null) {
            val styledAttributes = context.obtainStyledAttributes(attrs)
            divider = styledAttributes.getDrawable(0)!!
            styledAttributes.recycle()
        } else {
            divider = drawable
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}