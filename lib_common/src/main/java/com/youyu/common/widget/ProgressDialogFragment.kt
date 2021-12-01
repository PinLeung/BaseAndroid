package com.youyu.common.widget

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ConvertUtils
import com.youyu.common.R

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
class ProgressDialogFragment : DialogFragment() {

    private var messageResId: Int? = null

    companion object {
        fun newInstance() = ProgressDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            attributes.apply {
                width = ConvertUtils.dp2px(104f)
                height = ConvertUtils.dp2px(104f)
                dimAmount = 0f
            }
            setBackgroundDrawable(ColorDrawable())
        }
    }

    fun show(
        fragmentManager: FragmentManager,
        @StringRes messageResId: Int,
        isCancelable: Boolean = false
    ) {
        this.messageResId = messageResId
        this.isCancelable = isCancelable
        show(fragmentManager, "progressDialogFragment")
    }

}