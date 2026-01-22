package com.example.dtta

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Progress" else "Activities"
        }.attach()

        findViewById<View>(R.id.btnHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        findViewById<View>(R.id.btnMaps).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }


        findViewById<View>(R.id.btnProgress).setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        findViewById<View>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    fun goToSettings(view: View) {
        val intent = Intent(this, Setting::class.java)
        startActivity(intent)
    }
    inner class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment {
            return if (position == 0) ProgressTabFragment() else ActivitiesTabFragment()
        }
    }
}

class ProgressTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress_tab, container, false)
    }
}
class ActivitiesTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activities_tab, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.rvActivities)
        rv.layoutManager = LinearLayoutManager(context)
        val listData = listOf(
            ActivityData(
                "Tugas Runneria 1",
                "Haikal Alghiffari Sunggoro 224443076 2 AEC 4",
                "5.00 km", "5,498", "50m 20s", "Oktober 20, 2025 at 3:22 PM",
                R.drawable._8,      // Ganti dengan nama file peta 1 Anda
                R.drawable.orang    // Ganti dengan nama file avatar Anda
            ),
            ActivityData(
                "Tugas Runneria 2",
                "Haikal Alghiffari Sunggoro 224443076 1 AEC 4",
                "3.00 km", "3,798", "36m 20s", "November 18, 2025 at 5:22 PM",
                R.drawable._9,      // Ganti dengan nama file peta 2 Anda
                R.drawable.orang
            )
        )

        rv.adapter = ActivityAdapter(listData)
        return view
    }
}

class ActivityAdapter(private val list: List<ActivityData>) : RecyclerView.Adapter<ActivityAdapter.Holder>() {

    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitle: TextView = v.findViewById(R.id.tvTitle)
        val tvDesc: TextView = v.findViewById(R.id.tvDesc)
        val tvDist: TextView = v.findViewById(R.id.tvDistance)
        val tvSteps: TextView = v.findViewById(R.id.tvSteps)
        val tvTime: TextView = v.findViewById(R.id.tvTime)
        val tvDate: TextView = v.findViewById(R.id.tvDate)
        val tvName: TextView = v.findViewById(R.id.tvUserName)
        val imgMap: android.widget.ImageView = v.findViewById(R.id.imgMap)
        val imgAvatar: android.widget.ImageView = v.findViewById(R.id.imgAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
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

    override fun getItemCount(): Int = list.size
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