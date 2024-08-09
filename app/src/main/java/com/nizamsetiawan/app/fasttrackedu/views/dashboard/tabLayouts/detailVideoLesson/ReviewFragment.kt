package com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentReviewBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse

class ReviewFragment : CoreFragment<FragmentReviewBinding>() {

    private var videoLessonData: VideoLessonResponse? = null

    fun setData(data: VideoLessonResponse) {
        videoLessonData = data
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentReviewBinding =
        FragmentReviewBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoLessonData?.let { data ->

        }
    }
}
