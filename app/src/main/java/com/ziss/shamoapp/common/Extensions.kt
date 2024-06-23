package com.ziss.shamoapp.common

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

fun ImageView.loadImage(@DrawableRes url: Int) {
    Glide.with(this.context).load(url).into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(url).into(this)
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Int.toLocalCurrency(): String {
    val locale = Locale("id", "ID")
    val currencyFormat = NumberFormat.getCurrencyInstance(locale).apply {
        maximumFractionDigits = 0
    }
    return currencyFormat.format(this)
}