package com.example.prioriti.model
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime

data class Task (
    val name: String,
    val date: Instant = Clock.System.now(),
    val isChecked: Boolean
)