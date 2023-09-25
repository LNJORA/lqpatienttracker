package com.example.myapplication.presentation.patient_details

import android.widget.RadioGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(
    onBackClick: () -> Unit,
    onSuccessfulSaving: () -> Unit,
    viewModel: PatientDetailsViewModel = hiltViewModel()

) {
    val focusRequester = remember {FocusRequester()}
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        viewModel.eventFlow.collectLatest{ event ->
            when(event){
                PatientDetailsViewModel.UiEvent.SaveNote -> {
                    onSuccessfulSaving()
                    Toast.makeText(context,"Successfully Saved", Toast.LENGTH_SHORT).show()
                }
                is PatientDetailsViewModel.UiEvent.ShowToast -> {
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
        topBar = {
            TopBar(onBackClicked = onBackClick)
        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(it)
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester),
                value = state.name,
                onValueChange = {newValue ->
                    viewModel.onAction(PatientDetailsEvent.EnteredName(newValue))
                },
                label = { Text(text = "Name")},
                textStyle = androidx.compose.material.MaterialTheme.typography.body1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Next)}
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = state.age,
                    onValueChange = {newValue ->
                        viewModel.onAction(PatientDetailsEvent.EnteredAge(newValue))
                    },
                    label = { Text(text = "Age")},
                    textStyle = androidx.compose.material.MaterialTheme.typography.body1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                       onNext = {focusManager.moveFocus(FocusDirection.Next)}
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                RadioGroup(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Male",
                    selected = state.gender == 1,
                    onClick ={viewModel.onAction(PatientDetailsEvent.SelectedMale)}
                )
                RadioGroup(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Female",
                    selected = state.gender == 2,
                    onClick = {viewModel.onAction(PatientDetailsEvent.SelectedFemale)}
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.doctorAssigned,
                onValueChange = { newValue ->
                    viewModel.onAction(PatientDetailsEvent.EnteredAssignedDoctor(newValue))
                },
                label = { androidx.compose.material.Text(text = "Assigned Doctor's Name") },
                textStyle = androidx.compose.material.MaterialTheme.typography.body1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = state.prescription,
                onValueChange = { newValue ->
                    viewModel.onAction(PatientDetailsEvent.EnteredPrescription(newValue))
                },
                label = { androidx.compose.material.Text(text = "Prescription") },
                textStyle = androidx.compose.material.MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onAction(PatientDetailsEvent.SaveButton) }
            ) {
                androidx.compose.material.Text(
                    text = "Save",
                    style = androidx.compose.material.MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
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
                    contentDescription = "Back"
                )

            }

        }

    )
}

@Composable
private fun RadioGroup(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
){
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = androidx.compose.material.MaterialTheme.colors.primary
            )
        )
        androidx.compose.material.Text(
            text = text,
            style = androidx.compose.material.MaterialTheme.typography.body1
        )
    }
}



