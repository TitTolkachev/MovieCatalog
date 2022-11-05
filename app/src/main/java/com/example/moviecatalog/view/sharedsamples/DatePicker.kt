package com.example.moviecatalog.view.sharedsamples

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import java.time.ZonedDateTime
import java.util.*

@SuppressLint("NewApi")
@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NewDatePicker(
    context: Context,
    date: MutableState<String>,
    zonedDate: MutableState<String>?,
    placeHolderText: String
) {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val zonedDateNow = ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZonedDateTime.now().offset)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, Year: Int, Month: Int, DayOfMonth: Int ->
            date.value = if (DayOfMonth < 10) "0$DayOfMonth." else "$DayOfMonth."
            date.value += if (Month < 9) "0${Month + 1}." else "${Month + 1}."
            date.value += Year.toString()

            if (zonedDate != null) {
                zonedDate.value = "$Year-${Month + 1}-$DayOfMonth" + "T"
                zonedDate.value +=
                        if (zonedDateNow.hour < 10)
                            "0"
                        else
                            ""
                zonedDate.value += zonedDateNow.hour.toString() + ":"
                zonedDate.value +=
                    if (zonedDateNow.minute < 10)
                        "0"
                    else
                        ""
                zonedDate.value += zonedDateNow.minute.toString() + ":"
                zonedDate.value +=
                    if (zonedDateNow.second < 10)
                        "0"
                    else
                        ""
                zonedDate.value += zonedDateNow.second.toString() + "Z"
            }
        }, year, month, day
    )

    OutlinedButton(
        onClick = {
            datePickerDialog.show()
        },
        enabled = true,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(52.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (date.value == "") placeHolderText else date.value,
                fontFamily = ibmPlexSansFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Start,
                color = if (date.value == "") MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
            )
            Icon(
                painter = painterResource(id = R.drawable.calendaricon),
                contentDescription = LocalContext.current.getString(R.string.calendar_icon_content_description),
                modifier = Modifier
                    .scale(1.1f)
                    .padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}