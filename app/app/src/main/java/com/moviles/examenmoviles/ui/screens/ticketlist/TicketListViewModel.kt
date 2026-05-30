package com.moviles.examenmoviles.ui.screens.ticketlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.examenmoviles.data.mock.MockTickets
import com.moviles.examenmoviles.domain.model.Priority
import com.moviles.examenmoviles.domain.model.Status
import com.moviles.examenmoviles.domain.model.Ticket
import com.moviles.examenmoviles.feature.FeatureFlags
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Estados posibles de la pantalla
sealed class TicketListUiState {
    object Loading : TicketListUiState()
    data class Success(val tickets: List<Ticket>) : TicketListUiState()
    data class Error(val message: String) : TicketListUiState()
}

class TicketListViewModel : ViewModel() {

    // Estado de la UI
    private val _uiState = MutableStateFlow<TicketListUiState>(TicketListUiState.Loading)
    val uiState: StateFlow<TicketListUiState> = _uiState

    // Evento — notifica cuando un ticket fue creado o actualizado
    private val _ticketEvent = MutableSharedFlow<TicketEvent>()
    val ticketEvent: SharedFlow<TicketEvent> = _ticketEvent

    init {
        loadTickets()

        // Reacciona al flag de tickets resueltos
        viewModelScope.launch {
            FeatureFlags.showResolvedTickets.collect {
                loadTickets()
            }
        }
    }

    fun loadTickets() {
        viewModelScope.launch {
            _uiState.value = TicketListUiState.Loading
            try {
                val tickets = getFilteredTickets()
                _uiState.value = TicketListUiState.Success(tickets)
            } catch (e: Exception) {
                _uiState.value = TicketListUiState.Error("Failed to load tickets")
            }
        }
    }

    // Escenario 1 — nuevo ticket se refleja inmediatamente
    fun onTicketCreated(ticket: Ticket) {
        MockTickets.tickets.add(0, ticket)
        loadTickets()
        viewModelScope.launch {
            _ticketEvent.emit(TicketEvent.TicketCreated(ticket))
        }
    }

    // Escenario 2 — cambio de prioridad reordena la lista automáticamente
    fun onPriorityUpdated(ticketId: Int, newPriority: Priority) {
        val index = MockTickets.tickets.indexOfFirst { it.id == ticketId }
        if (index != -1) {
            MockTickets.tickets[index] = MockTickets.tickets[index].copy(priority = newPriority)
            loadTickets()
            viewModelScope.launch {
                _ticketEvent.emit(TicketEvent.PriorityUpdated(ticketId, newPriority))
            }
        }
    }

    fun onStatusUpdated(ticketId: Int, newStatus: Status) {
        val index = MockTickets.tickets.indexOfFirst { it.id == ticketId }
        if (index != -1) {
            MockTickets.tickets[index] = MockTickets.tickets[index].copy(status = newStatus)
            loadTickets()
        }
    }

    private fun getFilteredTickets(): List<Ticket> {
        val showResolved = FeatureFlags.showResolvedTickets.value
        return MockTickets.tickets
            .filter { if (!showResolved) it.status != Status.RESOLVED else true }
            .sortedWith(
                compareBy {
                    when (it.priority) {
                        Priority.HIGH -> 0
                        Priority.MEDIUM -> 1
                        Priority.LOW -> 2
                    }
                }
            )
    }
}

// Eventos que puede emitir el ViewModel
sealed class TicketEvent {
    data class TicketCreated(val ticket: Ticket) : TicketEvent()
    data class PriorityUpdated(val ticketId: Int, val priority: Priority) : TicketEvent()
}