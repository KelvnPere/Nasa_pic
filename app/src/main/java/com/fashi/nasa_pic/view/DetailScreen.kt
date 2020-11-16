package com.fashi.nasa_pic.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fashi.nasa_pic.R
import kotlinx.android.synthetic.main.activity_detail_screen.*

class DetailScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        //val url : String = ""
        val title: String? = intent.getStringExtra("title")
        val explanation: String? = intent.getStringExtra("explanation")
        val url: String? = intent.getStringExtra("images")

        detail_title.text = title
        detail_explanation.text = explanation
        Glide.with(this).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(detail_image)
    }

}