package com.example.mygallery

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView




class folder_image : AppCompatActivity() {
    private var imagePaths: ArrayList<String>? = null



    lateinit var recycleriew: RecyclerView
    private var adapter: ImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folder_image)

        imagePaths = getIntent().getStringArrayListExtra("imagePaths");

        recycleriew = findViewById(R.id.rece)
        adapter = imagePaths?.let { ImageAdapter(this, imagePaths as java.util.ArrayList<String>) }

        recycleriew.setLayoutManager(
            GridLayoutManager(
                this,
                3
            )
        ) // You can customize the layout manager as per your requirement

        recycleriew.setAdapter(adapter)


    }
}