package com.nizamsetiawan.app.fasttrackedu.views.event.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nizamsetiawan.app.fasttrackedu.repository.AppRepository
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import kotlinx.coroutines.launch

class EventViewModel (private val eventRepository: AppRepository) : ViewModel() {
    private val _allEvent = MutableLiveData<ResponseState<List<EventResponse>>>()
    val allEvent: LiveData<ResponseState<List<EventResponse>>> by lazy { _allEvent }

    private val _detailEvent = MutableLiveData<ResponseState<EventResponse>>()
    val detailEvent: LiveData<ResponseState<EventResponse>> by lazy { _detailEvent }

    fun getAllEvent() {
        viewModelScope.launch {
            eventRepository.getEvent().collect {
                _allEvent.value = it
            }
        }
    }

    fun getDetailEvent(eventId: String) {
        viewModelScope.launch {
            eventRepository.getDetailEvent(eventId).collect {
                _detailEvent.value = it
            }
        }
    }


}