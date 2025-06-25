package com.example.ssafy_archive.data.api

import okhttp3.Interceptor
import okhttp3.Response
import com.example.ssafy_archive.utils.SharedPrefsManager
import com.example.ssafy_archive.viewmodel.App

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val prefs = SharedPrefsManager(App.instance)
        val token = prefs.accessToken.orEmpty()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}

// 자동 헤더 주입 적용
// accessToken을 매 요청마다 @Header("Authorization")에 직접 넘기고 있는데,
// 이걸 Interceptor를 사용해서 자동으로 헤더에 주입하면 더 깔끔해진다.