package com.morladim.comicviewer.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 *
 * @Author 5k5k
 * @Date 2022/1/1
 */
open class BaseBindingFragment<T : ViewDataBinding>(private val layoutId: Int) :
    Fragment(layoutId) {
    open val enableCachedView = true

    var cachedView: View? = null

    var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (enableCachedView && cachedView != null) {
            cachedView
        } else {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            return binding!!.root
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