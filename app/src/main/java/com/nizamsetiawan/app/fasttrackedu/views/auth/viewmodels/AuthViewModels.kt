package com.nizamsetiawan.app.fasttrackedu.views.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nizamsetiawan.app.fasttrackedu.repository.AppRepository
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.LoginRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.RegisterRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.LoginResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.RegisterResponse
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import kotlinx.coroutines.launch

class AuthViewModels(private val authRepository: AppRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<ResponseState<LoginResponse>>()
    val loginResult: LiveData<ResponseState<LoginResponse>> by lazy { _loginResult }

    private val _registerResult = MutableLiveData<ResponseState<RegisterResponse>>()
    val registerResult: LiveData<ResponseState<RegisterResponse>> by lazy { _registerResult }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(LoginRequest(email, password)).collect {
                _loginResult.value = it
            }
        }
    }

    fun register(name: String ,email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            authRepository.register(RegisterRequest(name, email, password, confirmPassword)).collect {
                _registerResult.value = it
            }
        }
    }

    fun logout()= authRepository.logout()
}