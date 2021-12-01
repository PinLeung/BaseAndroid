package com.youyu.common.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.youyu.common.R
import com.youyu.common.databinding.LayoutCommonActionbarBinding

/**
 *@author YouYu
 *@date 2021/3/25
 *@description
 */
class CustomActionBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutCommonActionbarBinding
    private var listener: OnItemClickListener? = null
    private var textContent: String? = null
    private var iconDrawable: Drawable? = null
    private var showBack: Boolean = true

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomActionBar)
        try {
            textContent = ta.getString(R.styleable.CustomActionBar_android_text)
            iconDrawable = ta.getDrawable(R.styleable.CustomActionBar_menuIcon)
            showBack = ta.getBoolean(R.styleable.CustomActionBar_showBack, showBack)
        } finally {
            ta.recycle()
        }
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_common_actionbar, this, false)
        binding = DataBindingUtil.bind(view)!!
        addView(view)
        initView()
    }

    private fun initView() {
        binding.apply {
            textContent?.let { tvTitle.text = it }
            iconDrawable?.let { ivMenu.setImageDrawable(it) }
            ivBack.visibility = if (showBack) View.VISIBLE else View.GONE
            ivBack.setOnClickListener { v -> listener?.itemClick(v, 0) }
            tvMenu.setOnClickListener { v -> listener?.itemClick(v, 1) }
        }
    }

    /**
     * 监听事件0：返回按钮，1：菜单按钮
     */
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun itemClick(view: View, position: Int)
    }

}