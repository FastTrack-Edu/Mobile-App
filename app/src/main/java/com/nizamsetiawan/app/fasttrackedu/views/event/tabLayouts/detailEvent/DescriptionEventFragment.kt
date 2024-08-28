package com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentDescriptionBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse


class DescriptionEventFragment : CoreFragment<FragmentDescriptionBinding>() {

    private var eventData: EventResponse? = null

    fun setData(data: EventResponse) {
        eventData = data
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDescriptionBinding =
        FragmentDescriptionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventData?.let { data ->
            binding.apply {
                desc.text = data.description
            }
        }
    }
}