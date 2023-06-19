package io.twotle.uimakercompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.twotle.uimakercompose.model.UserSignIn
import io.twotle.uimakercompose.model.UserSignUp
import io.twotle.uimakercompose.repository.backend.BackendService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RegisterViewModel: ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email;
    private val _pw = mutableStateOf("")
    val pw : State<String> = _pw;
    private val _username = mutableStateOf("")
    val username: State<String> = _username;
    private val _height = mutableStateOf(0)
    val height : State<Int> = _height
    private val _weight = mutableStateOf(0)
    val weight : State<Int> = _weight
    private val _age = mutableStateOf(0)
    val age : State<Int> = _age;

    fun setEmail(it: String) {
        _email.value = it
    }
    fun setPW(it:String) {
        _pw.value = it
    }
    fun setUsername(it: String) {
        _username.value = it
    }
    fun setHeight(it: Int) {
        _height.value = it
    }
    fun setWeight(it: Int) {
        _weight.value = it
    }
    fun setAge(it: Int) {
        _age.value = it
    }

    suspend fun register(): Boolean {
        return suspendCoroutine { continuation ->
            viewModelScope.launch(Dispatchers.IO) {
                val result = BackendService.apiService.signUp(UserSignUp(
                    _email.value,
                    _pw.value,
                    _username.value,
                    _height.value,
                    _weight.value,
                    _age.value
                ))
                continuation.resume(true)
            }
        }
    }
}