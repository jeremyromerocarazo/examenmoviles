package com.moviles.examenmoviles.ui.screens.ticketdetail


import androidx.lifecycle.ViewModel
import com.moviles.examenmoviles.domain.model.Status
import com.moviles.examenmoviles.domain.model.Ticket

class TicketDetailViewModel : ViewModel() {
    // Recibe el ticket y expone el estado actualizado
    private var _ticket: Ticket? = null
    val ticket get() = _ticket

    fun setTicket(ticket: Ticket) {
        _ticket = ticket
    }
}