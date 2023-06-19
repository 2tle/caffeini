@file:OptIn(ExperimentalMaterial3Api::class)

package io.twotle.uimakercompose.view.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import io.twotle.uimakercompose.model.Caffeine
import io.twotle.uimakercompose.viewmodel.CaffeineViewModel




@Composable
fun CaffineList(caffines: List<Caffeine>) {
    LazyColumn {
        items(caffines) {
            CaffeineRow(date = it.date, text = it.name , mg = it.caffeine)
        }
    }
}


@Composable
fun CaffeineRow(date: String,text: String, mg: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(date)
        Text(text)
        Text(mg.toString()+"mg")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addCaffeineInputFields(viewModel: CaffeineViewModel) {
    Column {
        TextField(
            value = viewModel.drinkName.value,
            onValueChange = { viewModel.setDrinkName(it) },
            label = { Text(text = "Drink Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.caffine.value.toString(),
            onValueChange = { viewModel.setCaffeine(it.toInt())},
            label = {Text("Caffeine")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}

@Composable
fun addCaffeineBtn(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text("Add")
    }
}