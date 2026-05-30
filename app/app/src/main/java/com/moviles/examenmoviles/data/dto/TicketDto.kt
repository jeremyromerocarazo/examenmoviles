package com.moviles.examenmoviles.data.dto

// DTO — lo que vendría del backend (preparado para Retrofit)
data class TicketDto(
    val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val supplier: String,
    val category: String,
    val createdAt: String
)

data class CreateTicketRequest(
    val title: String,
    val description: String,
    val priority: String,
    val supplier: String,
    val category: String
)

data class UpdateTicketStatusRequest(
    val status: String
)

data class UpdateTicketPriorityRequest(
    val priority: String
)