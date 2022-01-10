package com.hariagus.submission2bajp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hariagus.submission2bajp.R
import java.io.Serializable

fun View.viewVisible() {
    this.visibility = View.VISIBLE
}

fun View.viewGone() {
    this.visibility = View.GONE
}

fun Context.loadImageGlide(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .placeholder(android.R.color.darker_gray)
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

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    this.startActivity(intent)
}

fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        when (val value = it.second) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}