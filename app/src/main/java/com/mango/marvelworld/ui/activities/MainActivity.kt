package com.mango.marvelworld.ui.activities

import android.os.Bundle
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.mango.marvelworld.ui.presentation.characterlist.ListScreen
import com.mango.marvelworld.ui.presentation.characterlist.ListViewModel
import com.mango.marvelworld.ui.theme.MarvelWorldTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.hypot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setOnExitAnimationListener { splashScreenView ->

            val centerX = splashScreenView.view.width / 2
            val centerY = splashScreenView.view.height / 2
            val startRadius = hypot(centerX.toDouble(), centerY.toDouble()).toFloat()
            val endRadius = 0f

            val circularReveal = ViewAnimationUtils.createCircularReveal(
                splashScreenView.view, centerX, centerY, startRadius, endRadius
            ).apply {
                startDelay = 1000L
                duration = 500L
                interpolator = AccelerateDecelerateInterpolator()
                doOnEnd { splashScreenView.remove() }
            }

            circularReveal.start()

        }

        setContent {
            val listViewModel = hiltViewModel<ListViewModel>()
            MarvelWorldTheme {
                ListScreen(
                    listViewModel = listViewModel
                )
            }
        }
    }
}