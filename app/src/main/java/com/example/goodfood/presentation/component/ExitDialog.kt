package com.example.goodfood.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExitDialog(onDismiss: () -> Unit, onSubmit: () -> Unit, dialogOpen: Boolean) {
    if (dialogOpen) {
        AlertDialog(onDismissRequest = onDismiss, text = {
            Text(text = "Do You Really Want To Exit ?")
        }, dismissButton = {
            OutlinedButton(
                onClick = onDismiss, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancel", color = Color.White)
            }
        }, confirmButton = {
            OutlinedButton(
                onClick = onSubmit, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Confirm", color = Color.White)
            }

        })
    }
}