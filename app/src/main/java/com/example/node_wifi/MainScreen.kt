package com.example.node_wifi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun Screen(viewModel: SensorDataViewModel) { // Pass ViewModel as argument
    Box(modifier = Modifier.fillMaxSize()) {
        DataShow(viewModel)
    }
}

@Composable
fun DataShow(viewModel: SensorDataViewModel) { // Receive ViewModel
    val myState = viewModel.sensorData.observeAsState(SensorData(0.00,0))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "DATA received")
        Row {
            Row {
                Text(
                text = "Humidity = ${myState.value.strHumidity}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .border(2.dp, Color.White)
                    .padding(4.dp)
            )}
            Row {
                Text(
                    text = "Temperature = ${myState.value.strTemp}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .border(2.dp, Color.White)
                        .padding(4.dp)
                )
            }
        }
    }
}


