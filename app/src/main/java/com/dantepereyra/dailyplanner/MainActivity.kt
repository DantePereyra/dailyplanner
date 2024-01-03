package com.dantepereyra.dailyplanner

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.dantepereyra.dailyplanner.data.model.Task

class MainActivity : ComponentActivity() {
    private val taskViewModel = TaskViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPlannerApp(taskViewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyPlannerApp(viewModel: TaskViewModel) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Daily Planner") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Hoy")
                    }
                }
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Tarea") }
            )
        }
    ) {
        TaskList(viewModel)
    }
}


@Composable
fun TaskList(viewModel: TaskViewModel) {
    LazyColumn {
        items(viewModel.taskList) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {

}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    val viewModel = TaskViewModel()
    viewModel.taskList.addAll(
        listOf(
            Task(1, "Tarea 1", "Descripción de la tarea 1", "10:00"),
            Task(2, "Tarea 2", "Descripción de la tarea 2", "12:30"),
            Task(3, "Tarea 3", "Descripción de la tarea 3", "15:00")
        )
    )
    DailyPlannerApp(viewModel)
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(Task(1, "Tarea 1", "Descripción de la tarea 1", "10:00"))
}