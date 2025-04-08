package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreen, modifier) {
        composable<MainScreen> {
            MainScreen(
                { text -> navController.navigate(DetailsScreen(text)) },
                { navController.navigate(ThirdScreen) },
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp)
            )
        }
        composable<DetailsScreen> { entry ->
            val route: DetailsScreen = entry.toRoute()
            DetailScreen(name = route.name, navController)
        }
        composable<ThirdScreen> {
            ThirdScreen { navController.popBackStack() }
        }
    }
}

@Composable
fun MainScreen(
    navigateToDetails: (String) -> Unit,
    toThirdScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember {
        mutableStateOf("")
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
            onClick = { navigateToDetails(text) },
            modifier = Modifier.align(Alignment.End),
        ) {
            Text(text = "To Detail Screen")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                toThirdScreen()
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
                navController.navigate(ThirdScreen)
            },
        ) {
            Text("Navigate to the third screen")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(MainScreen)
            },
        ) {
            Text("Exit")
        }

    }
}

@Composable
fun ThirdScreen(
    onPrevScreen: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Third screen")

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                onPrevScreen()
            },
        ) {
            Text("Exit")
        }
    }
}