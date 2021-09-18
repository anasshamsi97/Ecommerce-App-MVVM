package com.example.ecommerceapp.ui.utils

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.widget.ImageView
import android.widget.RatingBar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ecommerceapp.R

/* DataBinding adapters for across the app */

@BindingAdapter(value = ["image"])
fun setImage(
    view: ImageView,
    url: String?
) {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    val color = ContextCompat.getColor(view.context, R.color.orange_200)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) circularProgressDrawable.colorFilter =
        BlendModeColorFilter(
            color,
            BlendMode.SRC_ATOP
        ) else circularProgressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

    circularProgressDrawable.start()
    GlideApp.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(circularProgressDrawable)
        .error(R.mipmap.ic_launcher)
        .into(view)
}

@BindingAdapter(value = ["rating"])
fun setRating(
    view: RatingBar,
    rating: Double
) {
    view.rating = (rating * 10).toFloat()
}