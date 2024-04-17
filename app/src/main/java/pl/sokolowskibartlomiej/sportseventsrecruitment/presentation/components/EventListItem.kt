package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import pl.sokolowskibartlomiej.sportseventsrecruitment.R
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.utils.getFormattedString

@Composable
fun EventListItem(
    event: Event,
    onClick: (String) -> Unit = {}
) {
    val context = LocalContext.current

    Card(
        onClick = { if (!event.videoUrl.isNullOrBlank()) onClick(event.videoUrl) },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(event.imageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_downloading),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(width = 150.dp, height = 120.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = event.subtitle,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = event.date.getFormattedString(context),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}