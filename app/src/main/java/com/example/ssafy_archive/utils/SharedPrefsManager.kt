package com.example.ssafy_archive.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "ssafy_community_prefs"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_LOGIN_ID = "login_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_SSAFY_NUMBER = "ssafy_number"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    val isLoggedIn: Boolean
        get() = !accessToken.isNullOrBlank()

    var userId: String?
        get() = prefs.getString(KEY_USER_ID, null)
        set(value) = prefs.edit().putString(KEY_USER_ID, value).apply()

    var id: String?
        get() = prefs.getString(KEY_LOGIN_ID, null)
        set(value) = prefs.edit().putString(KEY_LOGIN_ID, value).apply()

    var name: String?
        get() = prefs.getString(KEY_USER_NAME, null)
        set(value) = prefs.edit().putString(KEY_USER_NAME, value).apply()

    var ssafyNumber: String?
        get() = prefs.getString(KEY_SSAFY_NUMBER, null)
        set(value) = prefs.edit().putString(KEY_SSAFY_NUMBER, value).apply()

    var userRole: String?
        get() = prefs.getString(KEY_USER_ROLE, null)
        set(value) = prefs.edit().putString(KEY_USER_ROLE, value).apply()

    var accessToken: String?
        get() = prefs.getString(KEY_ACCESS_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_ACCESS_TOKEN, value).apply()

    var refreshToken: String?
        get() = prefs.getString(KEY_REFRESH_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_REFRESH_TOKEN, value).apply()

    fun clearTokens() {
        prefs.edit()
            .remove(KEY_ACCESS_TOKEN)
            .remove(KEY_REFRESH_TOKEN)
            .apply()
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}
