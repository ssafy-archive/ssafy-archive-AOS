package com.example.ssafy_archive.data.api

import retrofit2.http.*
import retrofit2.Response

// 회원가입 요청/응답 DTO
data class RegisterRequest(
    val name: String,
    val ssafyNumber: String,
    val loginId: String,
    val password: String
)

data class RegisterResponse(
    val code: Int,
    val body: UserDto
)

// 로그인 요청/응답 DTO
data class LoginRequest(
    val id: String,
    val password: String
)

data class TokenBody(
    val accessToken: String,
    val refreshToken: String
)

// 2) 최종 로그인 응답 래퍼
data class LoginResponse(
    val code: Int,
    val body: TokenBody
)

// 유저 정보 공통 DTO
data class UserDto(
    val userId: Int,
    val id: String,
    val name: String,
    val ssafyNumber: String,
    val userRole: String
)

// 비밀번호 변경 요청/응답 DTO
data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String
)

// 사용자 정보 수정 요청 DTO
data class UpdateUserInfoRequest(
    val name: String,
    val ssafyNumber: String
)

// 공통 성공 응답 DTO
data class GenericSuccessResponse(
    val code: Int,
    val body: SuccessBody
)

data class SuccessBody(
    val success: Boolean
)

// Retrofit 인터페이스
interface UserApi {

    @POST("/api/v1/user")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("/api/v1/user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("/api/v1/user")
    suspend fun getUserInfo(@Header("Authorization") token: String): Response<RegisterResponse>

    @PUT("/api/v1/user")
    suspend fun updateUserInfo(
        @Body request: UpdateUserInfoRequest,
        @Header("Authorization") token: String
    ): Response<GenericSuccessResponse>

    @PUT("/api/v1/user/{userId}/password")
    suspend fun changePassword(
        @Path("userId") userId: Int,
        @Body request: ChangePasswordRequest,
        @Header("Authorization") token: String
    ): Response<GenericSuccessResponse>
}
