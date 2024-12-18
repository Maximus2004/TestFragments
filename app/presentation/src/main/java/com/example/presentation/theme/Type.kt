package com.example.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.W400),
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_bold, FontWeight.W700)
)

val Typography = Typography(
    labelMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = Color.Black
    ),
    labelLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
        letterSpacing = 0.25.sp,
        color = Color.Black
    ),
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        color = Color.Black
    ),
    displayMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.Black
    ),
    displayLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.Black
    ),
    headlineMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color.Black
    ),
)