package com.fashi.nasa_pic.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.model.ImageModelClass
import com.fashi.nasa_pic.view.DetailScreen
import java.io.ByteArrayOutputStream


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
                Glide.with(context).load(images.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.image)

                viewHolder.itemView.setOnClickListener(View.OnClickListener {
                    val i = Intent(context, DetailScreen::class.java)
                    //pass data though intent using puExtra
                    i.putExtra("title", images.title)
                    i.putExtra("explanation", images.explanation)
                    i.putExtra("images", images.image)

                    /*
                            With Android 9, you cannot start an activity
                             from a non-activity context unless
                             you pass the intent flag FLAG_ACTIVITY_NEW_TASK.
                             That's what the Flag was added.
                             */i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(i)
                })
            }
            else -> {
                val itemViewHolder = viewHolder as ItemViewHolder
                val images: ImageModelClass = listRecyclerItem[i] as ImageModelClass
                //itemViewHolder.title.setText(images.name)
                Glide.with(context).load(images.image).into(itemViewHolder.image)

                viewHolder.itemView.setOnClickListener(View.OnClickListener {
                    val i = Intent(context, DetailScreen::class.java)
                    //pass data though intent using puExtra
                    i.putExtra("title", images.title)
                    i.putExtra("explanation", images.explanation)
                    i.putExtra("images", images.image)

                    /*
                            With Android 9, you cannot start an activity
                             from a non-activity context unless
                             you pass the intent flag FLAG_ACTIVITY_NEW_TASK.
                             That's what the Flag was added.
                             */i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(i)
                })
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