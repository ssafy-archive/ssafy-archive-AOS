package com.example.ssafy_archive.data.repository

import com.example.ssafy_archive.data.api.AuthInterceptor
import com.example.ssafy_archive.data.api.ChangePasswordRequest
import com.example.ssafy_archive.data.api.LoginRequest
import com.example.ssafy_archive.data.api.LoginResponse
import com.example.ssafy_archive.data.api.RegisterRequest
import com.example.ssafy_archive.data.api.UpdateUserInfoRequest
import com.example.ssafy_archive.data.api.UserApi
import com.example.ssafy_archive.data.api.UserDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val api: UserApi by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)   // 로그 찍기용
            .addInterceptor(AuthInterceptor())    // 토큰 자동 주입
            .build()

        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // 나중에 교체 http://10.0.2.2:8080/
            .client(client) // AuthInterceptor - 모든 요청에 자동으로 토큰이 붙도록 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    suspend fun register(request: RegisterRequest): Boolean {
        val response = api.register(request)
        return response.isSuccessful && response.body()?.code == 200
    }

    suspend fun changePassword(
        userId: Int,
        oldPassword: String,
        newPassword: String,
        accessToken: String
    ): Boolean {
        val response = api.changePassword(
            userId = userId,
            request = ChangePasswordRequest(oldPassword, newPassword),
            token = "Bearer $accessToken"
        )
        return response.isSuccessful && response.body()?.body?.success == true
    }

    suspend fun getUserInfo(accessToken: String): UserDto? {
        val response = api.getUserInfo(accessToken)
        return if (response.isSuccessful) response.body()?.body else null
    }

    suspend fun updateUserInfo(
        name: String,
        ssafyNumber: String,
        accessToken: String
    ): Boolean {
        val request = UpdateUserInfoRequest(name, ssafyNumber)
        val response = api.updateUserInfo(request, accessToken)
        return response.isSuccessful && response.body()?.body?.success == true
    }

    suspend fun login(
        id: String,
        password: String
    ): LoginResponse? {
        val request = LoginRequest(
            id = id.trim(),
            password = password.trim()
        )
        val response = api.login(request)
        return if (response.isSuccessful) response.body() else null
    }

}
