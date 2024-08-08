package com.nizamsetiawan.app.fasttrackedu.repository

import com.google.gson.Gson
import com.nizamsetiawan.app.fasttrackedu.di.modules.KoinModules
import com.nizamsetiawan.app.fasttrackedu.source.local.LocalDataSource
import com.nizamsetiawan.app.fasttrackedu.source.remote.RemoteDataSource
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.LoginRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.RegisterRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.LoginResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.RegisterResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import com.nizamsetiawan.app.fasttrackedu.utils.Prefs
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


class AppRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    fun login(loginDto: LoginRequest): Flow<ResponseState<LoginResponse>> = flow {
        try {
            emit(ResponseState.Loading)
            val response = remoteDataSource.login(loginDto)

            if (response.error != null) {
                emit(ResponseState.Error(response.error))
            } else {
                val loginResult = response.user
                if (loginResult != null) {
                    Prefs.setLoginPrefs(loginResult, response.token ?: "")

                    KoinModules.reloadModule()
                }
                emit(ResponseState.Success(response))
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = if (!errorBody.isNullOrEmpty()) {
                val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
                errorResponse.error ?: e.message() ?: "Unknown error"
            } else {
                e.message() ?: "Unknown error"
            }
            emit(ResponseState.Error(errorMessage))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseState.Error(e.message ?: "Unknown error"))
        }
    }


    fun register(registerDTO: RegisterRequest): Flow<ResponseState<RegisterResponse>> = flow {
        try {
            emit(ResponseState.Loading)
            val response = remoteDataSource.register(registerDTO)

            if (response.error != null) {
                emit(ResponseState.Error(response.error))
            } else {
                emit(ResponseState.Success(response))
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = if (!errorBody.isNullOrEmpty()) {
                val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                errorResponse.error ?: e.message() ?: "Unknown error"
            } else {
                e.message() ?: "Unknown error"
            }
            emit(ResponseState.Error(errorMessage))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseState.Error(e.message ?: "Unknown error"))
        }
    }

    fun logout(): Boolean {
        return try {
            Prefs.fastTrackAuthPrefs()
            KoinModules.reloadModule()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    //Video Lesson
    fun getVideoLessons(): Flow<ResponseState<List<VideoLessonResponse>>> = flow {
        try {
            emit(ResponseState.Loading)
            val response = remoteDataSource.videoLessons()
            emit(ResponseState.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseState.Error(e.message.toString()))
        }
    }

}