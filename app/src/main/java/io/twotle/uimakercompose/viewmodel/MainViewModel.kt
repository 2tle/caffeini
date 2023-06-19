package io.twotle.uimakercompose.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.twotle.uimakercompose.model.Caffeine
import io.twotle.uimakercompose.repository.backend.BackendService
import io.twotle.uimakercompose.util.sharedpreferences.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _curCaf = mutableStateOf(0)
    val curCaf : State<Int> = _curCaf

    private val _maxCaf = mutableStateOf(0)
    val maxCaf : State<Int> = _maxCaf

    private val _caffeineList = mutableStateListOf<Caffeine>()
    val caffeineList : List<Caffeine> get() = _caffeineList

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = SharedPreferences.prefs.getString("token","")
            Log.d(">>><<<",token)
            val info = BackendService.apiService.getRecommend(token)
            if(info.isSuccessful) {
                try {
                    Log.d("<>",info.body()!!.toString())
                    _username.value = info.body()!!.username
                    _maxCaf.value = info.body()!!.amount
                } catch (e: Exception) {

                }

            }
            val caffeines = BackendService.apiService.getTodayCaffeine(token)
            Log.d("<1>",caffeines.isSuccessful.toString())
            if(caffeines.isSuccessful) {
                try {
                    Log.d("<2>",caffeines.body()!!.toString())
                    if(caffeines.body()!!.lists.size != 0) {
                        _caffeineList.clear()
                        _caffeineList.addAll(caffeines.body()!!.lists)
                        var curCafs = 0;
                        caffeines.body()!!.lists.forEach {
                            curCafs += it.caffeine
                        }
                        _curCaf.value = curCafs
                    }
                } catch (e: Exception) {
                    Log.e("<4>",e.toString())
                }


            }
        }
    }
}