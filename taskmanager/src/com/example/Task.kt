package com.example

import java.io.Serializable

// Enum class representing task priority levels
enum class Priority : Serializable {
    LOW, MEDIUM, HIGH
}

// Data class representing a task
data class Task(val id: Int, var description: String, var priority: Priority, var isCompleted: Boolean = false) : Serializable {
    // Function to display the task in a user-friendly format
    override fun toString(): String {
        val status = if (isCompleted) "☑" else "☐"
        return "$id. $description - Priority: $priority - $status"
    }
}