package io.twotle.uimakercompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.twotle.uimakercompose.model.UserSignIn
import io.twotle.uimakercompose.repository.backend.BackendService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginViewModel: ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email;
    private val _pw = mutableStateOf("")
    val pw : State<String> = _pw;
    private val _token = mutableStateOf("")
    val token: State<String> = _token

    fun setEmail(it: String) {
        _email.value = it;
    }

    fun setPW(it: String) {
        _pw.value = it;
    }

    suspend fun login() : String {
        return suspendCoroutine { continuation ->
            viewModelScope.launch(Dispatchers.IO) {
                val result = BackendService.apiService.signIn(UserSignIn(_email.value, _pw.value))
                _token.value = result.body()?.token!!

                // 값을 반환하기 위해 continuation을 호출
                continuation.resume(_token.value)
            }
        }
    }

}