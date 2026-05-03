package com.example.acedu.core.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.acedu.core.presentation.components.LoadingState
import com.example.acedu.core.presentation.components.UserMessageBlock
import com.example.acedu.core.presentation.navigation.LocalNavigationManager
import com.example.acedu.core.presentation.navigation.NavigationManager
import com.example.acedu.core.presentation.theme.AceduTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <UiState, reified ViewModelType : BaseViewModel<UiState>> BaseScreen(
    viewModel: ViewModelType = hiltViewModel(),
    navigationManager: NavigationManager = LocalNavigationManager.current,
    content: @Composable (uiState: UiState, viewModel: ViewModelType) -> Unit
) {
    val baseUiState by viewModel.baseUiState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    navigationManager.navigateTo(
                        screen = event.screen,
                        popUpTo = event.popUpTo,
                        inclusive = event.inclusive,
                        clearBackStack = event.clearBackStack,
                        launchSingleTop = event.launchSingleTop
                    )
                }
                is NavigationEvent.Back -> {
                    navigationManager.navigateBack()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content(uiState, viewModel)

        if (baseUiState.loadStates.isLoading) {
            LoadingState(backgroundColor = Color.Black.copy(alpha = 0.4f))
        }
    }

    baseUiState.userMessageState?.let { userMessageState ->
        ModalBottomSheet(
            onDismissRequest = { viewModel.dismissUserMessageState() },
            sheetState = sheetState,
            containerColor = AceduTheme.colors.surfaceContainerLow,
            scrimColor = Color.Black.copy(alpha = 0.6f),
            dragHandle = null
        ) {
            UserMessageBlock(state = userMessageState)
        }
    }
}
