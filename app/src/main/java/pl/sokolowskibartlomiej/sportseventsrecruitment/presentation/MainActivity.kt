package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.events.EventsScreen
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.playback.PlaybackScreen
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.schedule.ScheduleScreen
import pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.theme.SportsEventsRecruitmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportsEventsRecruitmentTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                var selectedEventVideoUrl by rememberSaveable { mutableStateOf("") }

                LaunchedEffect(key1 = currentDestination) {
                    currentDestination?.let {
                        if (currentDestination.route != Screen.Playback.route)
                            selectedEventVideoUrl = ""
                        else if (selectedEventVideoUrl == "")
                            navController.navigateUp()
                    }
                }

                Scaffold(
                    bottomBar = {
                        val items = listOf(Screen.Events, Screen.Schedule)
                        AnimatedVisibility(
                            visible = currentDestination?.route != Screen.Playback.route,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it })
                        ) {
                            NavigationBar {
                                items.forEach { screen ->
                                    NavigationBarItem(
                                        icon = {
                                            screen.icon?.let { Icon(it, contentDescription = null) }
                                        },
                                        label = { Text(stringResource(screen.titleId)) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            if (currentDestination?.route == Screen.Playback.route)
                                                navController.navigateUp()
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.Events.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Events.route) {
                            EventsScreen(
                                onItemClick = { url ->
                                    selectedEventVideoUrl = url
                                    navController.navigate(Screen.Playback.route)
                                }
                            )
                        }
                        composable(Screen.Schedule.route) { ScheduleScreen() }
                        composable(Screen.Playback.route) {
                            PlaybackScreen(
                                videoUrl = selectedEventVideoUrl,
                                onBackPressed = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}