package com.example.ssafy_archive.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ssafy_archive.R
import com.example.ssafy_archive.ui.theme.SsafyarchiveTheme
import com.example.ssafy_archive.utils.SharedPrefsManager
import com.example.ssafy_archive.ui.auth.LoginActivity
import com.example.ssafy_archive.ui.group.GroupListActivity
import com.example.ssafy_archive.ui.intro.IntroActivity
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = SharedPrefsManager(applicationContext)
        val isLoggedIn = prefs.isLoggedIn

        setContent {
            SsafyarchiveTheme {
                SplashScreen {
                    if (isLoggedIn) {
                        startActivity(Intent(this, GroupListActivity::class.java))
                    } else {
                        startActivity(Intent(this, IntroActivity::class.java))
                    }
                    finish()
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF65BDEA))
    ) {
        // 가운데 로고
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ssarchive_w),
                contentDescription = "SSAFY Archive Logo",
                modifier = Modifier.size(180.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ver. 1.0.0",
                color = Color.White
            )
        }

        // 하단 저작권 문구
        Text(
            text = "SSARCHIVE. All rights reserved.",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        )
    }
}
