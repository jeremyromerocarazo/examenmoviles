package com.moviles.examenmoviles.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviles.examenmoviles.domain.model.Ticket
import com.moviles.examenmoviles.ui.screens.createticket.CreateTicketScreen
import com.moviles.examenmoviles.ui.screens.featureflags.FeatureFlagsScreen
import com.moviles.examenmoviles.ui.screens.login.LoginScreen
import com.moviles.examenmoviles.ui.screens.ticketdetail.TicketDetailScreen
import com.moviles.examenmoviles.ui.screens.ticketlist.TicketListScreen
import com.moviles.examenmoviles.ui.screens.ticketlist.TicketListViewModel

object AppDestinations {
    const val LOGIN = "login"
    const val TICKET_LIST = "ticket_list"
    const val TICKET_DETAIL = "ticket_detail"
    const val CREATE_TICKET = "create_ticket"
    const val FEATURE_FLAGS = "feature_flags"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val ticketListViewModel: TicketListViewModel = viewModel()
    var selectedTicket by remember { mutableStateOf<Ticket?>(null) }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(AppDestinations.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(AppDestinations.TICKET_LIST) {
                        popUpTo(AppDestinations.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestinations.TICKET_LIST) {
            TicketListScreen(
                onTicketClick = { ticket ->
                    selectedTicket = ticket
                    navController.navigate(AppDestinations.TICKET_DETAIL)
                },
                onCreateClick = {
                    navController.navigate(AppDestinations.CREATE_TICKET)
                },
                onFlagsClick = {
                    navController.navigate(AppDestinations.FEATURE_FLAGS)
                },
                viewModel = ticketListViewModel
            )
        }

        composable(AppDestinations.TICKET_DETAIL) {
            selectedTicket?.let { ticket ->
                TicketDetailScreen(
                    ticket = ticket,
                    onBackClick = { navController.popBackStack() },
                    onStatusUpdated = { id, status ->
                        ticketListViewModel.onStatusUpdated(id, status)
                        navController.popBackStack()
                    },
                    onPriorityUpdated = { id, priority ->
                        ticketListViewModel.onPriorityUpdated(id, priority)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(AppDestinations.CREATE_TICKET) {
            CreateTicketScreen(
                onBackClick = { navController.popBackStack() },
                onTicketCreated = { ticket ->
                    ticketListViewModel.onTicketCreated(ticket)
                    navController.popBackStack()
                }
            )
        }

        composable(AppDestinations.FEATURE_FLAGS) {
            FeatureFlagsScreen(
                onHomeClick = { navController.popBackStack() }
            )
        }
    }
}