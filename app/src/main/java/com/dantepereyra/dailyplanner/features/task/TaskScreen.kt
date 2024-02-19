@file:OptIn(ExperimentalMaterial3Api::class)

package com.dantepereyra.dailyplanner.features.task

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dantepereyra.dailyplanner.R
import com.dantepereyra.dailyplanner.domain.Task
import java.util.Date
import java.util.Locale

val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navigateToAddTask: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    val onCompletedClick: (Task) -> Unit = { task ->
        viewModel.markTaskAsCompleted(task)
    }
    val onDeleteClick: (Task) -> Unit = { task ->
        viewModel.deleteTask(task)
    }
    val onEditClick: (Task) -> Unit = { task ->
        viewModel.editTask(task)
    }
    TaskContent(
        state.value,
        navigateToAddTask = navigateToAddTask,
        onCompletedClick = onCompletedClick,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick

    )
}

@Composable
fun TaskContent(
    state: List<Task> = emptyList(),
    navigateToAddTask: () -> Unit,
    onCompletedClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    onEditClick: (Task) -> Unit
) {


    Scaffold(
        topBar = {
            DailyTopAppBar()
        },
        bottomBar = {
            DailyBottomAppBar(currentDate)
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddTask() },
                content = { Icon(Icons.Filled.Add, contentDescription = "Add") }
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            TasksList(
                tasks = state,
                onCompletedClick = onCompletedClick,
                onDeleteClick = onDeleteClick,
                onEditClick = onEditClick
            )
        }
        BackgroundImage()
    }
}

@Composable
fun TasksList(
    tasks: List<Task>,
    onCompletedClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    onEditClick: (Task) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(
                task = task,
                onCompletedClick = onCompletedClick,
                onDeleteClick = onDeleteClick,
                onEditClick = onEditClick
            )
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCompletedClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    onEditClick: (Task) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = task.description)

        Row {
            IconButton(onClick = { onCompletedClick(task) }) {
                Icon(
                    imageVector = if (task.isCompleted) Icons.Filled.Check else Icons.Filled.Clear,
                    contentDescription = "Completed"
                )
            }
            IconButton(onClick = { onEditClick(task) }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = { onDeleteClick(task) }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }

        }
    }
}


@Composable
fun DailyTopAppBar() {
    val showDialog = remember { mutableStateOf(false) }
    showDialog.value = true
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
            IconButton(onClick = {          }) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Cat Fact")
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

