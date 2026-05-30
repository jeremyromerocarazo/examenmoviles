package com.moviles.examenmoviles.ui.screens.ticketdetail


import androidx.lifecycle.ViewModel
import com.moviles.examenmoviles.domain.model.Status
import com.moviles.examenmoviles.domain.model.Ticket

class TicketDetailViewModel : ViewModel() {

    private var _ticket: Ticket? = null
    val ticket get() = _ticket

    fun setTicket(ticket: Ticket) {
        _ticket = ticket
    }
}