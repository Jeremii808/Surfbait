package com.example.surfbait30.ui.hapuna

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surfbait30.R
import com.example.surfbait30.TimestampConverter.getTimeFromSeconds
import com.example.surfbait30.data.Post
import kotlinx.android.synthetic.main.post_item_layout_hapuna.view.*
import kotlin.text.Typography.degree

class PostItemAdapterHapuna(private val postList: List<Post>, private val context: Context) :
    RecyclerView.Adapter<ViewHolderHapuna>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHapuna {
        return ViewHolderHapuna(
            LayoutInflater.from(context).inflate(
                R.layout.post_item_layout_hapuna,
                parent,
                false
            )
        )
    }

    // The first eight items in postList contain the forecast for today
    override fun getItemCount(): Int {
        return 8
    }

    // Data from API GET request is parsed into the respective
    // view holder in layout "post_item_layout_hapuna.xml"
    override fun onBindViewHolder(holder: ViewHolderHapuna, position: Int) {
        // Time of swell
        val time: String = getTimeFromSeconds(postList[position].timestamp)
        holder.itemView.time_hapuna.text = time

        // Minimum and maximum height of swell
        val min: String = postList[position].swell.minBreakingHeight.toString()
        val max: String = postList[position].swell.maxBreakingHeight.toString()
        val swell: String = "$min-$max" + "FT"
        holder.itemView.swell_hapuna.text = swell

        // Weather temperature
        val temperature: String = postList[position].condition.temperature.toString() + degree
        holder.itemView.temp_hapuna.text = temperature

        // Wind speed in miles per hour
        val speed: String = postList[position].wind.speed.toString()
        holder.itemView.wind_hapuna.text = speed

        // Compass direction of incoming wind
        val compass: String = postList[position].wind.compassDirection
        holder.itemView.compass_hapuna.text = compass
    }
}

class ViewHolderHapuna(view: View) : RecyclerView.ViewHolder(view)