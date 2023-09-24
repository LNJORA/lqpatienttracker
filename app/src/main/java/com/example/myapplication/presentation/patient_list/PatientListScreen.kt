package com.example.myapplication.presentation.patient_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListScreen() {

    val patientList = listOf(
        Patient(
            name = "Mohammed Arif",
            age = "25",
            gender = 1,
            doctorAssigned = "Dr. Patel",
            prescription = "Drink at least 8 glasses of water per day"
        ),
        Patient(
            name = "Lucy Natasha",
            age = "34",
            gender = 2,
            doctorAssigned = "Dr. Green",
            prescription = "Get at least 7 hours of sleep",
        )

    )
    Scaffold(
        topBar = {},
        floatingActionButton = {
            ListFab(onFabClicked = {})

        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(patientList) { patient ->
                PatientItem(
                    patient = patient,
                    onItemClicked = {},
                    onDeleteConfirm = {},
                )

            }

        }
        if (patientList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add Patients Details\nby pressing add button",
                    style = androidx.compose.material.MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )


            }

        }
    }

}

@Composable
fun ListAppBar() {
    TopAppBar(
        title = {
            Text(text = "Patient Tracker",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    )

}

@Composable
fun ListFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(onClick = onFabClicked) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Patient Button")

    }
}