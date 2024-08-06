package com.nizamsetiawan.app.fasttrackedu.source.remote.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
