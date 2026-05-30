package com.moviles.examenmoviles.ui.screens.ticketdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.domain.model.*
import com.moviles.examenmoviles.feature.FeatureFlags
import com.moviles.examenmoviles.ui.components.AppButton
import com.moviles.examenmoviles.ui.screens.ticketlist.PriorityBadge
import com.moviles.examenmoviles.ui.screens.ticketlist.StatusBadge
import com.moviles.examenmoviles.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(
    ticket: Ticket,
    onBackClick: () -> Unit,
    onStatusUpdated: (Int, Status) -> Unit,
    onPriorityUpdated: (Int, Priority) -> Unit
) {
    val canUpdatePriority by FeatureFlags.canUpdatePriority.collectAsState()
    var showStatusDialog by remember { mutableStateOf(false) }
    var showPriorityDialog by remember { mutableStateOf(false) }

    // Diálogo para cambiar estado
    if (showStatusDialog) {
        AlertDialog(
            onDismissRequest = { showStatusDialog = false },
            title = { Text("Update Status") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Status.entries.forEach { status ->
                        TextButton(onClick = {
                            onStatusUpdated(ticket.id, status)
                            showStatusDialog = false
                        }) {
                            Text(status.name.replace("_", " "), color = AppPrimary)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showStatusDialog = false }) {
                    Text("Cancel", color = AppSecondaryText)
                }
            },
            shape = RoundedCornerShape(18.dp)
        )
    }

    // Diálogo para cambiar prioridad
    if (showPriorityDialog) {
        AlertDialog(
            onDismissRequest = { showPriorityDialog = false },
            title = { Text("Update Priority") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Priority.entries.forEach { priority ->
                        TextButton(onClick = {
                            onPriorityUpdated(ticket.id, priority)
                            showPriorityDialog = false
                        }) {
                            Text(priority.name, color = AppPrimary)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showPriorityDialog = false }) {
                    Text("Cancel", color = AppSecondaryText)
                }
            },
            shape = RoundedCornerShape(18.dp)
        )
    }

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            TopAppBar(
                title = { Text("Ticket Detail",
                    style = MaterialTheme.typography.headlineMedium) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back", tint = AppPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBackground)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Título
            Text(text = ticket.title,
                style = MaterialTheme.typography.headlineLarge,
                color = AppPrimary)

            // Badges
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PriorityBadge(priority = ticket.priority)
                StatusBadge(status = ticket.status)
            }

            HorizontalDivider(color = AppBorder)

            // Info rows
            DetailRow(label = "Supplier", value = ticket.supplier)
            DetailRow(label = "Category", value = ticket.category.name)
            DetailRow(label = "Created", value = ticket.createdAt)

            HorizontalDivider(color = AppBorder)

            // Descripción
            Text("Description", style = MaterialTheme.typography.titleMedium)
            Text(text = ticket.description,
                style = MaterialTheme.typography.bodyLarge,
                color = AppSecondaryText)

            Spacer(modifier = Modifier.height(8.dp))

            // Botón cambiar estado
            AppButton(
                text = "Update Status",
                onClick = { showStatusDialog = true },
                modifier = Modifier.fillMaxWidth()
            )

            // Botón cambiar prioridad — controlado por feature flag
            if (canUpdatePriority) {
                OutlinedButton(
                    onClick = { showPriorityDialog = true },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Update Priority", color = AppPrimary)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge, color = AppSecondaryText)
        Text(value, style = MaterialTheme.typography.bodyLarge, color = AppPrimary)
    }
}