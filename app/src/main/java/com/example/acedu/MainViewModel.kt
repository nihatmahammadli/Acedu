package com.example.acedu

import androidx.lifecycle.ViewModel
import com.example.acedu.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _isReady = MutableStateFlow(true)
    val isReady = _isReady.asStateFlow()

    private val _startDestination = MutableStateFlow(Screen.Splash.route)
    val startDestination = _startDestination.asStateFlow()
}
