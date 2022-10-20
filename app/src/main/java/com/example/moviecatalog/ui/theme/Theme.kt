package com.example.moviecatalog.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

private val MyColorScheme = lightColorScheme(
    primary = AccentColor,
    secondary = TextGrayColor,
    onSecondary = White,

    background = BackgroundDarkColor,
    surface = StatusBarColor,
    outline = BorderGrayColor
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MovieCatalogTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = MyColorScheme
    val activity = LocalView.current.context as Activity
    val backgroundArgb = MaterialTheme.colorScheme.surface.toArgb()
    activity.window.statusBarColor = backgroundArgb
    activity.window.navigationBarColor = backgroundArgb

    //activity.window.decorView.apply {
    //    systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    //}

    val windowInsetsController =
        ViewCompat.getWindowInsetsController(activity.window.decorView) ?: return
    // Configure the behavior of the hidden system bars
    windowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    // Hide both the status bar and the navigation bar
    windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}