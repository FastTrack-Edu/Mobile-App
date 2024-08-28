package com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizamsetiawan.app.fasttrackedu.BuildConfig
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentTermEventBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse
import com.nizamsetiawan.app.fasttrackedu.utils.downloadPdf
import com.nizamsetiawan.app.fasttrackedu.views.event.tabLayouts.detailEvent.adapter.TermEventAdapter

class TermEventFragment : CoreFragment<FragmentTermEventBinding>() {

    private var eventData: EventResponse? = null

    fun setData(data: EventResponse) {
        eventData = data
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentTermEventBinding =
        FragmentTermEventBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpButton()
    }

    private fun setupRecyclerView() {
        eventData?.let { data ->
            val eventList = data.termConditions?.filterNotNull() ?: emptyList()
            val adapter = TermEventAdapter(eventList)
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }
    }
    private fun setUpButton() {
        binding.btnPanduan.setOnClickListener {
            eventData?.let { data ->
                val guideBookUrl = data.guideBook
                val name = data.name // Menggunakan nama event sebagai judul
                val eventId = data.id // Mengambil ID event

                if (guideBookUrl != null) {
                    val fullUrl = "${BuildConfig.BASE_URL}$guideBookUrl"
                    requireContext().downloadPdf(fullUrl, name!!)
                } else {
                    Toast.makeText(requireContext(), "Guide book tidak tersedia.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}