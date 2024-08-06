package com.nizamsetiawan.app.fasttrackedu.source.remote.network

import com.nizamsetiawan.app.fasttrackedu.source.remote.request.LoginRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.RegisterRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.LoginResponse
import com.nizamsetiawan.app.fasttrackedu.source.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

}