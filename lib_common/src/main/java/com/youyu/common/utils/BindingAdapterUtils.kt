package com.youyu.common.utils

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

/**
 *@author YouYu
 *@date 2021/3/10
 *@description
 */
object BindingAdapterUtils {

    private const val TAG = "TAG"

    @JvmStatic
    @BindingAdapter("imgUrl", "imgRadius", requireAll = false)
    fun setImgURL(view: ImageView, url: String?, radius: Int?) {
        url?.let {
            if (it.isNotEmpty()) {
                Glide.with(view.context).load(url)
                    .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius ?: 30)))
                    .into(view)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imgCircleUrl")
    fun setImgCircleURL(view: ImageView, url: String?) {
        url?.let {
            if (it.isNotEmpty()) {
                Glide.with(view.context).load(url)
                    .transform(MultiTransformation(CenterCrop(), CircleCrop()))
                    .into(view)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("backgroundResource")
    fun setBackgroundValue(view: View, resId: Int?) {
        resId?.let {
            view.setBackgroundResource(it)
        }
    }

    @JvmStatic
    @BindingAdapter("bgColor")
    fun setBackgroundColor(view: View, resId: Int?) {
        resId?.let {
            try {
                view.setBackgroundColor(ContextCompat.getColor(view.context, it))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("textColorResource")
    fun setTextColorValue(view: TextView, resId: Int?) {
        try {
            resId?.let {
                view.setTextColor(ContextCompat.getColor(view.context, it))
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    @BindingAdapter("textToHtml")
    fun setTextHtml(view: TextView, text: String?) {
        text?.let {
            val imageGetter = MyImageGetter(view.context, view)
            val sequence = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY, imageGetter, null)
            } else {
                Html.fromHtml(text)
            }
            view.text = sequence
        }
    }

    @JvmStatic
    @BindingAdapter("loadUrl")
    fun loadUrl(view: WebView, url: String?) {
        url?.let {
            view.loadUrl(it)
        }
    }

    @JvmStatic
    @BindingAdapter("loadData")
    fun loadData(view: WebView, text: String?) {
        text?.let {
            view.loadData(it, "text/html", "utf-8")
        }
    }

    @JvmStatic
    @BindingAdapter("loadBaseData")
    fun loadBaseData(view: WebView, text: String?) {
        text?.let {
            view.loadDataWithBaseURL(null, it, "text/html", "utf-8", null)
        }
    }

}