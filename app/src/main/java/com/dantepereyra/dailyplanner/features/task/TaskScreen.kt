package com.dantepereyra.dailyplanner.features.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dantepereyra.dailyplanner.domain.Task

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navigateToAddTask: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    TaskContent(
        state.value,
        navigateToAddTask = navigateToAddTask
    )
}

@Composable
fun TaskContent(
    state: List<Task> = emptyList(),
    navigateToAddTask: () -> Unit
) {
    Column {

        LazyColumn {
            items(state) { task ->
                Task(task)
            }
        }
    }
}

@Composable
fun Task(task: Task) {
    Row {

        Text(
            text = task.tittle,
            fontSize = 20.sp,

            )
    }
}
