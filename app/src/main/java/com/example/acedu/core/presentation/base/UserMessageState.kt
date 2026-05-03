package com.example.acedu.core.presentation.base

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
sealed class UserMessageState {
    data class Success(
        val message: String? = null,
        @StringRes val messageResId: Int? = null,
        val title: String? = null,
        @StringRes val titleResId: Int? = null
    ) : UserMessageState()

    data class Error(
        val message: String? = null,
        @StringRes val messageResId: Int? = null,
        val title: String? = null,
        @StringRes val titleResId: Int? = null
    ) : UserMessageState()

    data class Warning(
        val message: String? = null,
        @StringRes val messageResId: Int? = null,
        val title: String? = null,
        @StringRes val titleResId: Int? = null
    ) : UserMessageState()

    data class Info(
        val message: String? = null,
        @StringRes val messageResId: Int? = null,
        val title: String? = null,
        @StringRes val titleResId: Int? = null
    ) : UserMessageState()
}