package com.example.ssafy_archive.ui.profile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ssafy_archive.ui.theme.SsafyarchiveTheme
import com.example.ssafy_archive.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SsafyarchiveTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyPageScreen(
                        onSaveSuccess = {
                            Toast.makeText(this, "정보가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MyPageScreen(
    viewModel: AuthViewModel = viewModel(),
    onSaveSuccess: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var ssafyNumber by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getUserInfo(
            onResult = { user ->
                name = user.name
                ssafyNumber = user.ssafyNumber
                id = user.id
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("내 정보 수정", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = id,
            onValueChange = {},
            label = { Text("아이디") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("이름") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ssafyNumber,
            onValueChange = { ssafyNumber = it },
            label = { Text("학번") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.updateUserInfo(name, ssafyNumber, onSaveSuccess)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장")
        }
    }
}
