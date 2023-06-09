package com.mango.marvelworld.ui.activities

import android.os.Bundle
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mango.marvelworld.domain.utils.connectivityobserver.ConnectivityObserver
import com.mango.marvelworld.domain.utils.connectivityobserver.ConnectivityObserverImpl
import com.mango.marvelworld.ui.presentation.features.characterlist.ListScreen
import com.mango.marvelworld.ui.theme.MarvelWorldTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.hypot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = ConnectivityObserverImpl(applicationContext)

        installSplashScreen().setOnExitAnimationListener { splashScreenView ->

            val centerX = splashScreenView.view.width / 2
            val centerY = splashScreenView.view.height / 2
            val startRadius = hypot(centerX.toDouble(), centerY.toDouble()).toFloat()
            val endRadius = 0f

            val splashScreenAnimationEndTime =
                Instant.ofEpochMilli(splashScreenView.iconAnimationStartMillis + splashScreenView.iconAnimationDurationMillis)

            val delay = Instant.now(Clock.systemUTC()).until(
                splashScreenAnimationEndTime,
                ChronoUnit.MILLIS
            )

            val circularReveal = ViewAnimationUtils.createCircularReveal(
                splashScreenView.view, centerX, centerY, startRadius, endRadius
            ).apply {
                startDelay = if (delay > 0) delay else 0
                duration = 300L
                interpolator = AccelerateDecelerateInterpolator()
                doOnEnd { splashScreenView.remove() }
            }

            circularReveal.start()

        }

        setContent {
            MarvelWorldTheme {
                val status by connectivityObserver.observe().collectAsStateWithLifecycle(
                    initialValue = ConnectivityObserver.ConnectiviyStatus.UNAVAILABLE
                )

                ListScreen(
                    connectionStatus = status
                )
            }
        }
    }
}