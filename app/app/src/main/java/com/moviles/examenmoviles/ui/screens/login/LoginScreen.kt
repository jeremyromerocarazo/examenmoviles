package com.moviles.examenmoviles.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.ui.components.AppButton
import com.moviles.examenmoviles.ui.components.AppTextField
import com.moviles.examenmoviles.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = AppBackground,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier.padding(14.dp),
                    containerColor = AppError,
                    contentColor = AppBackground,
                    shape = RoundedCornerShape(14.dp),
                    snackbarData = data
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 26.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(AppSurfaceVariant, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ConfirmationNumber,
                        contentDescription = null,
                        tint = AppPrimary,
                        modifier = Modifier.size(38.dp)
                    )
                }
                Text(
                    text = "Panini Support",
                    style = MaterialTheme.typography.headlineLarge,
                    color = AppPrimary
                )
                Text(
                    text = "FIFA World Cup 2026",
                    style = MaterialTheme.typography.bodyLarge,
                    color = AppSecondaryText
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppTextField(
                    value = email,
                    label = "Email",
                    placeholder = "agent@panini.com",
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                AppTextField(
                    value = password,
                    label = "Password",
                    placeholder = "••••••••",
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )
                AppButton(
                    text = "Sign In",
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Please fill in all fields")
                            }
                        } else {
                            onLoginClick()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Demo: Use any email and password",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppSecondaryText,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}