package com.example.moviecatalog.view.sharedsamples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@Composable
fun NewOutlinedButton(isValidInput: Boolean, buttonText: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        enabled = isValidInput,
        border = BorderStroke(
            if (!isValidInput)
                1.dp
            else
                0.dp,
            if (!isValidInput)
                MaterialTheme.colorScheme.outline
            else
                MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .height(44.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContentColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Text(
            text = buttonText,
            fontFamily = ibmPlexSansFamily,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
        )
    }
}