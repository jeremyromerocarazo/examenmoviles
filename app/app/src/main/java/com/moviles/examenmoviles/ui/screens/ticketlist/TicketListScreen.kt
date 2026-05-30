package com.moviles.examenmoviles.ui.screens.ticketlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moviles.examenmoviles.domain.model.*
import com.moviles.examenmoviles.feature.FeatureFlags
import com.moviles.examenmoviles.ui.components.BottomBar
import com.moviles.examenmoviles.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(
    onTicketClick: (Ticket) -> Unit,
    onCreateClick: () -> Unit,
    onFlagsClick: () -> Unit,
    viewModel: TicketListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val canCreate by FeatureFlags.canCreateTicket.collectAsState()

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Support Tickets",
                        style = MaterialTheme.typography.headlineMedium)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBackground
                )
            )
        },
        floatingActionButton = {
            // FAB solo visible si el flag está activo
            if (canCreate) {
                FloatingActionButton(
                    onClick = onCreateClick,
                    containerColor = AppPrimary,
                    contentColor = AppBackground
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Create Ticket")
                }
            }
        },
        bottomBar = {
            BottomBar(
                currentRoute = "ticket_list",
                onHomeClick = { },
                onFlagsClick = onFlagsClick,
                onProfileClick = { }
            )
        }
    ) { padding ->
        when (uiState) {
            is TicketListUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = AppPrimary)
                }
            }
            is TicketListUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (uiState as TicketListUiState.Error).message,
                        color = AppError
                    )
                }
            }
            is TicketListUiState.Success -> {
                val tickets = (uiState as TicketListUiState.Success).tickets
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(
                        start = 14.dp, end = 14.dp,
                        top = 12.dp, bottom = 96.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(tickets, key = { it.id }) { ticket ->
                        TicketCard(
                            ticket = ticket,
                            onClick = { onTicketClick(ticket) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TicketCard(ticket: Ticket, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, AppBorder),
        colors = CardDefaults.cardColors(containerColor = AppSurface),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            // Título + badge prioridad
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = ticket.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = AppPrimary,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                PriorityBadge(priority = ticket.priority)
            }

            // Estado
            StatusBadge(status = ticket.status)

            // Proveedor
            Text(
                text = ticket.supplier,
                style = MaterialTheme.typography.bodyMedium,
                color = AppSecondaryText
            )
        }
    }
}

@Composable
fun PriorityBadge(priority: Priority) {
    val color = when (priority) {
        Priority.HIGH -> PriorityHigh
        Priority.MEDIUM -> PriorityMedium
        Priority.LOW -> PriorityLow
    }
    Surface(shape = RoundedCornerShape(50.dp), color = color) {
        Text(
            text = priority.name,
            style = MaterialTheme.typography.bodyMedium,
            color = AppBackground,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun StatusBadge(status: Status) {
    val color = when (status) {
        Status.OPEN -> StatusOpen
        Status.IN_PROGRESS -> StatusInProgress
        Status.RESOLVED -> StatusResolved
        Status.CLOSED -> StatusClosed
    }
    Surface(shape = RoundedCornerShape(50.dp), color = color.copy(alpha = 0.12f)) {
        Text(
            text = status.name.replace("_", " "),
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )
    }
}