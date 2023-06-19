package io.twotle.uimakercompose.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.twotle.uimakercompose.model.AddCaffeine
import io.twotle.uimakercompose.model.UserSignIn
import io.twotle.uimakercompose.repository.backend.BackendService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CaffeineViewModel: ViewModel() {
    private val _drinkName = mutableStateOf("")
    val drinkName : State<String> = _drinkName

    private val _caffine = mutableStateOf(0)
    val caffine : State<Int> = _caffine

    fun setDrinkName(it: String) {
        _drinkName.value = it
    }

    fun setCaffeine(it: Int) {
        _caffine.value = it
    }

    suspend fun add() : Boolean {
        return suspendCoroutine { continuation ->
            viewModelScope.launch(Dispatchers.IO) {
                val token = io.twotle.uimakercompose.util.sharedpreferences.SharedPreferences.prefs.getString("token","");
                val result = BackendService.apiService.addCaffeineDrink(token,AddCaffeine(_drinkName.value, _caffine.value))
                Log.d("<vm>",result.isSuccessful.toString())
                continuation.resume(result.isSuccessful)
            }
        }
    }
}