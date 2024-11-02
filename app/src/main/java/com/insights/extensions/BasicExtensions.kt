package com.insights.extensions

import android.view.View
import android.widget.ImageView
//import com.bumptech.glide.Glide

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}


fun View.hide() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

//fun ImageView.load(url: String) : ImageView {
//    Glide.with(this)
//        .load(url)
//        .into(this)
//    return this
//}

