package io.twotle.uimakercompose.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import io.twotle.uimakercompose.view.ui.addCaffeineBtn
import io.twotle.uimakercompose.view.ui.addCaffeineInputFields
import io.twotle.uimakercompose.view.ui.theme.UIMakerComposeTheme
import io.twotle.uimakercompose.viewmodel.CaffeineViewModel
import io.twotle.uimakercompose.viewmodel.CaffeineViewModelFactory
import io.twotle.uimakercompose.viewmodel.LoginViewModel
import io.twotle.uimakercompose.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.runBlocking

class AddCaffeineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val caffeineViewModel = ViewModelProvider(
                this,
                CaffeineViewModelFactory()
            )[CaffeineViewModel::class.java]
            addCaffeineApp(caffeineViewModel = caffeineViewModel)
        }
    }
}

@Composable
fun addCaffeineApp(caffeineViewModel: CaffeineViewModel) {
    val context = LocalContext.current
    UIMakerComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            addCaffeineInputFields(viewModel = caffeineViewModel)
            Spacer(modifier = Modifier.padding(8.dp))
            addCaffeineBtn {
                var result = false
                runBlocking {
                    result = caffeineViewModel.add()
                }
                Log.d(">>",result.toString())
                if(result) context.startActivity(Intent(context, MainActivity::class.java ))
            }
        }
    }
}