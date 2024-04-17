package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components.EventsScreenLayout

@Composable
fun EventsScreen(
    viewModel: EventsViewModel = koinViewModel(),
    onItemClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventsScreenLayout(
        events = uiState.events,
        isLoading = uiState.isLoading,
        isLoadingFailed = uiState.isLoadingFailed,
        toggleLoadingErrorDialog = viewModel::toggleLoadingErrorDialog,
        onItemClick = onItemClick
    )
}