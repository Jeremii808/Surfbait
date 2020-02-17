package com.example.surfbait30.ui.honolii

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surfbait30.R
import com.example.surfbait30.TimestampConverter.getTimeFromSeconds
import com.example.surfbait30.data.Post
import kotlinx.android.synthetic.main.post_item_layout_honolii.view.*
import kotlin.text.Typography.degree

class PostItemAdapterHonolii(private val postList: List<Post>, private val context: Context) :
    RecyclerView.Adapter<ViewHolderHonolii>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHonolii {
        return ViewHolderHonolii(
            LayoutInflater.from(context).inflate(
                R.layout.post_item_layout_honolii,
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
    // view holder in layout "post_item_layout_honolii.xml"
    override fun onBindViewHolder(holder: ViewHolderHonolii, position: Int) {
        // Time of swell
        val time: String = getTimeFromSeconds(postList[position].timestamp)
        holder.itemView.time_honolii.text = time

        // Minimum and maximum height of swell
        val min: String = postList[position].swell.minBreakingHeight.toString()
        val max: String = postList[position].swell.maxBreakingHeight.toString()
        val swell: String = "$min-$max" + "FT"
        holder.itemView.swell_honolii.text = swell

        // Weather temperature
        val temperature: String = postList[position].condition.temperature.toString() + degree
        holder.itemView.temp_honolii.text = temperature

        // Wind speed in miles per hour
        val wind: String = postList[position].wind.speed.toString()
        holder.itemView.wind_honolii.text = wind

        // Compass direction of incoming wind
        val compass: String = postList[position].wind.compassDirection
        holder.itemView.compass_honolii.text = compass
    }
}

class ViewHolderHonolii(view: View) : RecyclerView.ViewHolder(view)