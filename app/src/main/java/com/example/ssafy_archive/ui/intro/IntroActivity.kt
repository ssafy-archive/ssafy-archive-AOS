package com.example.ssafy_archive.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ssafy_archive.R
import com.example.ssafy_archive.ui.auth.LoginActivity
import com.example.ssafy_archive.ui.auth.RegisterActivity
import com.example.ssafy_archive.ui.theme.SsafyarchiveTheme

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SsafyarchiveTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IntroScreen(
                        onStartClick = {
                            startActivity(Intent(this, RegisterActivity::class.java))
                        },
                        onLoginClick = {
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun IntroScreen(
    onStartClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val buttonShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // 수직 중앙 정렬
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.ssarchive_c),
                contentDescription = "SSAFY Archive Logo",
                modifier = Modifier
                    .width(160.dp)
                    .height(160.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "우리들의 싸카이브",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "함께한 SSAFY, 그 이후를 이어가는 작은 공간\n지금, 우리의 이야기를 기록해보세요!",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column {
            Button(
                onClick = onStartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF65BDEA)
                )
            ) {
                Text("시작하기", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "이미 계정이 있나요? ")
                Text(
                    text = "로그인",
                    color = Color(0xFF65BDEA),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onLoginClick)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
