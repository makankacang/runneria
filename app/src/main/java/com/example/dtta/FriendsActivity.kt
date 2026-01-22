package com.example.dtta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFriends)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Data Dummy
        val friendList = listOf(
            Friend("Gibral Haikal", "Runner"),
            Friend("Haikal Alghiffari", "Beginner"),
            Friend("Intan Nadia", "Pro Runner"),
            Friend("Matahari Dhia", "Pro Runner"),
            Friend("Reyhan Nur", "Cyclist")

        )

        recyclerView.adapter = FriendAdapter(friendList)

        return view
    }

    class FriendAdapter(private val friendList: List<Friend>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.findViewById(R.id.tvUserName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friends, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val friend = friendList[position]
            holder.tvName.text = friend.name
        }

        override fun getItemCount(): Int = friendList.size
    }
}

data class Friend(
    val name: String,
    val status: String
)