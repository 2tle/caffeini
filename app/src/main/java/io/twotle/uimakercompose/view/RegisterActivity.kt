package io.twotle.uimakercompose.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import io.twotle.uimakercompose.R
import io.twotle.uimakercompose.view.ui.registerBtn
import io.twotle.uimakercompose.view.ui.registerInputFields
import io.twotle.uimakercompose.view.ui.theme.UIMakerComposeTheme
import io.twotle.uimakercompose.viewmodel.LoginViewModel
import io.twotle.uimakercompose.viewmodel.LoginViewModelFactory
import io.twotle.uimakercompose.viewmodel.RegisterViewModel
import io.twotle.uimakercompose.viewmodel.RegisterViewModelFactory
import kotlinx.coroutines.runBlocking

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val registerViewModel = ViewModelProvider(
                this,
                RegisterViewModelFactory()
            )[RegisterViewModel::class.java]
            registerApp(viewModel = registerViewModel)
        }
    }
}

@Composable
fun registerApp(viewModel: RegisterViewModel) {
    val context = LocalContext.current
    UIMakerComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            registerInputFields(viewModel = viewModel)
            Spacer(modifier = Modifier.height(8.dp))
            registerBtn {
                var result = false;
                runBlocking {
                    result = viewModel.register()
                }
                if(result) context.startActivity(Intent(context, LoginActivity::class.java))
            }
        }
    }
}
