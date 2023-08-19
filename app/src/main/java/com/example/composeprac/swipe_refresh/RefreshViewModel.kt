package com.example.composeprac.swipe_refresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RefreshViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadSomething()
    }

    fun loadSomething() = viewModelScope.launch {
        _isLoading.value = true
        delay(1000L)
        _isLoading.value = false
    }

}