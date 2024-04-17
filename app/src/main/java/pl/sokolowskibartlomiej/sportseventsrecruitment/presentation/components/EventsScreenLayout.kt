package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event

@Composable
fun EventsScreenLayout(
    events: List<Event>,
    isLoading: Boolean,
    isLoadingFailed: Boolean,
    toggleLoadingErrorDialog: (Boolean) -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    if (isLoading) LoadingBox()

    AnimatedVisibility(
        visible = events.isNotEmpty(),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(events, key = { it.id }) { event ->
                EventListItem(event = event, onClick = onItemClick)
            }
        }
    }

    NoInternetDialog(
        isVisible = isLoadingFailed,
        onReconnect = { toggleLoadingErrorDialog(true) },
        onDismiss = { toggleLoadingErrorDialog(false) }
    )
}