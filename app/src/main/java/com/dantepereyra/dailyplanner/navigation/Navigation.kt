package com.dantepereyra.dailyplanner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dantepereyra.dailyplanner.features.addtask.addTaskScreen
import com.dantepereyra.dailyplanner.features.task.ROUTE
import com.dantepereyra.dailyplanner.features.task.taskScreen


@Composable
fun Navigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE) {
        taskScreen()
        addTaskScreen()
    }

}