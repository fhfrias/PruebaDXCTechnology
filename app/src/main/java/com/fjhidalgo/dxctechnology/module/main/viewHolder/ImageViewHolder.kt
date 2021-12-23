package com.fjhidalgo.dxctechnology.module.main.viewHolder

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fjhidalgo.dxctechnology.R
import com.fjhidalgo.dxctechnology.data.network.model.ImageModel
import com.google.android.material.imageview.ShapeableImageView
import java.lang.Exception
import java.text.DecimalFormat


class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tv_title: TextView by lazy {
        itemView.findViewById<TextView>(R.id.tv_title)
    }

    private val tv_author: TextView by lazy {
        itemView.findViewById<TextView>(R.id.tv_author)
    }

    private val image: ShapeableImageView by lazy {
        itemView.findViewById<ShapeableImageView>(R.id.image)
    }

    fun bind(context: Context, item: ImageModel, position: Int, activity: AppCompatActivity) {

        tv_title.setText(item.title)
        tv_author.setText(item.author)

        try {

            Glide.with(activity).load(item.media?.get("m").toString().replace("\"", "")).centerCrop().into(image)
        } catch (e: Exception) {

        }

    }
}