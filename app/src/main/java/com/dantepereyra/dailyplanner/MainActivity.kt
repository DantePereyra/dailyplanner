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
import com.dantepereyra.dailyplanner.features.task.Task
import com.dantepereyra.dailyplanner.features.task.TaskRepository
import com.dantepereyra.dailyplanner.features.task.TaskViewModel
import com.dantepereyra.dailyplanner.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbHelper = TaskDBHelper(this)
        val writableDB = dbHelper.readableDatabase
        writableDB.execSQL(
            """
            INSERT INTO ${TaskDBScheme.TABLE_NAME}
            VALUES ('Cocinar','Preparar estofado verduras')
        """.trimIndent()
        )


        setContent {
            val navController = rememberNavController()
            DailyPlannerApp(navController = navController)
        }
    }
}

@Preview
@Composable
fun DailyPlannerApp(navController: NavController) {
    val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())

    Scaffold(
        topBar = {
            DailyTopAppBar()
        },
        bottomBar = {
            DailyBottomAppBar(currentDate)
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigateToAddTaskScreen() },
                content = { Icon(Icons.Filled.Add, contentDescription = "Add") }
            )
        }
    ) { innerPadding ->
        Box() {
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(modifier = Modifier.padding(innerPadding))
            }
        }
        BackgroundImage()
    }

}


@Composable
fun DailyTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = "Daily Planner") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.DateRange, contentDescription = "Calendar")
            }
        }
    )
}

@Composable
fun DailyBottomAppBar(currentDate: String) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {

        Text(
            text = currentDate,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


@Composable
fun BackgroundImage() {
    val image = painterResource(id = R.drawable.background)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.3f)
    )
}


@HiltAndroidApp
class DailyPlannerAplication : Application()