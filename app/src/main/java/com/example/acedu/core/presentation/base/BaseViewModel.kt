package com.example.acedu.core.presentation.base

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acedu.R
import com.example.acedu.navigation.Screen
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState> : ViewModel() {

    private val _baseUiState = MutableStateFlow(BaseUiState())
    val baseUiState: StateFlow<BaseUiState> = _baseUiState.asStateFlow()

    private val _uiState by lazy { MutableStateFlow(getInitialUiState()) }
    val uiState : StateFlow<UiState> by lazy { _uiState.asStateFlow() }


    abstract fun getInitialUiState(): UiState

    protected val currentState: UiState
        get() = _uiState.value

    protected val currentBaseState: BaseUiState
        get() = _baseUiState.value

    protected fun updateState(update: (UiState) -> UiState) {
        _uiState.update(function = update)
    }

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val navigationEvent = _navigationEvent.asSharedFlow()

    protected fun navigateTo(
        screen: Screen,
        popUpTo: Screen? = null,
        inclusive: Boolean = false,
        clearBackStack: Boolean = false,
        launchSingleTop: Boolean = false
    ) {
        viewModelScope.launch {
            _navigationEvent.emit(
                NavigationEvent.NavigateTo(
                    screen = screen,
                    popUpTo = popUpTo,
                    inclusive = inclusive,
                    clearBackStack = clearBackStack,
                    launchSingleTop = launchSingleTop
                )
            )
        }
    }

    protected fun <T> execute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: ((Throwable) -> Unit)? = null,
        showLoading: Boolean = true
    ) {
        viewModelScope.launch {
            if (showLoading) _baseUiState.update { it.copy(loadStates = LoadStates.Loading) }
            try {
                val result = call()
                onSuccess(result)
            } catch (e: Exception) {
                if (onError != null) {
                    onError(e)
                } else {
                    handleError(message = e.localizedMessage ?: "", throwable = e)
                }
            } finally {
                if (showLoading) _baseUiState.update { it.copy(loadStates = LoadStates.Idle) }
            }
        }
    }

    protected open fun handleError(message: String, throwable: Throwable? = null, httpCode: Int? = null) {
        when {
            httpCode != null && httpCode >= 500 -> showErrorMessage(messageResId = R.string.error_server)
            message.isBlank() -> showErrorMessage(messageResId = R.string.error_unknown)
            else -> showErrorMessage(message = message)
        }
    }

    protected fun showSuccessMessage(message: String? = null, title: String? = null,messageResId: Int? = null) {
        showUserMessage(UserMessageState.Success(message = message, title = title, messageResId = messageResId))
    }

    protected fun showErrorMessage(message: String? = null, title: String? = null) {
        showUserMessage(UserMessageState.Error(message = message, title = title))
    }

    protected fun showErrorMessage(messageResId: Int) {
        showUserMessage(UserMessageState.Error(messageResId = messageResId))
    }

    protected fun showWarningMessage(message: String? = null, title: String? = null) {
        showUserMessage(UserMessageState.Warning(message = message, title = title))
    }

    protected fun showInfoMessage(message: String? = null, title: String? = null) {
        showUserMessage(UserMessageState.Info(message = message, title = title))
    }

    fun dismissUserMessageState() {
        _baseUiState.update { it.copy(userMessageState = null) }
    }

    private fun showUserMessage(state: UserMessageState) {
        _baseUiState.update { it.copy(userMessageState = state) }
    }
}

sealed class NavigationEvent {
    data class NavigateTo(
        val screen: Screen,
        val popUpTo: Screen? = null,
        val inclusive: Boolean = false,
        val clearBackStack: Boolean = false,
        val launchSingleTop: Boolean = false
    ) : NavigationEvent()

    data object Back : NavigationEvent()
}