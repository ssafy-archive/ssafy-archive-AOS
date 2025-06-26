package com.example.ssafy_archive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.ssafy_archive.ui.auth.LoginActivity
import com.example.ssafy_archive.ui.group.GroupListActivity
import com.example.ssafy_archive.utils.SharedPrefsManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 상태 확인
        val prefs = SharedPrefsManager(this)

        val nextIntent = if (prefs.isLoggedIn) {
            Intent(this, GroupListActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(nextIntent)
        finish() // MainActivity 종료
    }
}
