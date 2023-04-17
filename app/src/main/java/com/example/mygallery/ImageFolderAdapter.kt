package com.example.mygallery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File


class ImageFolderAdapter(context: Context, imageFolders: HashMap<String, ArrayList<String>>) :

    RecyclerView.Adapter<ImageFolderAdapter.ImageFolderViewHolder>() {
    private val context: Context
    private val folderNames: ArrayList<String>
    private val imageFolders: HashMap<String, ArrayList<String>>

    init {
        this.context = context
        this.imageFolders = imageFolders
        folderNames = ArrayList(imageFolders.keys)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFolderViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_image_folder, parent, false)
        return ImageFolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageFolderViewHolder, position: Int) {
        val folderName = folderNames[position]
        val imagePaths = imageFolders[folderName]!!
        val imagePath = imagePaths[0] // Get the first image path for thumbnail

        // Load and display the thumbnail image using a library of your choice, e.g. Glide or Picasso

        Glide.with(context)
            .load(File(imagePath))
            .into(holder.imageViewFolder)
        holder.textViewFolderName.text = folderName

        holder.textViewFolderName.setOnClickListener {

            val intent = Intent(context, folder_image::class.java)
            intent.putStringArrayListExtra("imagePaths", imagePaths)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return folderNames.size
    }

    class ImageFolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewFolder: ImageView
        var textViewFolderName: TextView

        init {
            imageViewFolder = itemView.findViewById(R.id.imageViewFolder)
            textViewFolderName = itemView.findViewById(R.id.textViewFolderName)
        }
    }
}
