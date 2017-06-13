package binding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.bumptech.glide.Glide

object ImageBinding {

    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }

    @BindingAdapter("android:src", "placeHolder")
    fun setImageUrl(imageView: ImageView, url: String,
                    placeholder: Drawable) {
        Glide.with(imageView.context)
                .load(url)
                .placeholder(placeholder)
                .into(imageView)
    }
}