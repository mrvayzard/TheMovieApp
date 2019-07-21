package com.movieapp.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Main + SupervisorJob()) {
    override fun onCleared() {
        super.onCleared()
        cancel()
    }
}