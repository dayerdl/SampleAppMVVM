package com.example.sampleappmvvm.login.ui

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.sampleappmvvm.R

@Composable
fun LoginScreen(context: Context) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Login background", modifier = Modifier.fillMaxWidth()
        )
        Divider(Modifier.size(Dp(0f), Dp(16f)))
        credentials()
        Divider(Modifier.size(Dp(0f), Dp(16f)))
        Text(text = "LOGIN", color = colorResource(id = R.color.purple),
            modifier = Modifier.clickable { Toast.makeText(context,"hola", Toast.LENGTH_SHORT).show() })
    }
}

@Composable
fun credentials() {
    inputBox("Name")
    Divider(Modifier.size(Dp(0f), Dp(10f)))
    inputBox("Password")
}

@Composable
fun inputBox(title: String) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(title) },
        singleLine = true
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun loginScreenPreview() {
    LoginScreen(LocalContext.current)
}