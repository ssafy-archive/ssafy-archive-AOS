package com.example.ssafy_archive.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.ssafy_archive.ui.theme.SsafyarchiveTheme
import com.example.ssafy_archive.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ssafy_archive.ui.intro.IntroActivity

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SsafyarchiveTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RegisterScreen(
                        onRegisterSuccess = {
                            Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        },
                        onBackClick = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var ssafyNumber by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var showNameError by remember { mutableStateOf(false) }
    var showNumberError by remember { mutableStateOf(false) }
    var showIdError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var showConfirmError by remember { mutableStateOf(false) }
    var passwordMismatch by remember { mutableStateOf(false) }

    val commonModifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
    val shape = MaterialTheme.shapes.medium

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        // 뒤로가기
        TextButton(onClick = onBackClick) {
            Text("< 뒤로가기", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("회원가입", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    showNameError = false
                },
                label = { Text("이름") },
                modifier = commonModifier,
                shape = shape,
                isError = showNameError
            )
            if (showNameError) {
                Text("이름을 입력해주세요", color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = ssafyNumber,
                onValueChange = {
                    ssafyNumber = it
                    showNumberError = false
                },
                label = { Text("학번") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = commonModifier,
                shape = shape,
                isError = showNumberError
            )
            if (showNumberError) {
                Text("학번을 입력해주세요", color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = id,
                onValueChange = {
                    id = it
                    showIdError = false
                },
                label = { Text("아이디") },
                modifier = commonModifier,
                shape = shape,
                isError = showIdError
            )
            if (showIdError) {
                Text("아이디를 입력해주세요", color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    showPasswordError = false
                    passwordMismatch = false
                },
                label = { Text("비밀번호") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = commonModifier,
                shape = shape,
                isError = showPasswordError
            )
            if (showPasswordError) {
                Text("비밀번호를 입력해주세요", color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    showConfirmError = false
                    passwordMismatch = false
                },
                label = { Text("비밀번호 확인") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = commonModifier,
                shape = shape,
                isError = showConfirmError || passwordMismatch
            )
            when {
                showConfirmError -> Text("비밀번호 확인을 입력해주세요", color = Color.Red, modifier = Modifier.align(Alignment.Start))
                passwordMismatch -> Text("비밀번호가 일치하지 않습니다", color = Color.Red, modifier = Modifier.align(Alignment.Start))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // 유효성 검사
                    showNameError = name.isBlank()
                    showNumberError = ssafyNumber.isBlank()
                    showIdError = id.isBlank()
                    showPasswordError = password.isBlank()
                    showConfirmError = confirmPassword.isBlank()
                    passwordMismatch = password != confirmPassword

                    if (!(showNameError || showNumberError || showIdError || showPasswordError || showConfirmError || passwordMismatch)) {
                        viewModel.register(
                            name = name,
                            ssafyNumber = ssafyNumber,
                            loginId = id,
                            password = password,
                            onSuccess = onRegisterSuccess
                        )
                    }
                },
                modifier = commonModifier,
                shape = shape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF65BDEA))
            ) {
                Text("회원가입 완료", color = Color.White)
            }
        }
    }
}
