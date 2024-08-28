package com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse

class FragmentDetailEventAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()
    private val fragmentTitles = mutableListOf<String>()
    private val fragmentData = mutableListOf<EventResponse>()

    fun addFragment(fragment: Fragment, title: String, data: EventResponse) {
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
            is DescriptionEventFragment -> fragment.setData(data)
            is TermEventFragment -> fragment.setData(data)
            is TimelineEventFragment -> fragment.setData(data)
        }
        return fragment
    }

    fun getPageTitle(position: Int): CharSequence = fragmentTitles[position]
}