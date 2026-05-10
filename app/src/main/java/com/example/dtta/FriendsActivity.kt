package com.example.dtta

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var friendAdapter: FriendAdapter
    private lateinit var clubAdapter: ClubAdapter
    private var activeMenu = Menu.FRIENDS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        val friendList = listOf(
            Friend("Gibral Haikal", "Runner", "128", "934", "May 10, 2026", "3 saved routes", "5 posts", "42.5 km this month", "Nike Pegasus"),
            Friend("Haikal Alghiffari", "Beginner", "87", "421", "May 8, 2026", "1 saved route", "2 posts", "12.8 km this month", "Adidas Runfalcon"),
            Friend("Intan Nadia", "Pro Runner", "302", "1.2 M", "January 21, 2026", "18 saved routes", "24 posts", "168.4 km this month", "Asics Gel-Kayano"),
            Friend("Matahari Dhia", "Pro Runner", "245", "890 K", "April 28, 2026", "14 saved routes", "19 posts", "132.7 km this month", "Hoka Clifton"),
            Friend("Reyhan Nur", "Cyclist", "176", "12.4 K", "May 2, 2026", "9 cycling routes", "8 posts", "214.9 km this month", "Polygon Strattos")

        )

        val clubList = listOf(
            Club("FREERUNNERS BANDUNG", "9,986 runners - Bandung", "9.9 K", "24.8 K KM", "128 activities this week", "32 public routes", "86 posts", "Top runner: Intan", "Sunday long run"),
            Club("STRAVA BANDUNG", "6,782 cyclists - Bandung", "6.7 K", "31.2 K KM", "94 rides this week", "45 cycling routes", "71 posts", "Top cyclist: Reyhan", "Cikole climb"),
            Club("Go Running", "4,332 runners - Bandung", "4.3 K", "12.6 K KM", "67 activities this week", "18 public routes", "44 posts", "Top runner: Gibral", "Beginner 5K"),
            Club("Gajah Lulumpatan", "3,342 runners - Bandung", "3.3 K", "9.4 K KM", "52 activities this week", "12 public routes", "31 posts", "Top runner: Haikal", "Campus run"),
            Club("BDG Explorer", "2,943 runners - Bandung", "2.9 K", "7.8 K KM", "39 activities this week", "21 trail routes", "28 posts", "Top explorer: Matahari", "Tahura trail")
        )

        recyclerView = findViewById(R.id.rvFriends)
        searchView = findViewById(R.id.searchFriends)
        friendAdapter = FriendAdapter(friendList) { friend ->
            val intent = Intent(this, FriendDetailActivity::class.java)
            intent.putExtra(FriendDetailActivity.EXTRA_NAME, friend.name)
            intent.putExtra(FriendDetailActivity.EXTRA_STATUS, friend.status)
            intent.putExtra(FriendDetailActivity.EXTRA_FOLLOWING, friend.following)
            intent.putExtra(FriendDetailActivity.EXTRA_FOLLOWERS, friend.followers)
            intent.putExtra(FriendDetailActivity.EXTRA_ACTIVITY_INFO, friend.activityInfo)
            intent.putExtra(FriendDetailActivity.EXTRA_ROUTES_INFO, friend.routesInfo)
            intent.putExtra(FriendDetailActivity.EXTRA_POSTS_INFO, friend.postsInfo)
            intent.putExtra(FriendDetailActivity.EXTRA_STATISTICS_INFO, friend.statisticsInfo)
            intent.putExtra(FriendDetailActivity.EXTRA_GEAR_INFO, friend.gearInfo)
            startActivity(intent)
        }
        clubAdapter = ClubAdapter(clubList) { club ->
            val intent = Intent(this, ClubDetailActivity::class.java)
            intent.putExtra(ClubDetailActivity.EXTRA_NAME, club.name)
            intent.putExtra(ClubDetailActivity.EXTRA_DETAILS, club.details)
            intent.putExtra(ClubDetailActivity.EXTRA_MEMBERS, club.members)
            intent.putExtra(ClubDetailActivity.EXTRA_WEEKLY_DISTANCE, club.weeklyDistance)
            intent.putExtra(ClubDetailActivity.EXTRA_ACTIVITY_INFO, club.activityInfo)
            intent.putExtra(ClubDetailActivity.EXTRA_ROUTES_INFO, club.routesInfo)
            intent.putExtra(ClubDetailActivity.EXTRA_POSTS_INFO, club.postsInfo)
            intent.putExtra(ClubDetailActivity.EXTRA_LEADERBOARD_INFO, club.leaderboardInfo)
            intent.putExtra(ClubDetailActivity.EXTRA_EVENTS_INFO, club.eventsInfo)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = friendAdapter

        findViewById<View>(R.id.btnUser).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<View>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }

        findViewById<View>(R.id.btnSearch).setOnClickListener {
            searchView.requestFocus()
        }

        findViewById<View>(R.id.tabFriends).setOnClickListener {
            showFriends()
        }

        findViewById<View>(R.id.tabClubs).setOnClickListener {
            showClubs()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterActiveList(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterActiveList(newText.orEmpty())
                return true
            }
        })
    }

    private fun showFriends() {
        activeMenu = Menu.FRIENDS
        recyclerView.adapter = friendAdapter
        searchView.queryHint = "Cari nama teman..."
        searchView.setQuery("", false)
        updateTabs()
    }

    private fun showClubs() {
        activeMenu = Menu.CLUBS
        recyclerView.adapter = clubAdapter
        searchView.queryHint = "Cari club..."
        searchView.setQuery("", false)
        updateTabs()
    }

    private fun filterActiveList(keyword: String) {
        if (activeMenu == Menu.FRIENDS) {
            friendAdapter.filter(keyword)
        } else {
            clubAdapter.filter(keyword)
        }
    }

    private fun updateTabs() {
        val activeColor = Color.parseColor("#A80038")
        val inactiveColor = Color.parseColor("#777777")
        val friendsActive = activeMenu == Menu.FRIENDS

        findViewById<TextView>(R.id.tvFriendsTab).setTextColor(if (friendsActive) activeColor else inactiveColor)
        findViewById<TextView>(R.id.tvFriendsTab).typeface = if (friendsActive) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        findViewById<TextView>(R.id.tvClubsTab).setTextColor(if (friendsActive) inactiveColor else activeColor)
        findViewById<TextView>(R.id.tvClubsTab).typeface = if (friendsActive) Typeface.DEFAULT else Typeface.DEFAULT_BOLD
        findViewById<View>(R.id.indicatorFriends).setBackgroundColor(if (friendsActive) activeColor else Color.TRANSPARENT)
        findViewById<View>(R.id.indicatorClubs).setBackgroundColor(if (friendsActive) Color.TRANSPARENT else activeColor)
    }

    private enum class Menu {
        FRIENDS,
        CLUBS
    }

    class FriendAdapter(
        private val friendList: List<Friend>,
        private val onItemClick: (Friend) -> Unit
    ) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
        private val filteredFriends = friendList.toMutableList()

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.findViewById(R.id.tvUserName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friends, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val friend = filteredFriends[position]
            holder.tvName.text = friend.name
            holder.itemView.setOnClickListener {
                onItemClick(friend)
            }
        }

        override fun getItemCount(): Int = filteredFriends.size

        fun filter(keyword: String) {
            filteredFriends.clear()
            filteredFriends.addAll(
                if (keyword.isBlank()) {
                    friendList
                } else {
                    friendList.filter { friend ->
                        friend.name.contains(keyword, ignoreCase = true) ||
                            friend.status.contains(keyword, ignoreCase = true)
                    }
                }
            )
            notifyDataSetChanged()
        }
    }

    class ClubAdapter(
        private val clubList: List<Club>,
        private val onItemClick: (Club) -> Unit
    ) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {
        private val filteredClubs = clubList.toMutableList()

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.findViewById(R.id.tvClubName)
            val tvDetails: TextView = itemView.findViewById(R.id.tvClubDetails)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_clubs, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val club = filteredClubs[position]
            holder.tvName.text = club.name
            holder.tvDetails.text = club.details
            holder.itemView.setOnClickListener {
                onItemClick(club)
            }
        }

        override fun getItemCount(): Int = filteredClubs.size

        fun filter(keyword: String) {
            filteredClubs.clear()
            filteredClubs.addAll(
                if (keyword.isBlank()) {
                    clubList
                } else {
                    clubList.filter { club ->
                        club.name.contains(keyword, ignoreCase = true) ||
                            club.details.contains(keyword, ignoreCase = true)
                    }
                }
            )
            notifyDataSetChanged()
        }
    }
}

data class Friend(
    val name: String,
    val status: String,
    val following: String,
    val followers: String,
    val activityInfo: String,
    val routesInfo: String,
    val postsInfo: String,
    val statisticsInfo: String,
    val gearInfo: String
)

data class Club(
    val name: String,
    val details: String,
    val members: String,
    val weeklyDistance: String,
    val activityInfo: String,
    val routesInfo: String,
    val postsInfo: String,
    val leaderboardInfo: String,
    val eventsInfo: String
)
