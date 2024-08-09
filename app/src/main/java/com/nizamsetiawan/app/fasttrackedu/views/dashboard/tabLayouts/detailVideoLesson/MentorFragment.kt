package com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nizamsetiawan.app.fasttrackedu.BuildConfig
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentMentorBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse

class MentorFragment : CoreFragment<FragmentMentorBinding>() {

    private var videoLessonData: VideoLessonResponse? = null

    fun setData(data: VideoLessonResponse) {
        videoLessonData = data
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMentorBinding =
        FragmentMentorBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoLessonData?.let { data ->
            // Update UI dengan data
            binding.tvGreeting.text = data.mentor?.name
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}${data.mentor?.photo}")
                .into(binding.ivUserProfile)
            // Tambahkan kode lain sesuai kebutuhan
            binding.tvDesc.text = data.mentor?.about
        }
    }
}
