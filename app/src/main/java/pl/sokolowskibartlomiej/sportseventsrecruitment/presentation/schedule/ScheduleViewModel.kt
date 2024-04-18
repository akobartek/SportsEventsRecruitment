package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.schedule

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases.LoadScheduleUseCase
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.common.ScreenViewModel

class ScheduleViewModel(private val loadScheduleUseCase: LoadScheduleUseCase) : ScreenViewModel() {
    init {
        loadData()
    }

    private var job: Job? = null

    override fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            toggleLoading()
            job = launch {
                while (true) {
                    val schedule = loadScheduleUseCase()
                    if (schedule.isSuccess) setEvents(schedule.getOrElse { listOf() })
                    else toggleLoadingErrorDialog()
                    delay(30_000)
                }
            }
            job?.join()
        }
    }

    override fun onCleared() {
        job?.cancel()
        job = null
        super.onCleared()
    }
}