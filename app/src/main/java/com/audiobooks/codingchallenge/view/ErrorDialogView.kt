package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.audiobooks.codingchallenge.R
import java.lang.Exception

@Composable
fun ErrorAlertDialog(
    isErrorDialogVisible: Boolean,
    onDismiss: () -> Unit,
    exception: Exception
) {
    if (isErrorDialogVisible) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Red
                    )
                    Text(text = stringResource(id = R.string.error_dialog_title))
                }
            },
            text = {
                Text(text = "Error: ${exception.message}")
                Text(text = stringResource(id = R.string.error_dialog_message))
            },
            confirmButton = {
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
}
