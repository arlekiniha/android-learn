package com.example.myapplication

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

//sealed class Screen(
//    val route: String,
//) {
//    object MainScreen : Screen("main_screen")
//    object DetailScreen : Screen("detail_screen")
//    object ThirdScreen : Screen("third_screen")
//
//    fun withArgs(vararg args: String): String {
//        return buildString{
//            append(route)
//            args.forEach { arg ->
//                append("/$arg")
//            }
//        }
//    }
//}

@Serializable
data object MainScreen

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class DetailsScreen(
    val name: String,
)

@Serializable
data object ThirdScreen

