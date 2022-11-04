package com.example.moviecatalog.view.sharedsamples

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@ExperimentalMaterial3Api
@Composable
fun NewOutlinedTextField(
    message: MutableState<String>,
    placeholderText: String,
    isPassword: Boolean
) {
    OutlinedTextField(
        value = message.value,
        onValueChange = { newText ->
            message.value = newText
        },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                    text = placeholderText,
                    fontFamily = ibmPlexSansFamily,
                    fontSize = 14.sp
                )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            placeholderColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(
            fontFamily = ibmPlexSansFamily,
            fontSize = 14.sp
        ),
        singleLine = true,
        visualTransformation =
        if (isPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions =
        if (isPassword)
            KeyboardOptions(keyboardType = KeyboardType.Password)
        else
            KeyboardOptions.Default
    )
}