@file:OptIn(ExperimentalMaterial3Api::class)

package com.dantepereyra.dailyplanner.features.addtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dantepereyra.dailyplanner.BackgroundImage
import com.dantepereyra.dailyplanner.DailyBottomAppBar
import com.dantepereyra.dailyplanner.DailyTopAppBar
import com.dantepereyra.dailyplanner.domain.Task
import com.dantepereyra.dailyplanner.features.task.currentDate

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddTaskScreen(viewModel: AddTaskViewmodel = hiltViewModel(),
        navigateToTaskScren: () -> Unit
) {
    AddTaskContent(
        addTask = {
            viewModel.saveTask(it)
        },
        navigateToTaskScren = navigateToTaskScren
    )
}

@Composable
fun AddTaskContent(
    addTask: (Task) -> Unit,
    navigateToTaskScren: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            DailyTopAppBar()
        },
        bottomBar = {
            DailyBottomAppBar(currentDate)
        },

        ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                TextField(
                    value = name ,
                    onValueChange = { name = it },
                    placeholder = { Text("Nombre") }
                )


                TextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Descripción") }
                )

                // Botón para agregar la tarea
                Button(onClick = {

                    addTask(Task(name.text,description.text))
                    name = TextFieldValue()
                    description = TextFieldValue()
                    navigateToTaskScren()

                }) {
                    Text(text = "Agregar tarea")
                }
            }
        }
        BackgroundImage()
    }

}
