package com.moviles.examenmoviles.feature

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Feature Flags — controlan funcionalidades sin tocar el resto del código
object FeatureFlags {

    // Flag 1 — habilita o deshabilita la creación de nuevos tickets
    private val _canCreateTicket = MutableStateFlow(true)
    val canCreateTicket: StateFlow<Boolean> = _canCreateTicket

    // Flag 2 — habilita o deshabilita cambio de prioridad de tickets
    private val _canUpdatePriority = MutableStateFlow(true)
    val canUpdatePriority: StateFlow<Boolean> = _canUpdatePriority

    // Flag 3 — muestra o esconde tickets ya resueltos en la lista
    private val _showResolvedTickets = MutableStateFlow(true)
    val showResolvedTickets: StateFlow<Boolean> = _showResolvedTickets

    // Funciones para cambiar los flags desde la pantalla de FeatureFlags
    fun toggleCreateTicket() {
        _canCreateTicket.value = !_canCreateTicket.value
    }

    fun toggleUpdatePriority() {
        _canUpdatePriority.value = !_canUpdatePriority.value
    }

    fun toggleShowResolved() {
        _showResolvedTickets.value = !_showResolvedTickets.value
    }
}