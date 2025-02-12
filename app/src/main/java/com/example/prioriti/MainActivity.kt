package com.example.prioriti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prioriti.ui.theme.PrioritiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrioritiTheme {
                Column(modifier = Modifier.padding(24.dp)) {
                    CreateTask("Clean up trash", "2/12/24")
                }
            }
        }
    }
}

@Composable
fun CreateTask(name: String, date: String) {
    /* Create a task with the given name and date

    Parameters:
    name (String): The name of the task
    date (String): The date or time limit for the task if provided

    Returns: None
     */
    var checked by remember { mutableStateOf(false) }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = checked,
                onCheckedChange = {checked = it}
            )
            Text(
                text = name,
                style = if(checked)TextStyle(textDecoration = TextDecoration.LineThrough)else TextStyle(textDecoration = null)
            )
        }
        Text(
            text = date,
            modifier = Modifier.padding(start = 50.dp),
            style = if(checked)TextStyle(textDecoration = TextDecoration.LineThrough)else TextStyle(textDecoration = null)
        )
    }
}
@Preview
@Composable
fun CreateTaskPreview() {
    PrioritiTheme {
        CreateTask("Task", "Date")
    }
}