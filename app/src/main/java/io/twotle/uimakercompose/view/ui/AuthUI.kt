@file:OptIn(ExperimentalMaterial3Api::class)

package io.twotle.uimakercompose.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import io.twotle.uimakercompose.R
import io.twotle.uimakercompose.viewmodel.LoginViewModel
import io.twotle.uimakercompose.viewmodel.RegisterViewModel

@Composable
fun logoImage() {
    val img: Painter = painterResource(id = R.drawable.ic_logo)
    Image(painter = img,contentDescription = "Logo")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginInputFields(viewModel: LoginViewModel) {
    Column {
        TextField(
            value = viewModel.email.value!!,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.pw.value!!,
            onValueChange = { viewModel.setPW(it)},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
    }
}

@Composable
fun registerInputFields(viewModel: RegisterViewModel) {
    Column {
        TextField(
            value = viewModel.username.value!!,
            onValueChange = { viewModel.setUsername(it) },
            label = { Text(text = "Username")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.email.value!!,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text(text = "Email")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.pw.value!!,
            onValueChange = { viewModel.setPW(it) },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.height.value.toString(),
            onValueChange = {viewModel.setHeight(it.toInt()) },
            label = { Text(text = "Height")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.weight.value.toString(),
            onValueChange = {viewModel.setWeight(it.toInt()) },
            label = { Text(text = "Weight")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.age.value.toString(),
            onValueChange = {viewModel.setAge(it.toInt()) },
            label = { Text(text = "Age")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

    }
}

@Composable
fun loginBtn(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text("Sign In")
    }
}
@Composable
fun registerBtn(onClick: () -> Unit) {
    TextButton(onClick =  onClick ) {
        Text(text = "Sign Up")
    }
}