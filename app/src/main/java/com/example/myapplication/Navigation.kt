package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                navController, Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp)
            )
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"), navController)
        }
        composable(
            route = Screen.ThirdScreen.route,
        ) {
            ThirdScreen()
        }
    }
}

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var text by remember {
        mutableStateOf(" ")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(text))
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To Detail Screen")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(Screen.ThirdScreen.route)
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text("Navigate to the third screen")
        }
    }
}

@Composable
fun DetailScreen(
    name: String?,
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name")

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(Screen.ThirdScreen.route)
            },
        ) {
            Text("Navigate to the third screen")
        }

    }
}

@Composable
fun ThirdScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Third screen")
    }
}