package com.example.acedu.core.presentation.base

import androidx.compose.runtime.Immutable

@Immutable
data class LoadStates(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false
) {
    companion object {
        val Idle = LoadStates()
        val Loading = LoadStates(isLoading = true)
        val Refreshing = LoadStates(isRefreshing = true)
        val LoadingMore = LoadStates(isLoadingMore = true)
    }
}