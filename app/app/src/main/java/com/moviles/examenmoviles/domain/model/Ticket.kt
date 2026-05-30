package com.moviles.examenmoviles.domain.model

// Modelo de dominio — lo que usa la UI
data class Ticket(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: Status,
    val supplier: String,
    val category: Category,
    val createdAt: String
)

enum class Priority {
    HIGH, MEDIUM, LOW
}

enum class Status {
    OPEN, IN_PROGRESS, RESOLVED, CLOSED
}

enum class Category {
    INVENTORY, DISTRIBUTION, LOGISTICS, SUPPLIER, OTHER
}