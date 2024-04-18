package pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository

import kotlinx.coroutines.delay
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.repository.EventsRepository
import java.util.Date

class FakeEventsRepository : EventsRepository {

    private var returnErrors = false

    override suspend fun loadEvents(): Result<List<Event>> {
        delay(1000)
        return if (returnErrors) Result.failure(Throwable())
        else Result.success(
            listOf(
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
        )
    }

    override suspend fun loadSchedule(): Result<List<Event>> {
        delay(1000)
        return if (returnErrors) Result.failure(Throwable())
        else Result.success(
            listOf(
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
        )
    }

    fun returnErrors() {
        returnErrors = true
    }
}