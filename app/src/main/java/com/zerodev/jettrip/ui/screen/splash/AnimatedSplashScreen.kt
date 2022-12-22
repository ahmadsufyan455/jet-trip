package com.zerodev.jettrip.ui.screen.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zerodev.jettrip.R
import com.zerodev.jettrip.ui.navigation.Screen
import com.zerodev.jettrip.ui.theme.JetTripTheme
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController,
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(
    alpha: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag("splashScreen")
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize()
            .alpha(alpha = alpha),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
@Preview
fun SplashPreview() {
    JetTripTheme {
        Splash(alpha = 1f)
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashDarkPreview() {
    JetTripTheme {
        Splash(alpha = 1f)
    }
}