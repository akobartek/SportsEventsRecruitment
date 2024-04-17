package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.graphics.vector.ImageVector
import pl.sokolowskibartlomiej.sportseventsrecruitment.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    data object Events : Screen("events", Icons.Filled.Event, R.string.events)
    data object Schedule : Screen("schedule", Icons.Filled.Schedule, R.string.schedule)
}