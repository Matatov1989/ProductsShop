package com.example.productsshop.adapters

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.productsshop.R
import com.example.productsshop.models.ProductColor

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide
            .with(view.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.baseline_android_24)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("spinnerItems")
    fun setSpinnerItems(
        spinner: Spinner,
        items: List<String>,
    ) {
        val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
   //     spinner.onItemSelectedListener = listener
    }
}