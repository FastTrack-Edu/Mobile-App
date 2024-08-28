package com.nizamsetiawan.app.fasttrackedu.views.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentDashboardBinding
import com.nizamsetiawan.app.fasttrackedu.di.modules.KoinModules
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.adapters.VideoLessonAdapter
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.viewmodels.DashboardViewModel
import com.nizamsetiawan.app.fasttrackedu.views.event.adapters.EventAdapter
import com.nizamsetiawan.app.fasttrackedu.views.event.viewmodels.EventViewModel
import org.koin.android.ext.android.inject

class DashboardFragment : CoreFragment<FragmentDashboardBinding>(),
    VideoLessonAdapter.OnVideoLessonClickListener {

    private val dashboardViewModel: DashboardViewModel by inject()
    private val eventViewModel: EventViewModel by inject()
    private val videoLessonAdapter = VideoLessonAdapter(this)
    private val eventAdapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        KoinModules.reloadModule()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.getVideoLesson()
        eventViewModel.getAllEvent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup views or observe LiveData here
        setupButton()
        setupRecyclerView()
        setupObservers()
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onVideoLessonButtonClicked(item: VideoLessonResponse) {
        // Handle video lesson button click event
    }

    private fun setupButton() {
        binding.apply {
            cardMentoring.setOnClickListener {}
            cardCourse.setOnClickListener { }
            cardVideoLesson.setOnClickListener {
                startActivity(Intent(requireContext(), VideoLessonActivity::class.java))
            }
            cardBlog.setOnClickListener { }
            viewMoreVideoLesson.setOnClickListener {
                startActivity(Intent(requireContext(), VideoLessonActivity::class.java))
            }
            viewMoreEventPopuler.setOnClickListener {
                startActivity(Intent(requireContext(), DetailVideoLessonActivity::class.java))
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvVideoLesson.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvVideoLesson.adapter = videoLessonAdapter
        binding.rvEventPopuler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvEventPopuler.adapter = eventAdapter

    }

    private fun setupObservers() {
        binding.apply {
            dashboardViewModel.videoLesson.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ResponseState.Loading -> {

                    }

                    is ResponseState.Success -> {
                        videoLessonAdapter.updateItems(state.data)
                    }

                    is ResponseState.Error -> {
                    }
                }

            }
        }
        binding.apply {
            eventViewModel.allEvent.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ResponseState.Loading -> {

                    }

                    is ResponseState.Success -> {
                        eventAdapter.updateItems(state.data)
                    }

                    is ResponseState.Error -> {}

                }
            }

        }
    }

}
