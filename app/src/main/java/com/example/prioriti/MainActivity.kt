package com.example.prioriti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
    // val mainUiState by mainViewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    LazyColumn(modifier = modifier.padding(24.dp)) {
        items(mainViewModel.tasks.value) { task ->
            CreateTask(task)
        }
        item{
            Button(onClick = {showDialog = true}, modifier = Modifier.padding(horizontal = 100.dp, vertical = 100.dp)) {
                Text("Open Dialog")
            }
        }
    }
    if (showDialog) {
        CreteTaskDialog(onDismiss = { showDialog = false }, mainViewModel= mainViewModel)
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

@Composable
fun CreteTaskDialog(onDismiss: () -> Unit, mainViewModel: MainViewModel = MainViewModel()) {
    var text by remember { mutableStateOf("") }
    var text1 by remember { mutableStateOf("") }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Row(Modifier.padding(5.dp)) {
                        Button(
                            modifier = Modifier.padding(5.dp),
                            onClick = { onDismiss() }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                        Text(text = "Add a Task",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(5.dp)
                        )
                        Spacer(Modifier.weight(1f))
                    }
                    TextField(
                        value = text,
                        onValueChange = {text = it},
                        label = { Text("Task Name")},
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                    TextField(
                        value = text1,
                        onValueChange = {text1 = it},
                        label = { Text("Task Date")},
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                    Row{
                        TextButton(
                            onClick = { mainViewModel.createTask(text); onDismiss() },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color(0,75,0)
                            ),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = "Save")
                        }
                        Spacer(Modifier.weight(1f))
                        TextButton(
                            onClick = { onDismiss()},
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Red
                            ),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }

            }
        }
    }
}