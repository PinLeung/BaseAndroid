package com.youyu.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.text.Html
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

/**
 *@author YouYu
 *@date 2021/4/15
 *@description
 */
class MyImageGetter(val context: Context, val textView: TextView) : Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable {
        val listDrawable = LevelListDrawable()
        val simpleTarget = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val bitmapDrawable = BitmapDrawable(context.resources, resource)
                listDrawable.apply {
                    addLevel(1, 1, bitmapDrawable)
                    setBounds(0, 0, resource.width, resource.height)
                    level = 1
                }
                textView.apply {
                    invalidate()
                    text = text
                }
            }
        }
        Glide.with(context).asBitmap().load(source).into(simpleTarget)
        return listDrawable
    }

}