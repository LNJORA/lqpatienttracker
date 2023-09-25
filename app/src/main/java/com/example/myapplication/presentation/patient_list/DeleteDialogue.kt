package com.example.myapplication.presentation.patient_list

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialogue(
    title:String,
    message:String,
    onDialogDismiss: () -> Unit,
    onConfirmButtonClicked: () -> Unit,
) {
    AlertDialog(
        title={
              Text(text =title,
                   style = MaterialTheme.typography.h6
              )
        },
        text = {
               Text(
                   text = message,
                   style = MaterialTheme.typography.body1
               )

        },
        onDismissRequest = {onDialogDismiss()},
        confirmButton = { TextButton(onClick = {onConfirmButtonClicked}) {
                              Text(text = "Yes")
        }
        },
        dismissButton = {
            TextButton(onClick = {onDialogDismiss}) {
                Text(text = "No")
                
            }
        },
        onDismissRequest = onDialogDismiss
    )
}