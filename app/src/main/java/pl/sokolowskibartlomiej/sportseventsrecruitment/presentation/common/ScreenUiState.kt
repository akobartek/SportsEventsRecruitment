package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.common

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event

data class ScreenUiState(
    val events: List<Event> = listOf(),
    val isLoading: Boolean = true,
    val isLoadingFailed: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (other is ScreenUiState &&
            (events !== other.events || isLoading != other.isLoading || isLoadingFailed != other.isLoadingFailed)
        ) return false
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = events.hashCode()
        result = 31 * result + isLoading.hashCode()
        result = 31 * result + isLoadingFailed.hashCode()
        return result
    }
}
