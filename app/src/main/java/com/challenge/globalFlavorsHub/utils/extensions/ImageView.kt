package com.challenge.globalFlavorsHub.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromURL(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}
