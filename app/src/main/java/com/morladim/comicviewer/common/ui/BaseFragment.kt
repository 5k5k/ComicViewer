package com.morladim.comicviewer.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 *
 * @Author 5k5k
 * @Date 2021/12/30
 */
open class BaseFragment : Fragment {

    open val enableCachedView = true

    var cachedView: View? = null

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (enableCachedView && cachedView != null) {
            cachedView
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!enableCachedView || cachedView == null) {
            cachedView = view
            initView(view, savedInstanceState)
        }
    }

    open fun initView(view: View, savedInstanceState: Bundle?) {

    }
}