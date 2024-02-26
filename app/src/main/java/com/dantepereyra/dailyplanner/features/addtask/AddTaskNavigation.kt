package com.dantepereyra.dailyplanner.features.addtask

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dantepereyra.dailyplanner.features.task.navigateToTaskScreen

val ROUTE_ADD_TASK = "add_task_screen"
fun NavController.navigateToAddTaskScreen() {
    navigate(ROUTE_ADD_TASK){

    }
}

fun NavGraphBuilder.addTaskScreen(navController: NavHostController) {
    composable(ROUTE_ADD_TASK) {
        AddTaskScreen(navigateToTaskScren = { navController.navigateToTaskScreen() }) }
}