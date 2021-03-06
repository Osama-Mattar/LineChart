package com.mattar.osama.linechart.ui.line

class LineChartDataModel {
    object DataPoints {
        val currency: String = "\$USD"
        val lastUpdated: String = "LAST UPDATED: 01H:31M AGO"
        val dataPoints3 = listOf(
            com.mattar.osama.plotchart.line.Point(20f, "Mon"),
            com.mattar.osama.plotchart.line.Point(40f, "Tue"),
            com.mattar.osama.plotchart.line.Point(75f, "Wed"),
            com.mattar.osama.plotchart.line.Point(50f, "Thu"),
            com.mattar.osama.plotchart.line.Point(35f, "Today")
        )
    }
}