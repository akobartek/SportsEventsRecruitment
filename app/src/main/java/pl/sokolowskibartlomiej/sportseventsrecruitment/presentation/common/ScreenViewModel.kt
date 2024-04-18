package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event

abstract class ScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState = _uiState.asStateFlow()

    abstract fun loadData()

    protected fun setEvents(events: List<Event>) {
        _uiState.getAndUpdate { currentState ->
            currentState.copy(
                isLoading = false,
                events = events
            )
        }
    }

    fun toggleLoading() {
        _uiState.getAndUpdate { currentState ->
            currentState.copy(isLoading = true)
        }
    }

    fun toggleLoadingErrorDialog(reloadData: Boolean = false) {
        _uiState.getAndUpdate { currentState ->
            currentState.copy(isLoadingFailed = !currentState.isLoadingFailed, isLoading = false)
        }
        if (reloadData) loadData()
    }
}