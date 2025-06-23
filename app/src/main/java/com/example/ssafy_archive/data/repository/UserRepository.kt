package com.example.ssafy_archive.data.repository

import com.example.ssafy_archive.data.api.ChangePasswordRequest
import com.example.ssafy_archive.data.api.RegisterRequest
import com.example.ssafy_archive.data.api.UpdateUserInfoRequest
import com.example.ssafy_archive.data.api.UserApi
import com.example.ssafy_archive.data.api.UserDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val api: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://YOUR_API_BASE_URL") // TODO: 실제 URL로 교체
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

    // UserRepository.kt

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

}
