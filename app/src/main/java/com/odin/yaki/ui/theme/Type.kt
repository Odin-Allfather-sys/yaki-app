package com.odin.yaki.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.odin.yaki.R

val Barlow = FontFamily(
    Font(R.font.barlow_400, FontWeight.Normal),
    Font(R.font.barlow_500, FontWeight.Medium),
    Font(R.font.barlow_600, FontWeight.SemiBold),
    Font(R.font.barlow_700, FontWeight.Bold),
)

val BarlowSC = FontFamily(
    Font(R.font.barlowsc_500, FontWeight.Medium),
    Font(R.font.barlowsc_600, FontWeight.SemiBold),
    Font(R.font.barlowsc_700, FontWeight.Bold),
)

val YakiTypography = Typography(
    displayLarge = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 46.sp),
    displayMedium = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 42.sp),
    headlineMedium = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    headlineSmall = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 21.sp),
    titleLarge = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 18.sp),
    titleMedium = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    bodyLarge = TextStyle(fontFamily = Barlow, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = Barlow, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = Barlow, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.SemiBold, fontSize = 11.sp),
)
