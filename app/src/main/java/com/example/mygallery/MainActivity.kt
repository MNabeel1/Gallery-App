package com.example.mygallery

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mygallery.R.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)


        val projection =
            arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " DESC"

        val cursor: Cursor? = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )

        val imageFolders: HashMap<String, ArrayList<String>> = HashMap()

        if (cursor != null) {
            val columnIndexData: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val columnIndexFolder: Int =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val imagePath: String = cursor.getString(columnIndexData)
                val folderName: String = cursor.getString(columnIndexFolder)
                if (!imageFolders.containsKey(folderName)) {
                    imageFolders[folderName] = ArrayList()
                }

                imageFolders[folderName]!!.add(imagePath)


            }
            cursor.close()

        }
     //Log.d("new","gallery"+imageFolders)

        val recyclerView = findViewById<RecyclerView>(id.rec)
        val adapter = ImageFolderAdapter(this, imageFolders)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}