package com.fashi.nasa_pic.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.data.model.NasaModelClass

class DetailViewPagerAdapter(
    val context: Context,
    private val imagesList: List<NasaModelClass>?
) : RecyclerView.Adapter<DetailViewPagerAdapter.DetailViewHolder>() {

    class DetailViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val imageBanner: ImageView = itemView.findViewById(R.id.detail_image)
        private val title: TextView = itemView.findViewById(R.id.detail_title)
        private val explanation: TextView = itemView.findViewById(R.id.detail_explanation)

        fun bind(
            context: Context,
            nasaData: NasaModelClass
        ) {
            title.text = nasaData.title
            explanation.text = nasaData.explanation
            Glide.with(context).load(nasaData.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_detail_adapter, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val sportsArgs: NasaModelClass = imagesList!![position] as NasaModelClass
        holder.bind(context,sportsArgs)

    }

    override fun getItemCount() = imagesList!!.size
}