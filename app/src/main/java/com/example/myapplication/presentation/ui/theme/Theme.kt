package com.example.myapplication.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val DarkColorPalette = darkColors(
    primary  = RoyalBlue,
    secondary = Teal200,
    //tertiary = Pink80
)

private val LightColorPallete = lightColors(
    primary = RoyalBlue,
    secondary = Teal200
)
//    tertiary = Pink40

//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)

@Composable
fun PatientTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {val colors = if (darkTheme){
    DarkColorPalette
} else {
    LightColorPallete
}
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = colors.primarySurface
    )
    // val colorScheme = when {
    //   dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
    //     val context = LocalContext.current
    //   if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    //}

    // darkTheme -> DarkColorScheme
    //else -> LightColorScheme
    //}
    //val view = LocalView.current
    //if (!view.isInEditMode) {
    //  SideEffect {
    //val window = (view.context as Activity).window
    //window.statusBarColor = colorScheme.primary.toArgb()
    //WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme



    MaterialTheme(
        colors=colors,
        typography= Typography,
        shapes= Shapes,
        content = content
    )
}



//Set of Material typography styles to start with
//val Typography = androidx.compose.material3.Typography(
  //  bodyLarge = TextStyle(
    //    fontFamily = FontFamily.Default,
      //  fontWeight = FontWeight.Normal,
        //fontSize = 16.sp,
        //lineHeight = 24.sp,
        //letterSpacing = 0.5.sp
    //)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
