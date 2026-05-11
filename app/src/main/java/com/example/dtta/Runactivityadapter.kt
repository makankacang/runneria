package com.example.dtta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RunActivityAdapter(
    private val activities: List<RunActivity>
) : RecyclerView.Adapter<RunActivityAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvActivityTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvActivityDate)
        val tvDistance: TextView = itemView.findViewById(R.id.tvActivityDistance)
        val tvDuration: TextView = itemView.findViewById(R.id.tvActivityDuration)
        val tvPace: TextView = itemView.findViewById(R.id.tvActivityPace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_run_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activities[position]
        holder.tvTitle.text = activity.title
        holder.tvDate.text = activity.date
        holder.tvDistance.text = activity.distance
        holder.tvDuration.text = activity.duration
        holder.tvPace.text = activity.pace
    }

    override fun getItemCount(): Int = activities.size
}