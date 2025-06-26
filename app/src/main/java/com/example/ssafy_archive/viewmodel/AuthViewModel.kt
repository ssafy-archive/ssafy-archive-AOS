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
                    name = name,
                    ssafyNumber = ssafyNumber,
                    loginId = loginId,
                    password = password
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

    fun login(
        id: String, password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val resp = userRepository.login(id, password)
                if (resp?.code == 200) {
                    val tokenBody = resp.body
                    val prefs = SharedPrefsManager(App.instance)
                    prefs.accessToken  = tokenBody.accessToken
                    prefs.refreshToken = tokenBody.refreshToken
                    onSuccess()
                } else {
                    onError("아이디 또는 비밀번호가 잘못되었습니다.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onError("서버 연결에 실패했습니다.")
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
