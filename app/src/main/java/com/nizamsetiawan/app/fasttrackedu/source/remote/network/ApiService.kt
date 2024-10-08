package com.nizamsetiawan.app.fasttrackedu.source.remote.network

import com.nizamsetiawan.app.fasttrackedu.source.remote.request.LoginRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.RegisterRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.EventResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.LoginResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.RegisterResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.VideoLessonResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    //Video Lesson
    @GET("video-lesson")
    suspend fun getVideoLessons(): List<VideoLessonResponse>

    //Detail Video Lesson
    @GET("video-lesson/{lessonId}")
    suspend fun getVideoLessonById(@Path("lessonId") id: String): VideoLessonResponse

    //Event
    @GET("event")
    suspend fun getEvent() : List<EventResponse>

    @GET("event/{eventId}")
    suspend fun getEventById(@Path("eventId") id: String) : EventResponse



}