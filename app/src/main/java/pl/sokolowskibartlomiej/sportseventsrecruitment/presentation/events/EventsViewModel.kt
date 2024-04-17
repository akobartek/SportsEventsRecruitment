package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.events

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases.LoadEventsUseCase
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.common.ScreenViewModel

class EventsViewModel(private val loadEventsUseCase: LoadEventsUseCase): ScreenViewModel() {
    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                toggleLoading()
                val events = loadEventsUseCase().getOrThrow()
                setEvents(events)
            } catch (exc: Throwable) {
                toggleLoadingErrorDialog()
            }
        }
    }
}