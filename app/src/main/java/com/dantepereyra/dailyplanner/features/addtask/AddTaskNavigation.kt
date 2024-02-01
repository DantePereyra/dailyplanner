package com.dantepereyra.dailyplanner.features.addtask

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

val ROUTE="add_task_screen"
fun NavController.navigateToAddTaskScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.addTaskScreen(){
    composable(ROUTE) { AddTaskScreen() }
}