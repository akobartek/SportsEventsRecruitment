package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.events

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository.FakeEventsRepository
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.usecases.LoadEventsUseCase
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class EventsViewModelTest {

    private val timeout = 20.toDuration(DurationUnit.SECONDS)
    private lateinit var repository: FakeEventsRepository

    @Before
    fun setup() {
        repository = FakeEventsRepository()
    }

    @Test
    fun `test init ViewModel state`() = runTest(timeout = timeout) {
        val viewmodel = EventsViewModel(LoadEventsUseCase(repository))
        val state = viewmodel.uiState.value
        Assert.assertTrue(state.isLoading)
        Assert.assertFalse(state.isLoadingFailed)
        Assert.assertTrue(state.events.isEmpty())
    }

    @Test
    fun `test data loading success path`() = runTest(timeout = timeout) {
        val viewmodel = EventsViewModel(LoadEventsUseCase(repository))
        Thread.sleep(2000)
        val state = viewmodel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isLoadingFailed)
        Assert.assertEquals(3, state.events.size)
    }

    @Test
    fun `test data loading fail path`() = runTest(timeout = timeout) {
        repository.returnErrors()
        val viewmodel = EventsViewModel(LoadEventsUseCase(repository))
        Thread.sleep(2000)
        val state = viewmodel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertTrue(state.isLoadingFailed)
        Assert.assertTrue(state.events.isEmpty())
    }
}