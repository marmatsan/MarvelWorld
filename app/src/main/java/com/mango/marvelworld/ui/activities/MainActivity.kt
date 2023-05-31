package com.mango.marvelworld.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.mango.marvelworld.ui.presentation.characterlist.ListScreen
import com.mango.marvelworld.ui.presentation.characterlist.ListViewModel
import com.mango.marvelworld.ui.theme.MarvelWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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