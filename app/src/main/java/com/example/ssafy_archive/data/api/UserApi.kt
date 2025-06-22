package com.example.ssafy_archive.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class RegisterRequest(
    val loginId: String,
    val password: String,
    val name: String,
    val ssafyNumber: String
)

data class RegisterResponse(
    val code: Int,
    val body: UserDto
)

data class UserDto(
    val userId: Int,
    val loginId: String,
    val name: String,
    val ssafyNumber: String,
    val userRole: String
)

interface UserApi {
    @POST("/api/v1/user")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
