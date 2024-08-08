package com.nizamsetiawan.app.fasttrackedu.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityDetailVideoLessonBinding
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.CurriculumFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.DescriptionFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.FragmentDetailAdapter
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.MentorFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.ReviewFragment
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.videoLesson.BlankFragment

class DetailVideoLessonActivity : CoreActivity<ActivityDetailVideoLessonBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityDetailVideoLessonBinding =
        ActivityDetailVideoLessonBinding.inflate(layoutInflater)

    private fun setupViewPager() {
        val viewPager: ViewPager2 = binding.viewPagerdetaillesson
        val tabLayout: TabLayout = binding.tablayoutdetaillesson

        val fragmentAdapter = FragmentDetailAdapter(this)
        fragmentAdapter.addFragment(DescriptionFragment(), "Deskripsi")
        fragmentAdapter.addFragment(CurriculumFragment(), "Kurikulum")
        fragmentAdapter.addFragment(MentorFragment(), "Mentor")
        fragmentAdapter.addFragment(ReviewFragment(), "Review")
        fragmentAdapter.addFragment(BlankFragment(), "Kursus Serupa")

        viewPager.adapter = fragmentAdapter

        // Setup TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }
}
