package com.dantepereyra.dailyplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dantepereyra.dailyplanner.features.addtask.addTaskScreen
import com.dantepereyra.dailyplanner.features.task.ROUTE_TASK
import com.dantepereyra.dailyplanner.features.task.taskScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE_TASK) {
        taskScreen(navController)
        addTaskScreen(navController)
    }

}