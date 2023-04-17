package com.example.mygallery

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target


class ImageAdapter(context: Context, imagePaths: ArrayList<String>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private val context: Context
    private val imagePaths: ArrayList<String>

    init {
        this.context = context
        this.imagePaths = imagePaths
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.img_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = imagePaths[position]

        // Load the image into the ImageView
        Glide.with(context)
            .load(imagePath)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imagePaths.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
}

