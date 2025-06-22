package com.example.ssafy_archive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
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
            // TODO: Retrofit 연동 예정
            if (name.isNotBlank() && loginId.isNotBlank()) {
                onSuccess()
            }
        }
    }

}
