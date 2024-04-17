package pl.sokolowskibartlomiej.sportseventsrecruitment.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository.EventsRepositoryImpl
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository.EventsRepository
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases.LoadEventsUseCase
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases.LoadScheduleUseCase
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.events.EventsViewModel
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.schedule.ScheduleViewModel

val mainModule = module {
    single<EventsRepository> { EventsRepositoryImpl(get()) }
    single { LoadEventsUseCase(get()) }
    single { LoadScheduleUseCase(get()) }

    viewModel { EventsViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
}