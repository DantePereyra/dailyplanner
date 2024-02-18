@file:OptIn(ExperimentalMaterial3Api::class)

package com.dantepereyra.dailyplanner

import android.app.Application
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Date
import java.util.Locale
import java.util.Locale.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.dantepereyra.dailyplanner.bd.TaskDBHelper
import com.dantepereyra.dailyplanner.bd.TaskDBScheme
import com.dantepereyra.dailyplanner.domain.Task
import com.dantepereyra.dailyplanner.features.addtask.navigateToAddTaskScreen
import com.dantepereyra.dailyplanner.features.task.TaskRepository
import com.dantepereyra.dailyplanner.features.task.TaskViewModel
import com.dantepereyra.dailyplanner.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DailyPlannerApp(navController = navController)
        }
    }
}

@Composable
fun DailyPlannerApp(navController: NavController) {
    Navigation()
}

