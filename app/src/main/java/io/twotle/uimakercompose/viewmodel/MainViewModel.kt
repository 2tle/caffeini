package io.twotle.uimakercompose.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.twotle.uimakercompose.model.Caffeine
import io.twotle.uimakercompose.repository.backend.BackendService
import io.twotle.uimakercompose.util.sharedpreferences.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _username = MutableStateFlow<String>("")
    val username: StateFlow<String> get() = _username

    private val _curCaf = MutableStateFlow<Int>(0)
    val curCaf : StateFlow<Int> get() = _curCaf

    private val _maxCaf = MutableStateFlow<Int>(0)
    val maxCaf : StateFlow<Int> get() = _maxCaf

    private val _caffeineList = MutableStateFlow<List<Caffeine>>(arrayListOf())
    val caffeineList : StateFlow<List<Caffeine>> get() = _caffeineList

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = SharedPreferences.prefs.getString("token","")
            Log.d(">>><<<",token)
            val info = BackendService.apiService.getRecommend(token)
            if(info.isSuccessful) {
                try {
                    Log.d("<>",info.body()!!.toString())
                    _username.value = info.body()!!.username
                    _maxCaf.value = info.body()!!.amount.toInt()
                } catch (e: Exception) {

                }

            }
            val caffeines = BackendService.apiService.getTodayCaffeine(token)
            Log.d("<1>",caffeines.isSuccessful.toString())
            if(caffeines.isSuccessful) {
                try {
                    Log.d("<2>",caffeines.body()!!.toString())
                    if(caffeines.body()!!.lists.size != 0) {
                        _caffeineList.value = caffeines.body()!!.lists
                        var curCafs = 0.0;
                        caffeines.body()!!.lists.forEach {
                            curCafs += it.caffeine
                        }
                        _curCaf.value = curCafs.toInt()
                    }
                } catch (e: Exception) {
                    Log.e("<4>",e.toString())
                }


            }
        }
    }
}