package com.dantepereyra.dailyplanner.features.addtask

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dantepereyra.dailyplanner.BackgroundImage
import com.dantepereyra.dailyplanner.DailyBottomAppBar
import com.dantepereyra.dailyplanner.DailyTopAppBar
import com.dantepereyra.dailyplanner.domain.Task
import com.dantepereyra.dailyplanner.navigation.Navigation
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddTaskScreen(viewModel: AddTaskViewmodel = hiltViewModel()) {
    AddTaskScreen(
        addTask = {
            viewModel.saveTask(it)
        }
    )
}

@Composable
fun AddTaskScreen(   addTask: (Task) -> Unit
) {
    val task = Task(
        tittle="Lavar", description = "Poner lavadora y secadora"
    )
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = { addTask(task) }) {
            Text(text = "Add task")

        }
    }
}
