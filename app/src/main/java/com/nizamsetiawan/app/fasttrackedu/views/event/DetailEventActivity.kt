package com.nizamsetiawan.app.fasttrackedu.views.event

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nizamsetiawan.app.fasttrackedu.BuildConfig
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityDetailEventBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import com.nizamsetiawan.app.fasttrackedu.utils.toTime
import com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.DescriptionEventFragment
import com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.FragmentDetailEventAdapter
import com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.TermEventFragment
import com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.TimelineEventFragment
import com.nizamsetiawan.app.fasttrackedu.views.event.viewmodels.EventViewModel
import org.koin.android.ext.android.inject

class DetailEventActivity : CoreActivity<ActivityDetailEventBinding>() {
    private val detailEventViewModel: EventViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
        setupToolbar(binding.mToolbar)
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityDetailEventBinding =
        ActivityDetailEventBinding.inflate(layoutInflater)

    private fun setupViewPager(data: EventResponse) {
        val viewPager: ViewPager2 = binding.viewPagerdetaillesson
        val tabLayout: TabLayout = binding.tablayoutdetaillesson

        val fragmentAdapter = FragmentDetailEventAdapter(this)
        fragmentAdapter.addFragment(DescriptionEventFragment(), "Deskripsi", data)
        fragmentAdapter.addFragment(TermEventFragment(), "Syarat dan Ketentuan", data)
        fragmentAdapter.addFragment(TimelineEventFragment(), "Timeline", data)

        viewPager.adapter = fragmentAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }

    @SuppressLint("SetTextI18n")
    private fun observeData() {
        val eventId = intent.getStringExtra(POST_ID)
        eventId?.let { detailEventViewModel.getDetailEvent(it) }
        detailEventViewModel.detailEvent.observe(this) { response ->
            when (response) {
                is ResponseState.Loading -> {}
                is ResponseState.Success -> {
                    val data = response.data
                    binding.apply {
                        tvTitleName.text = data.name
                        Glide.with(root)
                            .load("${BuildConfig.BASE_URL}${data.thumbnail}")
                            .into(playerView)
                        tvTime.text = response.data.eventDate?.toTime()
                        tvJoined.text = response.data.category
                        tvAdvanced.text = response.data.audience
                        tvMaterial.text = response.data.organizer
                    }
                    setupViewPager(data) // Set up ViewPager with data
                }
                is ResponseState.Error -> {
                    // Handle error
                }
            }
        }
    }

    companion object {
        const val POST_ID = "post_id"
    }
}