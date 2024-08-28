package com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentTimelineEventBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse
import com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.adapter.TimelineEventAdapter


class TimelineEventFragment  : CoreFragment<FragmentTimelineEventBinding>() {

    private var eventData: EventResponse? = null

    fun setData(data: EventResponse) {
        eventData = data
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentTimelineEventBinding =
        FragmentTimelineEventBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        eventData?.let { data ->
            val timelineList = data.timelines?.filterNotNull() ?: emptyList()
            val adapter = TimelineEventAdapter(timelineList)
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }
    }
}