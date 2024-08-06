package com.nizamsetiawan.app.fasttrackedu.source.remote


import com.nizamsetiawan.app.fasttrackedu.source.remote.network.ApiService
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.LoginRequest
import com.nizamsetiawan.app.fasttrackedu.source.remote.request.RegisterRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(
        loginRequest: LoginRequest,
    ) = api.login(loginRequest)

    suspend fun register(
        registerRequest: RegisterRequest,
    ) = api.register(registerRequest)


}