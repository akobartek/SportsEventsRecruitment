package pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event

interface EventsRepository {
    suspend fun loadEvents(): Result<List<Event>>
    suspend fun loadSchedule(): Result<List<Event>>
}