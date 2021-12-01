package com.youyu.common.base

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.youyu.common.R
import com.youyu.common.databinding.ActivityBaseBinding
import com.youyu.common.utils.setClickListener
import com.youyu.common.utils.showToast
import com.youyu.common.widget.ProgressDialogFragment

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var rootBinding: ActivityBaseBinding
    protected abstract val layoutId: Int
    protected abstract val vmClass: Class<VM>
    protected val vm by lazy { ViewModelProvider(this).get(vmClass) }
    protected lateinit var binding: DB
    private var progressDialogFragment: ProgressDialogFragment? = null

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
        observe()
    }

    //解决键盘顶掉title
    open fun initKey(key: Boolean) {
        if (key){
            ImmersionBar.with(this)
                .keyboardEnable(true,WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED)
                .init();
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig.fontScale != 1f) {
            Log.e(TAG, "onConfigurationChanged: "+newConfig.fontScale )
            resources
        }
        super.onConfigurationChanged(newConfig)
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

    //LiveData相关
    open fun observe() {
        vm.apply {
            //加载框
            showProcess.observe(this@BaseActivity, {
                if (it) showProgressDialog() else hideProgressDialog()
            })
            //api异常
            apiError.observe(this@BaseActivity, {
                when (it.code) {
                    401 -> Log.e(TAG, "----------401Unauthorized----------")
                    403 -> Log.e(TAG, "----------403Forbidden----------")
                    404 -> Log.e(TAG, "----------404Not Found----------")
                    20001 ->showToast("请重新登录")
                }
            })
            //网络异常
            networkError.observe(this@BaseActivity, {
                showToast(it)
            })
            //服务器异常
            serviceError.observe(this@BaseActivity, {
                showToast(it)
            })
        }
    }

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

    //文字菜单相关
    open fun showMenu(text: String, textColor: Int? = null, click: () -> Unit) {
        rootBinding.apply {
            textColor?.let {
                tvMenu.setTextColor(ContextCompat.getColor(this@BaseActivity, it))
            }
            llTextIcon.visibility = View.GONE
            tvMenu.visibility = View.VISIBLE
            tvMenu.text = text
            tvMenu.setClickListener { click() }
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
            tvMenu.setClickListener { click() }
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
                tvTextIcon.setTextColor(ContextCompat.getColor(this@BaseActivity, it))
            }
            tvMenu.visibility = View.GONE
            llTextIcon.visibility = View.VISIBLE
            tvTextIcon.text = text
            tvTextIcon.setClickListener { textClick() }
            ivTextIcon.setImageResource(resId)
            ivTextIcon.setClickListener { iconClick() }
        }
    }

    private fun showProgressDialog() {
        if (progressDialogFragment == null) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment?.let {
            if (!it.isAdded) {
                it.show(supportFragmentManager, R.string.tv_loading, false)
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

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
        hideProgressDialog()
        progressDialogFragment=null
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