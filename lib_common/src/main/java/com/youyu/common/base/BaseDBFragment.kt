package com.youyu.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 *@author YouYu
 *@date 2021/3/11
 *@description
 */
abstract class BaseDBFragment<DB : ViewDataBinding> : Fragment() {

    protected abstract val layoutId: Int
    protected lateinit var binding: DB
    private var lazyLoad = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        initView()
    }

    open fun initView() {}

    override fun onResume() {
        super.onResume()
        if (!lazyLoad) {
            lazyLoadData()
            lazyLoad = true
        }
    }

    abstract fun lazyLoadData()

    companion object {
        const val TAG = "TAG"
    }

}