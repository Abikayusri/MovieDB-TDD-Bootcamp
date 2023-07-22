package abika.sinau.mymoviedb.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(url: String, placeholder: Int, error: Int) {
    Glide
        .with(this)
        .load(url)
        .error(error)
        .placeholder(placeholder)
        .into(this)
}