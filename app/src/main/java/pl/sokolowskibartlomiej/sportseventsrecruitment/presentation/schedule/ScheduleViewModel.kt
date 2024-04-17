package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.schedule

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases.LoadScheduleUseCase
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.common.ScreenViewModel

class ScheduleViewModel(private val loadScheduleUseCase: LoadScheduleUseCase): ScreenViewModel() {
    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                toggleLoading()
                val schedule = loadScheduleUseCase().getOrThrow()
                setEvents(schedule)
            } catch (exc: Throwable) {
                toggleLoadingErrorDialog()
            }
        }
    }
}