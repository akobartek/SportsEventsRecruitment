package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.common

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event

data class ScreenUiState(
    val isLoading: Boolean = false,
    val isLoadingFailed: Boolean = false,
    val events: List<Event> = listOf()
)
