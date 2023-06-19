package io.twotle.uimakercompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CaffeineViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CaffeineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CaffeineViewModel() as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}