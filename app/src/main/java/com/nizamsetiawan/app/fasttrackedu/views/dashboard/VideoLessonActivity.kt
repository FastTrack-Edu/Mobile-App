package com.nizamsetiawan.app.fasttrackedu.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityVideoLessonBinding
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.videoLesson.BlankFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.videoLesson.FragmentAdapter
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.videoLesson.GenericFragment

class VideoLessonActivity : CoreActivity<ActivityVideoLessonBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()

    }
    override fun setupBinding(layoutInflater: LayoutInflater): ActivityVideoLessonBinding =
        ActivityVideoLessonBinding.inflate(layoutInflater)

    private fun setupViewPager() {
        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tablayout

        val fragmentAdapter = FragmentAdapter(this)
        fragmentAdapter.addFragment(GenericFragment(), "Semua")
        fragmentAdapter.addFragment(BlankFragment(), "Research")
        fragmentAdapter.addFragment(BlankFragment(), "Science")
        fragmentAdapter.addFragment(BlankFragment(), "Puspernas")
        fragmentAdapter.addFragment(BlankFragment(), "Disertasi")


        viewPager.adapter = fragmentAdapter

        // Setup TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }
}