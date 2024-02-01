package com.dantepereyra.dailyplanner.features.task

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


val ROUTE="task_screen"
fun NavController.navigateToTaskScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.taskScreen(){
    composable(ROUTE) { TaskScreen() }
}