package com.rach.firstnodejs.appUi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rach.firstnodejs.appUi.ui.ALLUsers
import com.rach.firstnodejs.appUi.ui.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(Screens.HomeScreen.route){
            HomeScreen(
                onNavigateToAllUsers = {navController.navigate(Screens.AllUserScreens.route)}
            )
        }

        composable(Screens.AllUserScreens.route){
            ALLUsers()
        }
    }

}