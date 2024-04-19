package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import pl.sokolowskibartlomiej.sportseventsrecruitment.domain.model.Event
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.theme.SportsEventsRecruitmentTheme
import java.util.Date

class EventListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val event = Event(
        1,
        "title",
        "subtitle",
        Date(),
        "https://pg-im.wpcdn.pl/2/7510/z7510052O,Logo-Android.jpg",
        "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
    )

    @Test
    fun eventListItem_showCorrectData() {
        composeTestRule.setContent {
            SportsEventsRecruitmentTheme {
                EventListItem(
                    event = event,
                    onClick = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("title")
            .assertExists()
        composeTestRule
            .onNodeWithText("subtitle")
            .assertExists()
    }

    @Test
    fun eventListItem_onClickWorks() {
        var videoUrl by mutableStateOf("")
        composeTestRule.setContent {
            SportsEventsRecruitmentTheme {
                EventListItem(
                    event = event,
                    onClick = { videoUrl = event.videoUrl ?: "" }
                )
            }
        }
        composeTestRule
            .onRoot()
            .performClick()
        assert(videoUrl == event.videoUrl)
    }
}