package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Test
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.theme.SportsEventsRecruitmentTheme

class NoInternetDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun noInternetDialog_notVisibleWhenFlagFalse() {
        composeTestRule.setContent {
            SportsEventsRecruitmentTheme {
                NoInternetDialog(
                    isVisible = false,
                    onReconnect = { },
                    onDismiss = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("No internet connection")
            .assertDoesNotExist()
    }

    @Test
    fun noInternetDialog_visibleWhenFlagTrue() {
        composeTestRule.setContent {
            SportsEventsRecruitmentTheme {
                NoInternetDialog(
                    isVisible = true,
                    onReconnect = { },
                    onDismiss = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("No internet connection")
            .assertExists()
    }

    @Test
    fun noInternetDialog_onReconnectClickWorks() {
        var clicked by mutableStateOf(false)
        composeTestRule.setContent {
            SportsEventsRecruitmentTheme {
                NoInternetDialog(
                    isVisible = true,
                    onReconnect = { clicked = true },
                    onDismiss = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Try again")
            .performClick()
        assert(clicked)
    }

    @Test
    fun noInternetDialog_onDismissClickWorks() {
        var clicked by mutableStateOf(false)
        composeTestRule.setContent {
            SportsEventsRecruitmentTheme {
                NoInternetDialog(
                    isVisible = true,
                    onReconnect = { },
                    onDismiss = { clicked = true }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Cancel")
            .performClick()
        assert(clicked)
    }
}