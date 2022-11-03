package com.example.moviecatalog.view.sharedsamples

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@Composable
fun NewGenderCheckField(
    isMaleChosen: MutableState<Boolean>,
    isFemaleChosen: MutableState<Boolean>
) {

    Row(modifier = Modifier) {
        OutlinedButton(
            onClick = {
                isMaleChosen.value = true
                isFemaleChosen.value = false
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isMaleChosen.value) MaterialTheme.colorScheme.primary else Color.Transparent
            ),
            modifier = Modifier
                .height(44.dp)
                .weight(1f)
                .offset(x = 0.5.dp),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 0.dp,
                bottomStart = 8.dp,
                bottomEnd = 0.dp
            )
        ) {
            Text(
                text = LocalContext.current.getString(R.string.gender_picker_male_text),
                fontFamily = ibmPlexSansFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                color = if (isMaleChosen.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
        }

        OutlinedButton(
            onClick = {
                isMaleChosen.value = false
                isFemaleChosen.value = true
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isFemaleChosen.value) MaterialTheme.colorScheme.primary else Color.Transparent
            ),
            modifier = Modifier
                .height(44.dp)
                .weight(1f)
                .offset(x = ((-0.5).dp)),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 8.dp,
                bottomStart = 0.dp,
                bottomEnd = 8.dp
            )
        ) {
            Text(
                text = LocalContext.current.getString(R.string.gender_picker_female_text),
                fontFamily = ibmPlexSansFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                color = if (isFemaleChosen.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
        }
    }
}