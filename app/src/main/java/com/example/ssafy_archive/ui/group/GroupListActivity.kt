package com.example.ssafy_archive.ui.group

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ssafy_archive.ui.profile.MyPageActivity
import com.example.ssafy_archive.ui.theme.SsafyarchiveTheme

class GroupListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SsafyarchiveTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GroupListScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupListScreen() {
    val context = LocalContext.current

    // 뒤로가기 → 앱 종료
    BackHandler {
        (context as? Activity)?.finishAffinity()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("그룹 리스트") },
                actions = {
                    TextButton(onClick = {
                        context.startActivity(Intent(context, MyPageActivity::class.java))
                    }) {
                        Text("마이페이지")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            GroupListContent()
        }
    }
}

@Composable
fun GroupListContent() {
    // 임시 그룹 데이터 (추후 API 연동)
    val dummyGroups = listOf(
        "🔥 SSAFY 1반",
        "📚 알고리즘 스터디",
        "👩‍💻 안드로이드 앱팀",
        "☕ 카페 모각코",
        "🎮 게임 개발자 모임"
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummyGroups) { groupName ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = groupName,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
