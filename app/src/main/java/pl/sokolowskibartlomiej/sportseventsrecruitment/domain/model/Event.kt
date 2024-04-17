package pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model

import java.util.Date

data class Event(
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: Date,
    val imageUrl: String,
    val videoUrl: String?
)