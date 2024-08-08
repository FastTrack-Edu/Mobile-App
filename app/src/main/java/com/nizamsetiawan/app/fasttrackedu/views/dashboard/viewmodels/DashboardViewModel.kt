package com.nizamsetiawan.app.fasttrackedu.views.dashboard.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nizamsetiawan.app.fasttrackedu.repository.AppRepository
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import kotlinx.coroutines.launch

class DashboardViewModel (private val dashboardRepository: AppRepository) : ViewModel() {
    private val _videoLesson = MutableLiveData<ResponseState<List<VideoLessonResponse>>>()
    val videoLesson: LiveData<ResponseState<List<VideoLessonResponse>>> by lazy { _videoLesson }

    fun getVideoLesson() {
        viewModelScope.launch {
            dashboardRepository.getVideoLessons().collect {
                _videoLesson.value = it
            }
        }
    }

}