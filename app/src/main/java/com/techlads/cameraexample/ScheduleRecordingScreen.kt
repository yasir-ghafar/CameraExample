package com.techlads.cameraexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ScheduleRecordingScreen(
    onSchedule: (startTime: Long, endTime: Long) -> Unit
) {
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Schedule Video Recording",
            style = MaterialTheme.typography.bodyMedium)

        OutlinedTextField(
            value = startTime,
            onValueChange = {startTime = it},
            label = { Text("Start Time (HH:mm)")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = endTime,
            onValueChange = {endTime = it},
            label = { Text("End Time (HH:mm)")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val startMillis = parseTimeToMillis(startTime)
            val endMillis = parseTimeToMillis(endTime)
            if (startMillis != null && endMillis != null) {
                onSchedule(startMillis, endMillis)
            }
        }) {

        }
    }
}

fun parseTimeToMillis(time: String): Long? {
    return try {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = sdf.parse(time)
        date?.time
    } catch (e: Exception) {
        null
    }
}
