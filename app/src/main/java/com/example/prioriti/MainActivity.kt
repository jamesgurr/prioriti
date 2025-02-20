package com.example.prioriti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.prioriti.model.Task
import com.example.prioriti.ui.MainUiState
import com.example.prioriti.ui.MainViewModel
import com.example.prioriti.ui.theme.PrioritiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrioritiTheme {
                TaskApp(mainViewModel = MainViewModel(listOf(Task("Do Laundry"), Task("Eat garbage"))))
            }
        }
    }
}

@Composable
fun TaskApp(mainViewModel: MainViewModel = MainViewModel(), modifier: Modifier = Modifier) {
    val mainUiState by mainViewModel.uiState.collectAsState()

    LazyColumn(modifier = modifier) {
        items(mainViewModel.tasks.value) { task ->
            CreateTask(task)
        }

        item {
            Button(onClick = { mainViewModel.createTask("Seventeen")} ) {

        }
        }
    }
}

@Composable
fun CreateTask(task: Task) {
    val name = task.name
    val date = task.date.toString()
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
        TaskApp(mainViewModel = MainViewModel(listOf(Task("Do Laundry"), Task("Eat garbage"))))
    }
}