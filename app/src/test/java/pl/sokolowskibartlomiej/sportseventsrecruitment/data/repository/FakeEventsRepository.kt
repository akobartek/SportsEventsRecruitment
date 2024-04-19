package pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository

import kotlinx.coroutines.delay
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository.EventsRepository

class FakeEventsRepository : EventsRepository {

    private var returnErrors = false

    override suspend fun loadEvents(): Result<List<Event>> {
        delay(1000)
        return if (returnErrors) Result.failure(Throwable())
        else Result.success(sampleEvents)
    }

    override suspend fun loadSchedule(): Result<List<Event>> {
        delay(1000)
        return if (returnErrors) Result.failure(Throwable())
        else Result.success(sampleSchedule)
    }

    fun returnErrors() {
        returnErrors = true
    }
}