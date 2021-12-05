package com.example.sampleappmvvm.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.LiveData
import com.example.sampleappmvvm.R
import com.example.sampleappmvvm.login.LoginViewModel
import com.example.sampleappmvvm.views.CircularProgressBar


@Composable
fun LoginScreen(onLoginClicked: (String, String) -> Unit, state: LiveData<LoginViewModel.State>) {
    val state = state.observeAsState()
    when (state.value) {
        is LoginViewModel.State.Loading ->
            Box {
                LoginForm(onLoginClicked)
                CircularProgressBar()
            }
        else -> {
            LoginForm(onLoginClicked)
        }
    }
}

@Composable
fun LoginForm(onLoginClicked: (String, String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = colorResource(id = R.color.white))
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Login background", modifier = Modifier.fillMaxWidth()
        )
        Divider(Modifier.size(Dp(0f), Dp(16f)))
        credentials(onLoginClicked)
    }
}

@Composable
fun credentials(onLoginClicked: (String, String) -> Unit) {
    var user by rememberSaveable { mutableStateOf("code") }
    var password by rememberSaveable { mutableStateOf("test") }
    TextField(
        value = user,
        onValueChange = { user = it },
        label = { Text("Username") },
        singleLine = true
    )
    Divider(Modifier.size(Dp(0f), Dp(10f)))
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        singleLine = true
    )
    Divider(Modifier.size(Dp(0f), Dp(16f)))
    Text(
        text = "LOGIN", color = Color(R.color.white),
        modifier = Modifier.clickable(onClick = { onLoginClicked(user, password) })
    )
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun loginScreenPreview() {
    LoginForm(onLoginClicked = ::getToken)
}

fun getToken(user: String, pass: String) {}