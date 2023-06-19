package io.twotle.uimakercompose.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import io.twotle.uimakercompose.util.sharedpreferences.SharedPreferences
import io.twotle.uimakercompose.view.ui.loginBtn
import io.twotle.uimakercompose.view.ui.loginInputFields
import io.twotle.uimakercompose.view.ui.logoImage
import io.twotle.uimakercompose.view.ui.registerBtn
import io.twotle.uimakercompose.view.ui.theme.UIMakerComposeTheme
import io.twotle.uimakercompose.viewmodel.LoginViewModel
import io.twotle.uimakercompose.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.runBlocking

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val loginViewModel = ViewModelProvider(
                this,
                LoginViewModelFactory()
            )[LoginViewModel::class.java]
            loginApp(loginViewModel)
        }
    }
}

@Composable
fun loginApp(viewModel: LoginViewModel) {
    val context = LocalContext.current
    UIMakerComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            logoImage()
            Spacer(modifier = Modifier.height(16.dp))
            loginInputFields(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            loginBtn {
                var token = "";
                runBlocking {
                    token = viewModel.login();
                }
                Log.d("><",token)
                SharedPreferences.prefs.setString("token","Bearer "+token);
                context.startActivity(Intent(context, MainActivity::class.java ))
            }
            Spacer(modifier = Modifier.height(16.dp))
            registerBtn {
                context.startActivity(Intent(context, RegisterActivity::class.java))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun loginPreview() {
}