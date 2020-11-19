package com.fashi.nasa_pic.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fashi.nasa_pic.R
import com.fashi.nasa_pic.data.model.NasaModelClass
import com.fashi.nasa_pic.ui.view.DetailScreen

class NasaAdapter(val context: Context, private val listRecyclerItem: List<NasaModelClass>?) :
    RecyclerView.Adapter<NasaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val images: NasaModelClass = listRecyclerItem?.get(position) as NasaModelClass
      //  val item = items.get(position)

        val position:Int = position

        Glide.with(context).load(images.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val i = Intent(context, DetailScreen::class.java)
            //pass data though intent using puExtra
            i.putExtra("title", images.title)
            i.putExtra("explanation", images.explanation)
            i.putExtra("images", images.image)
            i.putExtra("position",position)
            /*
                    With Android 9, you cannot start an activity
                     from a non-activity context unless
                     you pass the intent flag FLAG_ACTIVITY_NEW_TASK.
                     That's what the Flag was added.
                     */i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        })

    }

    override fun getItemCount(): Int {
        return listRecyclerItem!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the ImageView that will add each item to

        var imageView: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }
}