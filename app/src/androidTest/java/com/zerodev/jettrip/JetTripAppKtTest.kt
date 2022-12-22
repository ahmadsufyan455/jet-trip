package com.zerodev.jettrip

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.zerodev.jettrip.data.TripDataSource
import com.zerodev.jettrip.ui.navigation.Screen
import com.zerodev.jettrip.ui.theme.JetTripTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetTripAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetTripTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetTripApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Splash.route)
    }

    @Test
    fun navHost_clickItem_navigateToDetailWithData() {
        // wait until trip list is shown
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("tripList")
                .fetchSemanticsNodes().size == 1
        }
        // do some action
        composeTestRule.onNodeWithTag("tripList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(TripDataSource.trips[9].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(TripDataSource.trips[9].name).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("tripList")
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithStringId(R.string.favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigateBack() {
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("tripList")
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithTag("tripList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(TripDataSource.trips[9].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithContentDescription("Back").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun add_item_to_favorite() {
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("tripList")
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithTag("tripList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(TripDataSource.trips[9].name).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithContentDescription("Favorite").performClick()
        composeTestRule.onNodeWithContentDescription("Back").performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.favorite).performClick()
        composeTestRule.onNodeWithTag("tripList").assertExists()
    }
}