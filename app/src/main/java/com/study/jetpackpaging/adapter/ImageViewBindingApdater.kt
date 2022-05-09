package com.study.jetpackpaging.adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.study.jetpackpaging.R

/**
 * 自定义databinding适配器 必须是静态方法
 */
class ImageViewBindingApdater {
    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageview: ImageView, url: String) {
            if (!TextUtils.isEmpty(url)) {
                Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background)
                    .into(imageview)
            } else {
                imageview.setBackgroundColor(Color.GRAY)
            }
        }
    }
}