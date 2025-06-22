package com.example.ssafy_archive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssafy_archive.data.api.RegisterRequest
import com.example.ssafy_archive.data.repository.UserRepository
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
        viewModelScope.launch {
            // TODO: 실제 API 호출 예정
            if (oldPassword.isNotBlank() && newPassword.isNotBlank()) {
                onSuccess()
            }
        }
    }


}
