package pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository

import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import java.util.Date

val sampleEvents = listOf(
    Event(
        1,
        "title1",
        "subtitle1",
        Date(),
        "https://pg-im.wpcdn.pl/2/7510/z7510052O,Logo-Android.jpg",
        "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
    ),
    Event(
        2,
        "title2",
        "subtitle2",
        Date(),
        "https://pg-im.wpcdn.pl/2/7510/z7510052O,Logo-Android.jpg",
        "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
    ),
    Event(
        3,
        "title3",
        "subtitle3",
        Date(),
        "https://pg-im.wpcdn.pl/2/7510/z7510052O,Logo-Android.jpg",
        "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
    ),
)

val sampleSchedule = listOf(
    Event(
        1,
        "title1",
        "subtitle1",
        Date(),
        "https://pg-im.wpcdn.pl/2/7510/z7510052O,Logo-Android.jpg",
        null
    ),
    Event(
        2,
        "title2",
        "subtitle2",
        Date(),
        "https://pg-im.wpcdn.pl/2/7510/z7510052O,Logo-Android.jpg",
        null
    )
)