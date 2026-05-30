package com.moviles.examenmoviles.ui.screens.createticket

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.domain.model.*
import com.moviles.examenmoviles.ui.components.AppButton
import com.moviles.examenmoviles.ui.components.AppTextField
import com.moviles.examenmoviles.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketScreen(
    onBackClick: () -> Unit,
    onTicketCreated: (Ticket) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var supplier by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }
    var selectedCategory by remember { mutableStateOf(Category.OTHER) }

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            TopAppBar(
                title = { Text("New Ticket",
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

            AppTextField(
                value = title,
                label = "Title",
                placeholder = "Describe the issue briefly",
                onValueChange = { title = it }
            )
            AppTextField(
                value = description,
                label = "Description",
                placeholder = "Provide full details of the incident...",
                onValueChange = { description = it },
                singleLine = false
            )
            AppTextField(
                value = supplier,
                label = "Supplier",
                placeholder = "Supplier or distributor name",
                onValueChange = { supplier = it }
            )

            // Selector de prioridad
            Text("Priority", style = MaterialTheme.typography.bodyLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Priority.entries.forEach { priority ->
                    val selected = selectedPriority == priority
                    FilterChip(
                        selected = selected,
                        onClick = { selectedPriority = priority },
                        label = { Text(priority.name) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AppPrimary,
                            selectedLabelColor = AppBackground
                        ),
                        shape = RoundedCornerShape(50.dp)
                    )
                }
            }

            // Selector de categoría
            Text("Category", style = MaterialTheme.typography.bodyLarge)
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Category.entries.forEach { category ->
                    val selected = selectedCategory == category
                    FilterChip(
                        selected = selected,
                        onClick = { selectedCategory = category },
                        label = { Text(category.name) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AppPrimary,
                            selectedLabelColor = AppBackground
                        ),
                        shape = RoundedCornerShape(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            AppButton(
                text = "Create Ticket",
                onClick = {
                    if (title.isNotBlank() && supplier.isNotBlank()) {
                        val newTicket = Ticket(
                            id = System.currentTimeMillis().toInt(),
                            title = title,
                            description = description.ifBlank { "No description provided." },
                            priority = selectedPriority,
                            status = Status.OPEN,
                            supplier = supplier,
                            category = selectedCategory,
                            createdAt = "2026-05-30"
                        )
                        onTicketCreated(newTicket)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}