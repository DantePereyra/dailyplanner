@file:OptIn(ExperimentalMaterial3Api::class)

package com.dantepereyra.dailyplanner

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dantepereyra.dailyplanner.data.model.Task
import java.util.Date
import java.util.Locale
import java.util.Locale.*

class MainActivity : ComponentActivity() {
    private val taskViewModel = TaskViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPlannerApp(taskViewModel)
        }
    }
}

@Composable
fun DailyPlannerApp(viewModel: TaskViewModel) {
    Scaffold(viewModel = viewModel)
}

@Composable
fun Scaffold(viewModel: TaskViewModel) {
    val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())

    Scaffold(
        topBar = {
            TopAppBar()
        },
        bottomBar = {
            BottomAppBar(currentDate)
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                content = { Icon(Icons.Filled.Add, contentDescription = "Add") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)

            ) {
                TaskList(viewModel = viewModel)
            }

            BackgroundImage()
        }

    }
}

@Composable
fun TopAppBar() {
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
fun BottomAppBar(currentDate: String) {
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

/*@Composable
fun AddTask(){
    var expanded by remember { mutableStateOf(false) }
    val menuOptions = listOf("Add Task", "Add Event")
    val anchorPosition = remember { mutableStateOf<IntOffset?>(null) }
    Button(
        onClick = {
            expanded = true
            anchorPosition.value = IntOffset(0, 0)
        }) {
        Text(text =)

    }
}*/

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

@Composable
fun TaskList(viewModel: TaskViewModel) {
    LazyColumn(

    ) {
        items(viewModel.taskList) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Surface(
        modifier = Modifier.padding(8.dp),
        color = MaterialTheme.colorScheme.surface,
            ) {
        Text(
            text = "${task.title}, ${task.description}, ${task.duelTime}",
            fontSize = 20.sp,

        )
    }
}


@Preview(showBackground = true)
@Composable
fun DailyPlannerAppPreview() {
    val viewModel = TaskViewModel()
    viewModel.taskList.addAll(
        listOf(
            Task(1, "Tarea 1", "Descripción de la tarea 1", "10:00"),
            Task(2, "Tarea 2", "Descripción de la tarea 2", "12:30"),
            Task(3, "Tarea 3", "Descripción de la tarea 3", "15:00")
        )
    )
    //No consigo que se muestren las tareas en el preview. Solo muestra la ultima guardada
    DailyPlannerApp(viewModel)
}

@Preview(showBackground = true)
@Composable
fun TaskList() {
    val viewModel = TaskViewModel()
    viewModel.taskList.addAll(
        listOf(
            Task(1, "Tarea 1", "Descripción de la tarea 1", "10:00"),
            Task(2, "Tarea 2", "Descripción de la tarea 2", "12:30"),
            Task(3, "Tarea 3", "Descripción de la tarea 3", "15:00")
        )
    )
    LazyColumn {
        items(viewModel.taskList) { task ->
            TaskItem(task = task)
        }
    }
}




