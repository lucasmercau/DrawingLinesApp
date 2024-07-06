package com.example

import java.io.*
import java.util.*

// com.example.TaskManager.kt

// Class managing the list of tasks
class TaskManager {
    private var nextId = 1 // Variable to generate unique task IDs
    private val tasks = mutableListOf<Task>() // List to store tasks
    private val fileName = "tasks.ser" // File to store tasks

    init {
        loadTasks() // Load tasks from file when TaskManager is initialized
    }
    // Function to get a valid priority from user input
    private fun getValidPriority(): Priority? {
        while (true) {
            print("Enter task priority (LOW, MEDIUM, HIGH): ")
            val input = readlnOrNull()?.uppercase(Locale.getDefault())
            if (input != null) {
                try {
                    return Priority.valueOf(input)
                } catch (e: IllegalArgumentException) {
                    println("Invalid priority. Please enter LOW, MEDIUM, or HIGH.")
                }
            } else {
                return null
            }
        }
    }

    // Function to add a new task
    fun addTask() {
        print("Enter task description: ")
        val description = readlnOrNull()?.trim() // Read task description and trim whitespace
        if (description.isNullOrEmpty()) {
            println("Task description cannot be empty.")
            return
        }
        // Read and validate task priority
        val priority: Priority = getValidPriority() ?: return

        val task = Task(nextId++, description, priority) // Create a new task
        tasks.add(task) // Add the task to the list
        saveTasks() // Save tasks to file
        println("Task added: $task")
    }

    // Function to remove a task
    fun removeTask() {
        if (tasks.isEmpty()) {
            println("No tasks available")
        } else {
            println("Tasks:")
            for (task in tasks) {
                println(task) // Print each task
            }
            print("Enter task id to remove: ")
            val id = readlnOrNull()?.toIntOrNull() ?: return // Read task ID
            val task = tasks.find { it.id == id } // Find the task by ID
            if (task != null) {
                tasks.remove(task) // Remove the task if found
                saveTasks() // Save tasks to file
                println("Task removed: $task")
            } else {
                println("Task not found")
            }
        }
    }

    // Function to list all tasks
    fun listTasks() {
        if (tasks.isEmpty()) {
            println("No tasks available")
        } else {
            println("Tasks:")
            for (task in tasks) {
                println(task) // Print each task
            }
        }
    }

    // Function to mark a task as completed
    fun markTaskAsCompleted() {
        if (tasks.isEmpty()) {
            println("No tasks available")
        } else {
            println("Tasks:")
            for (task in tasks) {
                println(task) // Print each task
            }
            print("Enter task id to mark as completed: ")
            val id = readLine()?.toIntOrNull() ?: return // Read task ID
            val task = tasks.find { it.id == id } // Find the task by ID
            if (task != null) {
                task.isCompleted = true // Mark the task as completed
                saveTasks() // Save tasks to file
                println("Task marked as completed: $task")
            } else {
                println("Task not found")
            }
        }
    }

    // Function to filter tasks by their completion status
    fun filterTasksByCompletionStatus() {
        print("Enter completion status (completed/incomplete): ")
        val status = readlnOrNull()?.lowercase(Locale.getDefault()) // Read completion status
        val filteredTasks = when (status) {
            "completed" -> tasks.filter { it.isCompleted } // Filter completed tasks
            "incomplete" -> tasks.filter { !it.isCompleted } // Filter incomplete tasks
            else -> {
                println("Invalid status")
                return
            }
        }
        if (filteredTasks.isEmpty()) {
            println("No tasks found for the given status")
        } else {
            println("Filtered Tasks:")
            for (task in filteredTasks) {
                println(task) // Print each filtered task
            }
        }
    }

    // Function to save tasks to a file
    private fun saveTasks() {
        try {
            ObjectOutputStream(FileOutputStream(fileName)).use { it.writeObject(tasks) }
        } catch (e: IOException) {
            println("Error saving tasks: ${e.message}")
        }
    }

    // Function to load tasks from a file
    private fun loadTasks() {
        try {
            val file = File(fileName)
            if (file.exists()) {
                ObjectInputStream(FileInputStream(fileName)).use {
                    val loadedTasks = it.readObject() as List<Task>
                    tasks.addAll(loadedTasks)
                    if (tasks.isNotEmpty()) {
                        nextId = tasks.maxOf { it.id } + 1
                    }
                }
            }
        } catch (e: IOException) {
            println("Error loading tasks: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("Error loading tasks: ${e.message}")
        }
    }
}