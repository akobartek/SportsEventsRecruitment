package pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository

import pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.DaznApi
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val api: DaznApi
) : EventsRepository {
    override suspend fun loadEvents(): Result<List<Event>> =
        try {
            val events = api.getEvents()
                .map { it.toDomainObject() }
                .sortedBy { it.date }
            Result.success(events)
        } catch (exc: Exception) {
            Result.failure(exc)
        }

    override suspend fun loadSchedule(): Result<List<Event>> =
        try {
            val schedule = api.getSchedule()
                .map { it.toDomainObject() }
                .sortedBy { it.date }
            Result.success(schedule)
        } catch (exc: Exception) {
            Result.failure(exc)
        }
}