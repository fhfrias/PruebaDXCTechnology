package com.fjhidalgo.dxctechnology.module.main.view

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fjhidalgo.dxctechnology.data.network.model.ImageModel
import com.fjhidalgo.dxctechnology.databinding.DetailActivityBinding
import java.io.Serializable

class DetailActivity: AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvTitle.setText(intent.getStringExtra("title"))
        binding.tvAuthor.setText(intent.getStringExtra("author"))
        binding.tvDate.setText(intent.getStringExtra("published"))
        binding.tvDescr.setText(Html.fromHtml(intent.getStringExtra("description")))

        Glide.with(this).load(intent.getStringExtra("urlImage")).centerCrop().into(binding.image)

        binding.btnBack.setOnClickListener {

            finish()
        }
    }

}