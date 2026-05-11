package com.example.dtta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ClubsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_club, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvClubs)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val clubList = listOf(
            Club("FREERUNNERS BANDUNG", "9,986 runners • Bandung"),
            Club("STRAVA BANDUNG", "6,782 cyclists • Bandung"),
            Club("Go Running", "4,332 runners • Bandung"),
            Club("Gajah Lulumpatan", "3,342 runners • Bandung"),
            Club("BDG Explorer", "2,943 runners • Bandung")
        )

        recyclerView.adapter = ClubAdapter(clubList)

        return view
    }
    class ClubAdapter(private val clubList: List<Club>) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.findViewById(R.id.tvClubName)
            val tvDetails: TextView = itemView.findViewById(R.id.tvClubDetails)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_clubs, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val club = clubList[position]
            holder.tvName.text = club.name
            holder.tvDetails.text = club.details
        }

        override fun getItemCount(): Int = clubList.size
    }
}

data class Club(
    val name: String,
    val details: String
)