package com.example.android.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.PhotoGridAdapter

/**
 * Binding Adapters are annotated methods used to create custom setters for custom properties
 * of a view.
 * imageUrl - attribute name in grid_view_item.xml
 * listData - attribute in fragment_overview.xml
 * @param imgView - type of target view
 * @param imgUrl - value being set to the view attribute
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Initialises the PhotoGridAdapter with the list of MarsPhoto objects. Using a BindingAdapter
 * to set the RecyclerView data causes data binding to automatically observe the LiveData for
 * the list of MarsPhoto objects. Then the binding adapter is called automatically when the
 * MarsPhoto list changes.
 * @param recyclerView - type of target view
 * @param marsPhotos - value being set to the view attribute
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, marsPhotos: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(marsPhotos)
}