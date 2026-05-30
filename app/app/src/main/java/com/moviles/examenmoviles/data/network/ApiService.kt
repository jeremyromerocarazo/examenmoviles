package com.moviles.examenmoviles.data.network

import com.moviles.examenmoviles.data.dto.CreateTicketRequest
import com.moviles.examenmoviles.data.dto.TicketDto
import com.moviles.examenmoviles.data.dto.UpdateTicketPriorityRequest
import com.moviles.examenmoviles.data.dto.UpdateTicketStatusRequest
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @GET("tickets")
    suspend fun getTickets(): Response<List<TicketDto>>

    @GET("tickets/{id}")
    suspend fun getTicketById(@Path("id") id: Int): Response<TicketDto>

    @POST("tickets")
    suspend fun createTicket(@Body request: CreateTicketRequest): Response<TicketDto>

    @PUT("tickets/{id}/status")
    suspend fun updateTicketStatus(
        @Path("id") id: Int,
        @Body request: UpdateTicketStatusRequest
    ): Response<TicketDto>

    @PUT("tickets/{id}/priority")
    suspend fun updateTicketPriority(
        @Path("id") id: Int,
        @Body request: UpdateTicketPriorityRequest
    ): Response<TicketDto>
}