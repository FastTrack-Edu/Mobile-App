package com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentCurriculumBinding
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.tabLayouts.detailVideoLesson.adapter.CurriculumAdapter

class CurriculumFragment : CoreFragment<FragmentCurriculumBinding>() {

    private var videoLessonData: VideoLessonResponse? = null

    fun setData(data: VideoLessonResponse) {
        videoLessonData = data
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCurriculumBinding =
        FragmentCurriculumBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CurriculumFragment", "Binding initialized: ${binding != null}")

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        videoLessonData?.let { data ->
            val curriculumList = data.curriculums?.filterNotNull() ?: emptyList()
            Log.d("CurriculumFragment", "Curriculum List Size: ${curriculumList.size}")

            val adapter = CurriculumAdapter(curriculumList)
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }
    }
}
