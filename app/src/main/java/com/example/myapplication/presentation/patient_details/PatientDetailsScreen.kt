package com.example.myapplication.presentation.patient_details

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(
    onBackClicked: () -> Unit,
    onSuccessfulSaving: () -> Unit,
    viewModel: PatientDetailsViewModel = hiltViewModel()

) {
    val focusRequester = remember {FocusRequester()}
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        viewModel.evenFlow.collectLatest{event ->
            when(event){
                PatientDetailViewModel.UiEvent.ShowToast ->{
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    LaunchedEffect(key1 = Unit){
        delay(500)
        focusRequester.requestFocus()
    }

    val state = viewModel.state

    Scaffold(
        topBar = { TopBar(onBackClicked = {}) }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(it)
        ){
            OutlinedTextField(
                modifier = Modifier
                .fillMaxSize(),
                value = "",
                onValueChange = {},
                label = { Text(text = "Name")},
                textStyle = androidx.compose.material.MaterialTheme.typography.body1,
                singleLine = true
            )

        }

    }
    
}

@Composable
fun TopBar(
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Patient's Details Screen",
                style = androidx.compose.material.MaterialTheme.typography.h6
            )
        },
        navigationIcon = {
            IconButton(onClick = {onBackClicked}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back")

            }

        }

    )
}


@Preview
@Composable
fun DetailsScreen() {
    PatientDetailsScreen()
}
