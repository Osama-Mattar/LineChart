package com.mattar.osama.linechart.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mattar.osama.linechart.ui.line.LineChartDataModel
import com.mattar.osama.linechart.ui.line.PlotChartScreen
import com.mattar.osama.linechart.ui.theme.LineChartTheme
import com.mattar.osama.linechart.ui.theme.sofiaFont

@Composable
fun HomeScreen() {
    LineChartTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Compose Charts") })
        }) {
            HomeScreenContent()
        }
    }
}

@Composable
private fun HomeScreenContent() {
    Card(
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, Color.White),
        elevation = 12.dp,
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = LineChartDataModel.DataPoints.lastUpdated,
                style = TextStyle(
                    fontFamily = sofiaFont,
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),

                text = LineChartDataModel.DataPoints.currency,
                style = TextStyle(
                    fontFamily = sofiaFont,
                    fontWeight = FontWeight.Bold
                )
            )
            PlotChartScreen()
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() = HomeScreen()