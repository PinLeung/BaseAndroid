package com.youyu.common.base

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.youyu.common.R
import com.youyu.common.databinding.ActivityBaseBinding
import com.youyu.common.utils.setClickListener

/**
 *@author YouYu
 *@date 2021/3/12
 *@description
 */
abstract class BaseDBActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var rootBinding: ActivityBaseBinding
    protected abstract val layoutId: Int
    protected lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
        rootBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        rootBinding.lifecycleOwner = this
        rootBinding.ivBack.setOnClickListener { onBackPressed() }
        rootBinding.tvTitle.text = title
        rootBinding.flContent.addView(initContentView())
        initStatusBar()
        initView()
    }

    private fun initContentView(): View {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    //沉浸式状态栏相关
    open fun initStatusBar() {
        //为布局添加statusBar高的PaddingTop
        rootBinding.llBackground.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0)
        //透明状态栏
        BarUtils.transparentStatusBar(this)
    }

    open fun initView() {}

    //返回键
    open fun modifyBackIcon(resId: Int?, click: () -> Unit) {
        rootBinding.apply {
            resId?.let { ivBack.setImageResource(it) }
            ivBack.setClickListener { click() }
        }
    }

    //toolbar显示隐藏
    open fun hideToolBar(flag: Boolean) {
        rootBinding.apply {
            rlToolbar.visibility = if (flag) View.GONE else View.VISIBLE
            viewLine.visibility = if (flag) View.GONE else View.VISIBLE
        }
    }

    override fun getResources(): Resources {
        val resources = super.getResources()
        if (resources != null && resources.configuration.fontScale != 1.0f) {
            val configuration = resources.configuration
            configuration.fontScale = 1.0f
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        return resources
    }

    //文字菜单相关
    open fun showMenu(text: String, textColor: Int? = null, click: () -> Unit) {
        rootBinding.apply {
            textColor?.let {
                tvMenu.setTextColor(ContextCompat.getColor(this@BaseDBActivity, it))
            }
            llTextIcon.visibility = View.GONE
            tvMenu.visibility = View.VISIBLE
            tvMenu.text = text
            tvMenu.setOnClickListener { click() }
        }
    }

    //Icon菜单相关
    open fun showIconMenu(resId: Int, width: Int, height: Int, click: () -> Unit) {
        rootBinding.apply {
            tvMenu.setBackgroundResource(resId)
            llTextIcon.visibility = View.GONE
            tvMenu.visibility = View.VISIBLE
            tvMenu.width = width
            tvMenu.height = height
            tvMenu.setOnClickListener { click() }
        }
    }

    //Icon菜单相关
    open fun showIconIMMenu(resId: Int, width: Int, height: Int, click: (v:View) -> Unit) {
        rootBinding.apply {
            tvMenu.setBackgroundResource(resId)
            llTextIcon.visibility = View.GONE
            tvMenu.visibility = View.VISIBLE
            tvMenu.width = width
            tvMenu.height = height
            tvMenu.setOnClickListener { click(it) }
        }
    }

    //Icon菜单相关
    open fun showIconMenu(click: (v:View) -> Unit) {
        rootBinding.apply {
            llTextIcon.visibility = View.GONE
            tvSpotMenu.visibility = View.VISIBLE
            tvSpotMenu.setOnClickListener { click(it) }

        }
    }

    //TextIcon菜单相关
    open fun showTextIcon(
        text: String,
        textColor: Int? = null,
        resId: Int,
        textClick: () -> Unit,
        iconClick: () -> Unit
    ) {
        rootBinding.apply {
            textColor?.let {
                tvTextIcon.setTextColor(ContextCompat.getColor(this@BaseDBActivity, it))
            }
            tvMenu.visibility = View.GONE
            llTextIcon.visibility = View.VISIBLE
            tvTextIcon.text = text
            tvTextIcon.setOnClickListener { textClick() }
            ivTextIcon.setImageResource(resId)
            ivTextIcon.setOnClickListener { iconClick() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    companion object {
        const val TAG = "TAG"
    }


    open fun  showShortToast(s:String){
        ToastUtils.showShort(s)
    }

    open fun  showToast(s:String){
        ToastUtils.showLong(s)
    }

}