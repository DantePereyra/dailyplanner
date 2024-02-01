package com.dantepereyra.dailyplanner.features.addtask

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dantepereyra.dailyplanner.BackgroundImage
import com.dantepereyra.dailyplanner.DailyBottomAppBar
import com.dantepereyra.dailyplanner.DailyTopAppBar
import com.dantepereyra.dailyplanner.navigation.Navigation
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun AddTaskScreen() {

    Scaffold(
        topBar = {
            DailyTopAppBar()
        },
        bottomBar = {
            AddTaskBottomAppBar()
        },

        ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {

            BackgroundImage()
        }

    }
}

@Composable
fun AddTaskBottomAppBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {

        Text(
            text = "Pruebas",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
