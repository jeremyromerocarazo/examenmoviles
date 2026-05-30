package com.moviles.examenmoviles.feature

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


object FeatureFlags {


    private val _canCreateTicket = MutableStateFlow(true)
    val canCreateTicket: StateFlow<Boolean> = _canCreateTicket


    private val _canUpdatePriority = MutableStateFlow(true)
    val canUpdatePriority: StateFlow<Boolean> = _canUpdatePriority


    private val _showResolvedTickets = MutableStateFlow(true)
    val showResolvedTickets: StateFlow<Boolean> = _showResolvedTickets


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