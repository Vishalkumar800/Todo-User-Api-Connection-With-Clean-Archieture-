package com.rach.firstnodejs.appUi.navigation

sealed class Screens(val route:String) {

    object HomeScreen:Screens("HomeScreens")
    object AllUserScreens:Screens("AllUsersScreens")

}