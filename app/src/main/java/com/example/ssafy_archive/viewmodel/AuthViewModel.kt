package com.example.ssafy_archive.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssafy_archive.data.api.RegisterRequest
import com.example.ssafy_archive.data.api.UserDto
import com.example.ssafy_archive.data.repository.UserRepository
import com.example.ssafy_archive.utils.SharedPrefsManager
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val userRepository = UserRepository()

    fun register(
        name: String,
        ssafyNumber: String,
        loginId: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val result = userRepository.register(
                RegisterRequest(
                    loginId = loginId,
                    password = password,
                    name = name,
                    ssafyNumber = ssafyNumber
                )
            )
            if (result) {
                onSuccess()
            }
        }
    }

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch launch@{
            val prefs = SharedPrefsManager(App.instance)
            val userId = prefs.userId?.toInt() ?: return@launch

            val result = userRepository.changePassword(
                userId = userId,
                oldPassword = oldPassword,
                newPassword = newPassword,
                accessToken = prefs.accessToken.orEmpty()
            )

            if (result) onSuccess()
        }
    }

    fun getUserInfo(onResult: (UserDto) -> Unit) {
        viewModelScope.launch {
            val prefs = SharedPrefsManager(App.instance)
            val result = userRepository.getUserInfo(
                accessToken = prefs.accessToken.orEmpty()
            )
            result?.let { onResult(it) }
        }
    }

    fun updateUserInfo(name: String, ssafyNumber: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val prefs = SharedPrefsManager(App.instance)
            val result = userRepository.updateUserInfo(
                name = name,
                ssafyNumber = ssafyNumber,
                accessToken = prefs.accessToken.orEmpty()
            )
            if (result) onSuccess()
        }
    }

    fun login(loginId: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val response = userRepository.login(loginId, password)
            if (response != null) {
                val prefs = SharedPrefsManager(App.instance)
                prefs.accessToken = response.body.accessToken
                prefs.refreshToken = response.body.refreshToken
                prefs.userId = response.body.user.userId.toString()
                prefs.loginId = response.body.user.loginId
                prefs.name = response.body.user.name
                prefs.ssafyNumber = response.body.user.ssafyNumber
                prefs.userRole = response.body.user.userRole
                onSuccess()
            }
        }
    }

}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
