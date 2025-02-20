package com.example.prioriti.ui

import androidx.lifecycle.ViewModel
import com.example.prioriti.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    private val tasks: MutableStateFlow<List<Task>>  = MutableStateFlow(listOf())

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow() // returns a readonly object

    fun createTask(name: String) {
        tasks.update { tasks.value + listOf(Task(name)) }
    }
}