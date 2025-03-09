package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.nio.file.WatchEvent

@Composable
fun SetBackground(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray))
}