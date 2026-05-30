package com.moviles.examenmoviles.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.moviles.examenmoviles.ui.theme.AppBorder
import com.moviles.examenmoviles.ui.theme.AppPrimary
import com.moviles.examenmoviles.ui.theme.AppSecondaryText

@Composable
fun AppTextField(
    value: String,
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = label, modifier = Modifier.padding(bottom = 6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
            placeholder = { Text(placeholder, color = AppSecondaryText) },
            shape = RoundedCornerShape(14.dp),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppBorder,
                unfocusedBorderColor = AppBorder,
                cursorColor = AppPrimary,
                focusedTextColor = AppPrimary,
                unfocusedTextColor = AppPrimary
            )
        )
    }
}