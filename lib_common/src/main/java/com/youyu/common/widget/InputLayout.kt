package com.youyu.common.widget

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.youyu.common.R
import com.youyu.common.databinding.LayoutCommonInputBinding
import com.youyu.common.utils.EditUtil

/**
 *@author YouYu
 *@date 2021/3/17
 *@description
 */
class InputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutCommonInputBinding
    private var listener: OnTextListener? = null
    private var mMode = 0 //账号0，密码1，验证码2
    private var mInputType = InputType.TYPE_CLASS_TEXT //输入类型
    private var mTextHint = "" //hint文字
    private var mTextMaxLength = 0 //文字长度
    private var mVisible = View.VISIBLE //文字长度
    private var mForgetTextColor = Color.parseColor("#47B36B")
    private var mLeftTips = ""//国际代码
    private var mShowLeftTips = false //是否显示国际代码
    private var mShowLine = true //是否显示底部线条

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.InputLayout)
        try {
            mMode = ta.getInt(R.styleable.InputLayout_mode, mMode)
            mInputType = ta.getInt(R.styleable.InputLayout_android_inputType, mInputType)
            mTextHint = ta.getString(R.styleable.InputLayout_android_hint).toString()
            mTextMaxLength = ta.getInt(R.styleable.InputLayout_android_maxLength, mTextMaxLength)
            mVisible = ta.getInt(R.styleable.InputLayout_android_visibility, mVisible)
            mForgetTextColor =
                ta.getColor(R.styleable.InputLayout_forgetTextColor, mForgetTextColor)
            mLeftTips = ta.getString(R.styleable.InputLayout_leftTips).toString()
            mShowLeftTips = ta.getBoolean(R.styleable.InputLayout_showLeftTips, mShowLeftTips)
            mShowLine = ta.getBoolean(R.styleable.InputLayout_showBottomLine, mShowLine)
        } finally {
            ta.recycle()
        }
        val view = LayoutInflater.from(context).inflate(R.layout.layout_common_input, this, false)
        binding = DataBindingUtil.bind(view)!!
        visibility = when (mVisible) {
            1 -> View.INVISIBLE
            2 -> View.GONE
            else -> View.VISIBLE
        }
        addView(view)
        initView()
    }

    private fun initView() {
        binding.apply {
            cbPwdVisible.visibility = if (mMode == 1) View.VISIBLE else View.GONE
            tvLeftTips.text = mLeftTips
            tvLeftTips.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0F)
            tvLeftTips.visibility = if (mShowLeftTips) View.VISIBLE else View.GONE
            viewLine.visibility = if (mShowLine) View.VISIBLE else View.GONE
            edtInput.apply {
                inputType = mInputType
                hint = mTextHint
                if (mTextMaxLength>0) {
                    filters = arrayOf(object : InputFilter.LengthFilter(mTextMaxLength){})
                }
            }
            addListener()
        }
    }

    private fun addListener() {
        binding.apply {
            var isDel=false;
            edtInput.doOnTextChanged { text, start, before, count ->
                text?.let {
                    listener?.textChanged(it.toString().replace(" ",""))
//                    if (mTextMaxLength != 0 && it.length > mTextMaxLength) {
//                        val newStr = it.substring(0, mTextMaxLength)
//                        var se=edtInput.selectionStart
//                        edtInput.apply {
//                            setText(newStr)
//                            if (se>newStr.length)
//                                se=newStr.length
//                            setSelection(se)
//                        }
//                    }
                    if (mMode == 0) {
                        ivDelete.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
                        edtInput.apply {
                            if (it.isEmpty()) {
                                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0F)
                            } else {
                                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0F)
                            }
                        }

                    }

                }

            }
            edtInput.doAfterTextChanged{
                if (mMode==0){
                    EditUtil.onTextChanged344(edtInput,it.toString(),isDel)
                    isDel=false
                }
            }
            //输入框清空
            ivDelete.setOnClickListener { edtInput.setText("") }
            //密码可见
            cbPwdVisible.setOnCheckedChangeListener { _, isChecked ->
                edtInput.inputType =
                    if (isChecked) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                edtInput.setSelection(edtInput.text.toString().length)
            }
            if (mMode==0){
                edtInput.setOnKeyListener { _, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                        var se=edtInput.selectionStart
                        if (se==4){
                            edtInput.setSelection(3)
                        }
                        if (se==9){
                            edtInput.setSelection(8)
                        }
                        isDel=true
                    }
                    false
                }
            }
        }
    }

    //获取内容
    fun getText(): String = binding.edtInput.text.toString().replace(" ","")

    //设置内容
    fun setText(content: String?) {
        content?.let {
            binding.edtInput.setText(content)
        }
    }

    //清空内容
    fun clearAll() {
        binding.edtInput.setText("")
    }

    //监听文本
    fun setOnTextChangedListener(listener: OnTextListener) {
        this.listener = listener
    }

    interface OnForgetClickListener {
        fun verifyClick(view: View)
    }

    interface OnTextListener {
        fun textChanged(it: String)
    }

    fun  editQuestFocus(){
        binding.edtInput.setFocusable(true);
        binding.edtInput.setFocusableInTouchMode(true);
        binding.edtInput.requestFocus();
    }
    fun getEdit(): EditText {
        return binding.edtInput
    }

}