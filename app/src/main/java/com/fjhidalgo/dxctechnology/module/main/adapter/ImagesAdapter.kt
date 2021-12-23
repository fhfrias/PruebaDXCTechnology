package com.fjhidalgo.dxctechnology.module.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.fjhidalgo.dxctechnology.R
import com.fjhidalgo.dxctechnology.data.network.model.ImageModel
import com.fjhidalgo.dxctechnology.module.main.view.MainActivity
import com.fjhidalgo.dxctechnology.module.main.viewHolder.ImageViewHolder

class ImagesAdapter(private val context: Context,
                    private val layoutInflater: LayoutInflater, internal var imageList: List<ImageModel>, internal var activity: AppCompatActivity
) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = layoutInflater.inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(context, imageList[position], position, activity)
        holder.itemView.setOnClickListener {

            (activity as MainActivity).showDetailItem(imageList[position])
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}