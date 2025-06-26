package com.example.ssafy_archive.data.api

import okhttp3.Interceptor
import okhttp3.Response
import com.example.ssafy_archive.utils.SharedPrefsManager
import com.example.ssafy_archive.viewmodel.App

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val path = req.url.encodedPath        // "/api/v1/user", "/api/v1/user/login" 등
        val method = req.method               // "GET", "POST" 등

        // public API: 회원가입, 로그인, (토큰 리프레시) 요청은 토큰 스킵
        val isRegister = method == "POST" && path == "/api/v1/user"
        val isLogin    = method == "POST" && path == "/api/v1/user/login"
        val isRefresh  = method == "POST" && path == "/api/v1/user/refresh"

        if (isRegister || isLogin || isRefresh) {
            return chain.proceed(req)
        }

        // 그 외 요청에만 SharedPrefs의 토큰을 헤더에 붙임
        val token = SharedPrefsManager(App.instance).accessToken
        val builder = req.newBuilder()
        if (!token.isNullOrBlank()) {
            builder.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(builder.build())
    }
}
