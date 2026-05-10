package com.example.dtta

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProgressActivity : AppCompatActivity() {
    private lateinit var progressContent: View
    private lateinit var activitiesContent: View
    private var activeTab = Tab.PROGRESS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        progressContent = findViewById(R.id.progressContent)
        activitiesContent = findViewById(R.id.activitiesContent)

        findViewById<View>(R.id.btnUser).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<View>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }

        findViewById<View>(R.id.tabProgress).setOnClickListener {
            showProgress()
        }

        findViewById<View>(R.id.tabActivities).setOnClickListener {
            showActivities()
        }

        val listData = listOf(
            ActivityData(
                "Tugas Runneria 1",
                "Haikal Alghiffari Sunggoro 224443076 2 AEC 4",
                "5.00 km", "5,498", "50m 20s", "Oktober 20, 2025 at 3:22 PM",
                R.drawable._8,
                R.drawable.orang
            ),
            ActivityData(
                "Tugas Runneria 2",
                "Haikal Alghiffari Sunggoro 224443076 1 AEC 4",
                "3.00 km", "3,798", "36m 20s", "November 18, 2025 at 5:22 PM",
                R.drawable._9,
                R.drawable.orang
            )
        )

        val activityAdapter = ActivityAdapter(listData)

        findViewById<RecyclerView>(R.id.rvActivities).apply {
            layoutManager = LinearLayoutManager(this@ProgressActivity)
            adapter = activityAdapter
        }

        findViewById<SearchView>(R.id.searchActivities).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                activityAdapter.filter(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                activityAdapter.filter(newText.orEmpty())
                return true
            }
        })

        if (intent.getStringExtra(EXTRA_INITIAL_TAB) == TAB_ACTIVITIES) {
            showActivities()
        } else {
            showProgress()
        }
    }

    private fun showProgress() {
        activeTab = Tab.PROGRESS
        progressContent.visibility = View.VISIBLE
        activitiesContent.visibility = View.GONE
        updateTabs()
    }

    private fun showActivities() {
        activeTab = Tab.ACTIVITIES
        progressContent.visibility = View.GONE
        activitiesContent.visibility = View.VISIBLE
        updateTabs()
    }

    private fun updateTabs() {
        val activeColor = Color.parseColor("#A80038")
        val inactiveColor = Color.parseColor("#777777")
        val progressActive = activeTab == Tab.PROGRESS

        findViewById<TextView>(R.id.tvProgressTab).setTextColor(if (progressActive) activeColor else inactiveColor)
        findViewById<TextView>(R.id.tvProgressTab).typeface = if (progressActive) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        findViewById<TextView>(R.id.tvActivitiesTab).setTextColor(if (progressActive) inactiveColor else activeColor)
        findViewById<TextView>(R.id.tvActivitiesTab).typeface = if (progressActive) Typeface.DEFAULT else Typeface.DEFAULT_BOLD
        findViewById<View>(R.id.indicatorProgress).setBackgroundColor(if (progressActive) activeColor else Color.TRANSPARENT)
        findViewById<View>(R.id.indicatorActivities).setBackgroundColor(if (progressActive) Color.TRANSPARENT else activeColor)
    }

    private enum class Tab {
        PROGRESS,
        ACTIVITIES
    }

    companion object {
        const val EXTRA_INITIAL_TAB = "initial_tab"
        const val TAB_ACTIVITIES = "activities"
    }
}

class ActivityAdapter(private val list: List<ActivityData>) : RecyclerView.Adapter<ActivityAdapter.Holder>() {
    private val filteredList = list.toMutableList()

    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitle: TextView = v.findViewById(R.id.tvTitle)
        val tvDesc: TextView = v.findViewById(R.id.tvDesc)
        val tvDist: TextView = v.findViewById(R.id.tvDistance)
        val tvSteps: TextView = v.findViewById(R.id.tvSteps)
        val tvTime: TextView = v.findViewById(R.id.tvTime)
        val tvDate: TextView = v.findViewById(R.id.tvDate)
        val tvName: TextView = v.findViewById(R.id.tvUserName)
        val imgMap: ImageView = v.findViewById(R.id.imgMap)
        val imgAvatar: ImageView = v.findViewById(R.id.imgAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = filteredList[position]
        holder.tvTitle.text = item.title
        holder.tvDesc.text = item.desc
        holder.tvDist.text = item.distance
        holder.tvSteps.text = item.steps
        holder.tvTime.text = item.time
        holder.tvDate.text = item.date
        holder.tvName.text = "Haikal Alghiffari"
        holder.imgMap.setImageResource(item.mapImageRes)
        holder.imgAvatar.setImageResource(item.avatarImageRes)
    }

    override fun getItemCount(): Int = filteredList.size

    fun filter(keyword: String) {
        filteredList.clear()
        filteredList.addAll(
            if (keyword.isBlank()) {
                list
            } else {
                list.filter { item ->
                    item.title.contains(keyword, ignoreCase = true) ||
                        item.desc.contains(keyword, ignoreCase = true) ||
                        item.distance.contains(keyword, ignoreCase = true) ||
                        item.date.contains(keyword, ignoreCase = true)
                }
            }
        )
        notifyDataSetChanged()
    }
}

data class ActivityData(
    val title: String,
    val desc: String,
    val distance: String,
    val steps: String,
    val time: String,
    val date: String,
    val mapImageRes: Int,
    val avatarImageRes: Int
)
