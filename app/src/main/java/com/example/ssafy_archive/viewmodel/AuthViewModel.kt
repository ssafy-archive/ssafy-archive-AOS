package com.example.ssafy_archive.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssafy_archive.data.api.RegisterRequest
import com.example.ssafy_archive.data.repository.UserRepository
import com.example.ssafy_archive.utils.SharedPrefsManager
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val userRepository = UserRepository()

    fun login(loginId: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            // TODO: 추후 Retrofit 연동
            if (loginId.isNotBlank() && password.isNotBlank()) {
                onSuccess()
            }
        }
    }

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
