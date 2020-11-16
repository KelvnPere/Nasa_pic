package com.fashi.nasa_pic.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.model.ImageModelClass


class ImageAdapter(
    private val context: Context,
    private val listRecyclerItem: List<Any>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val title: TextView = itemView.findViewById<View>(R.id.title1) as TextView
        var image: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return when (i) {
            TYPE -> {
                val layoutView: View = LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.item_list_rv, viewGroup, false
                )
                ItemViewHolder(layoutView)
            }
            else -> {
                val layoutView: View = LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.item_list_rv, viewGroup, false
                )
                ItemViewHolder(layoutView)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val viewType = getItemViewType(i)
        when (viewType) {
            TYPE -> {
                val itemViewHolder = viewHolder as ItemViewHolder
                val images: ImageModelClass = listRecyclerItem[i] as ImageModelClass
                ///itemViewHolder.title.setText(images.name)
                Glide.with(context).load(images.image).into(itemViewHolder.image)
            }
            else -> {
                val itemViewHolder = viewHolder as ItemViewHolder
                val images: ImageModelClass = listRecyclerItem[i] as ImageModelClass
                //itemViewHolder.title.setText(images.name)
                Glide.with(context).load(images.image).into(itemViewHolder.image)
            }
        }
    }

    override fun getItemCount(): Int {
        return listRecyclerItem.size
    }

    companion object {
        private const val TYPE = 1
    }
}