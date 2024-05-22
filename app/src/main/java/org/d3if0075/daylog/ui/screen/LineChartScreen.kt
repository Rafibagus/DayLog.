package org.d3if0075.daylog.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import org.d3if0075.daylog.R
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1


@Composable
fun LineChartScreen(navHostController: NavHostController) {

    val steps = 5
    val pointsData = listOf(
        Point(0f, 40f),
        Point(1f, 90f),
        Point(2f, 0f),
        Point(3f, 60f),
        Point(4f, 10f)
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val lineCharData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color(0XFFAB8172),
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = Color(0XFFAB8172)
                    ),
//                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.tertiary),
//                    ShadowUnderLine(
//                        alpha = 0.5f,
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                MaterialTheme.colorScheme.inversePrimary,
//                                Color.Transparent
//                            )
//                        )
//                    ),
//                    SelectionHighlightPopUp()
                )
            ),
        ),
        backgroundColor = Color.Transparent,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp)) // Apply rounded corners
                    .background(Color(0xFFEEE3CB)) // Set background color
            ){
                LineChart(
                    modifier = Modifier.fillMaxSize(),
                    lineChartData = lineCharData
                )
            }
            Spacer(modifier = Modifier.weight(1f)) // Pushes the Row to the bottom
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(500.dp, 68.dp)
                .background(Grey1)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            // Handle home image click
                            navHostController.navigate(Screen.Home.route)
                        }
                ) {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.home_house),
                        contentDescription = stringResource(id = R.string.home)
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable {
                            navHostController.navigate(Screen.Chart.route)
                            // Handle graph image click
                        }
                ) {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.analytics_graph_chart),
                        contentDescription = stringResource(id = R.string.graph)
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable {
                            navHostController.navigate(Screen.About.route)
                            // Handle account image click
                        }
                ) {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.account_user_person_square),
                        contentDescription = stringResource(id = R.string.account)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartPreview() {
    DayLogTheme {
        LineChartScreen(navHostController = rememberNavController())
    }
}
