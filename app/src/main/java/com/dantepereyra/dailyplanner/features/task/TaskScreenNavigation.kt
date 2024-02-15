package com.dantepereyra.dailyplanner.features.task

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dantepereyra.dailyplanner.features.addtask.navigateToAddTaskScreen


val ROUTE="task_screen"
fun NavController.navigateToTaskScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.taskScreen(navController: NavHostController){
    composable(ROUTE) { TaskScreen(navigateToAddTask = {navController.navigateToAddTaskScreen()}) }
}