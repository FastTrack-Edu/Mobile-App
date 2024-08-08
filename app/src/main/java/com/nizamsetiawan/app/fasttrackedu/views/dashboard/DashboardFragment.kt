package com.nizamsetiawan.app.fasttrackedu.views.dashboard

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
import org.koin.android.ext.android.inject

class DashboardFragment : CoreFragment<FragmentDashboardBinding>(),
    VideoLessonAdapter.OnVideoLessonClickListener {

    private val dashboardViewModel: DashboardViewModel by inject()
    private val videoLessonAdapter = VideoLessonAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Koin modules if needed here
        KoinModules.reloadModule()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
        dashboardViewModel.getVideoLesson()
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
            cardVideoLesson.setOnClickListener { }
            cardBlog.setOnClickListener { }
        }
    }
    private fun setupRecyclerView() {
        binding.rvVideoLesson.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvVideoLesson.adapter = videoLessonAdapter

    }

    private fun setupObservers() {
        binding.apply {
            dashboardViewModel.videoLesson.observe(viewLifecycleOwner){ state ->
                when(state){
                    is ResponseState.Loading ->{

                    }
                    is ResponseState.Success ->{
                        videoLessonAdapter.updateItems(state.data)
                    }
                    is ResponseState.Error -> {
                    }
                }

            }
        }
    }
}
