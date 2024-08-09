package com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse

class FragmentDetailAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()
    private val fragmentTitles = mutableListOf<String>()
    private val fragmentData = mutableListOf<VideoLessonResponse>() // Menyimpan data untuk setiap fragment

    fun addFragment(fragment: Fragment, title: String, data: VideoLessonResponse) {
        fragments.add(fragment)
        fragmentTitles.add(title)
        fragmentData.add(data)
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        val fragment = fragments[position]
        val data = fragmentData[position]
        // Set data ke fragment jika perlu
        when (fragment) {
            is DescriptionFragment -> fragment.setData(data)
            is CurriculumFragment -> fragment.setData(data)
            is MentorFragment -> fragment.setData(data)
            is ReviewFragment -> fragment.setData(data)
            // Tambahkan case lain jika perlu
        }
        return fragment
    }

    fun getPageTitle(position: Int): CharSequence = fragmentTitles[position]
}
