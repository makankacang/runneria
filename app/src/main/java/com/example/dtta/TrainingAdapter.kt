package com.example.dtta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrainingAdapter(
    private val trainingList: List<Training>
) : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvWorkout: TextView = itemView.findViewById(R.id.tvWorkout)
        val tvDuration: TextView = itemView.findViewById(R.id.tvDuration)
        val btnStart: Button = itemView.findViewById(R.id.btnStartTraining)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_training, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val training = trainingList[position]

        holder.tvWorkout.text = training.workout
        holder.tvDuration.text = training.duration
    }

    override fun getItemCount(): Int {
        return trainingList.size
    }
}