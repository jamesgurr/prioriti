package com.example.prioriti.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.prioriti.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(tasks: List<Task> = listOf()): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    var tasks = mutableStateOf<List<Task>>(listOf())
        //MutableStateFlow(tasks)

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow() // returns a readonly object

    fun createTask(name: String) {
//        tasks.update { tasks.value + listOf(Task(name)) }
        tasks.value += Task(name)
    }
}