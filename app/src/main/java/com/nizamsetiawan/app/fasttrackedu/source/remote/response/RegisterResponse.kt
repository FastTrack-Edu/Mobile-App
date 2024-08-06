package com.nizamsetiawan.app.fasttrackedu.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("user")
	val user: RegisterUser? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@SerializedName("error")
	val error: String? = null
)

data class RegisterUser(

	@field:SerializedName("member_type")
	val memberType: Any? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
