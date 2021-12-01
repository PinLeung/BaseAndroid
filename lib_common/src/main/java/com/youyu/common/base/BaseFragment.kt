package com.youyu.common.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.youyu.common.R
import com.youyu.common.utils.showToast
import com.youyu.common.widget.ProgressDialogFragment

/**
 *@author YouYu
 *@date 2021/3/11
 *@description
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    protected abstract val layoutId: Int
    protected abstract val vmClass: Class<VM>
    protected val vm by lazy { ViewModelProvider(this).get(vmClass) }
    protected lateinit var binding: DB
    private var lazyLoad = false
    private var progressDialogFragment: ProgressDialogFragment? = null

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
        observe()
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

    open fun observe() {
        vm.apply {
            //加载框
            showProcess.observe(viewLifecycleOwner, {
                if (it) showProgressDialog() else hideProgressDialog()
            })
            //api异常
            apiError.observe(viewLifecycleOwner, {
                when (it.code) {
                    401 -> Log.e(TAG, "----------401Unauthorized----------")
                    403 -> Log.e(TAG, "----------403Forbidden----------")
                    404 -> Log.e(TAG, "----------404Not Found----------")
                }
            })
            //网络异常
            networkError.observe(viewLifecycleOwner, {
                showToast(it)
            })
            //服务器异常
            serviceError.observe(viewLifecycleOwner, {
                showToast(it)
            })
        }
    }

    private fun showProgressDialog() {
        if (progressDialogFragment == null) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment?.let {
            if (!it.isAdded) {
                it.show(childFragmentManager, R.string.tv_loading, false)
            }
        }
    }

    private fun hideProgressDialog() {
        progressDialogFragment?.let {
            if (it.isAdded) {
                it.dismiss()
            }
        }
    }

    companion object {
        const val TAG = "TAG"
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
        progressDialogFragment=null
    }
}