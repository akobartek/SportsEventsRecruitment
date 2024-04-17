package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components.EventsScreenLayout

@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventsScreenLayout(
        events = uiState.events,
        isLoading = uiState.isLoading,
        isLoadingFailed = uiState.isLoadingFailed,
        toggleLoadingErrorDialog = viewModel::toggleLoadingErrorDialog
    )
}