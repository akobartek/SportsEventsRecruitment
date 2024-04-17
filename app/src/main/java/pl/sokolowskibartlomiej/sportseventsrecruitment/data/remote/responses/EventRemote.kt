package pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.responses

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import java.util.Date

data class EventRemote(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: Date,
    val imageUrl: String,
    val videoUrl: String?
) {
    fun toDomainObject() = Event(
        id = id.toIntOrNull() ?: 0,
        title = title,
        subtitle = subtitle,
        date = date,
        imageUrl = imageUrl,
        videoUrl = videoUrl
    )
}
