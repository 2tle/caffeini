package io.twotle.uimakercompose.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import io.twotle.uimakercompose.model.Caffeine
import io.twotle.uimakercompose.view.ui.addingFloatingActionBtn
import io.twotle.uimakercompose.view.ui.mainCaffeineList
import io.twotle.uimakercompose.view.ui.mainTopBar
import io.twotle.uimakercompose.view.ui.theme.UIMakerComposeTheme
import io.twotle.uimakercompose.viewmodel.LoginViewModel
import io.twotle.uimakercompose.viewmodel.LoginViewModelFactory
import io.twotle.uimakercompose.viewmodel.MainViewModel
import io.twotle.uimakercompose.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    val mainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory()
        )[MainViewModel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(mainViewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.fetchData()
    }
}

@Composable
fun MainApp(viewModel: MainViewModel) {
    UIMakerComposeTheme {
        val context = LocalContext.current


        Column {
            mainTopBar(viewModel.username.value,viewModel.curCaf.value, viewModel.maxCaf.value)
            mainCaffeineList(viewModel.caffeineList)

        }
        addingFloatingActionBtn {
            context.startActivity(Intent(context, AddCaffeineActivity::class.java))
        }

    }
}