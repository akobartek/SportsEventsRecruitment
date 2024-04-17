package pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository.EventsRepository

class LoadScheduleUseCase(private val eventsRepository: EventsRepository) {
    suspend operator fun invoke() = eventsRepository.loadSchedule()
}