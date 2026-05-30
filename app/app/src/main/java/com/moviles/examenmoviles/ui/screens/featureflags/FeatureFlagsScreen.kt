package com.moviles.examenmoviles.ui.screens.featureflags

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.feature.FeatureFlags
import com.moviles.examenmoviles.ui.components.BottomBar
import com.moviles.examenmoviles.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureFlagsScreen(
    onHomeClick: () -> Unit
) {
    val canCreate by FeatureFlags.canCreateTicket.collectAsState()
    val canUpdatePriority by FeatureFlags.canUpdatePriority.collectAsState()
    val showResolved by FeatureFlags.showResolvedTickets.collectAsState()

    Scaffold(
        containerColor = AppBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Feature Flags",
                    style = MaterialTheme.typography.headlineMedium) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBackground)
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = "feature_flags",
                onHomeClick = onHomeClick,
                onFlagsClick = { },
                onProfileClick = { }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text("Toggle features on or off for testing.",
                style = MaterialTheme.typography.bodyMedium,
                color = AppSecondaryText)

            Spacer(modifier = Modifier.height(8.dp))

            FlagToggleRow(
                title = "Create Tickets",
                description = "Allows agents to create new support tickets",
                checked = canCreate,
                onToggle = { FeatureFlags.toggleCreateTicket() }
            )
            HorizontalDivider(color = AppBorder)

            FlagToggleRow(
                title = "Update Priority",
                description = "Allows changing ticket priority from detail screen",
                checked = canUpdatePriority,
                onToggle = { FeatureFlags.toggleUpdatePriority() }
            )
            HorizontalDivider(color = AppBorder)

            FlagToggleRow(
                title = "Show Resolved Tickets",
                description = "Shows or hides resolved tickets in the list",
                checked = showResolved,
                onToggle = { FeatureFlags.toggleShowResolved() }
            )
        }
    }
}

@Composable
private fun FlagToggleRow(
    title: String,
    description: String,
    checked: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = AppPrimary)
            Text(description, style = MaterialTheme.typography.bodyMedium, color = AppSecondaryText)
        }
        Switch(
            checked = checked,
            onCheckedChange = { onToggle() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = AppBackground,
                checkedTrackColor = AppPrimary,
                uncheckedThumbColor = AppBackground,
                uncheckedTrackColor = AppSecondaryText
            )
        )
    }
}