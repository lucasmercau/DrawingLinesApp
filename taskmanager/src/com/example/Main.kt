package com.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Main.kt

// Entry point of the application
fun main() {
    val taskManager = TaskManager() // Create an instance of com.example.TaskManager
    while (true) {
        // Display menu options
        println("\nTask Manager")
        println("1. Add Task")
        println("2. Remove Task")
        println("3. List Tasks")
        println("4. Mark Task as Completed")
        println("5. Filter Tasks by Completion Status")
        println("6. Exit")
        print("Choose an option: ")

        // Read user input and call the corresponding function
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> taskManager.addTask() // Add a new task
            2 -> taskManager.removeTask() // Remove an existing task
            3 -> taskManager.listTasks() // List all tasks
            4 -> taskManager.markTaskAsCompleted() // Mark a task as completed
            5 -> taskManager.filterTasksByCompletionStatus() // Filter tasks by their completion status
            6 -> {
                println("Exiting...")
                break // Exit the application
            }
            else -> println("Invalid option. Try again.") // Handle invalid input
        }
    }
}
