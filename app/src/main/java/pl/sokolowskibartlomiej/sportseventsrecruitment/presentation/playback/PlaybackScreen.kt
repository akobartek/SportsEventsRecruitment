package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.playback

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components.VideoPlayer

@Composable
fun PlaybackScreen(videoUrl: String, onBackPressed: () -> Unit) {
    AnimatedVisibility(
        visible = videoUrl.isNotBlank(),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            VideoPlayer(
                videoUrl = videoUrl,
                modifier = Modifier.align(Alignment.Center)
            )
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        }
    }
}