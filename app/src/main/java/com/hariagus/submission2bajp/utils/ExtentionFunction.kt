package com.hariagus.submission2bajp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hariagus.submission2bajp.R

fun View.viewVisible() {
    this.visibility = View.VISIBLE
}

fun View.viewGone() {
    this.visibility = View.GONE
}

fun Context.loadImageGlide(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .into(imageView)
}

@SuppressLint("CheckResult")
fun Context.loadImageGlideAnim(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .apply {
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.loading_animation)
        }
        .into(imageView)
}