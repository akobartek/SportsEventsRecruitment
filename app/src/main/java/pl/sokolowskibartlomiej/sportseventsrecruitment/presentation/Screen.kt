package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.graphics.vector.ImageVector
import pl.sokolowskibartlomiej.sportseventsrecruitment.R

sealed class Screen(
    val route: String,
    @StringRes val titleId: Int,
    val icon: ImageVector? = null,
) {
    data object Events : Screen("events", R.string.events, Icons.Filled.Event)
    data object Schedule : Screen("schedule", R.string.schedule, Icons.Filled.Schedule)
    data object Playback : Screen("playback", R.string.playback)
}