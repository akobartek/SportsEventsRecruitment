package pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository.EventsRepository

class LoadEventsUseCase(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke() = eventsRepository.loadEvents()
}