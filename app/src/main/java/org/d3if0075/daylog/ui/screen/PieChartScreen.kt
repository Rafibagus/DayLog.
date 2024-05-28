package org.d3if0075.daylog.ui.screen

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.model.PieChartInput
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1
import org.d3if0075.daylog.ui.theme.LightGray
import org.d3if0075.daylog.ui.theme.LightGreen
import org.d3if0075.daylog.ui.theme.LightOrange
import org.d3if0075.daylog.ui.theme.LightPurple
import org.d3if0075.daylog.ui.theme.pink
import kotlin.math.PI
import kotlin.math.atan2


@Composable
fun PieChartScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey1)
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mood Chart",
            fontSize = 35.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 30.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            PieChart(
                modifier = Modifier
                    .size(500.dp),
                input = listOf(
                    PieChartInput(
                        color = pink,
                        value = 1,
                        description = "Excited"
                    ),
                    PieChartInput(
                        color = LightGray,
                        value = 1,
                        description = "Sad"
                    ),
                    PieChartInput(
                        color = LightGreen,
                        value = 1,
                        description = "Dissapointed"
                    ),
                    PieChartInput(
                        color = LightPurple,
                        value = 1,
                        description = "Calm"
                    ),
                    PieChartInput(
                        color = LightOrange,
                        value = 1,
                        description = "Happy"
                    ),
                ),
                centerText = "Mood Chart"
            )
        }
    }
}

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    radius: Float = 500f,
    innerRadius: Float = 250f,
    transparentWidth: Float = 70f,
    input: List<PieChartInput>,
    centerText: String = ""
) {
    var circleCenter by remember { mutableStateOf(Offset.Zero) }
    var inputList by remember { mutableStateOf(input) }
    var isCenterTapped by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val tapAngleInDegrees = (-atan2(
                                x = circleCenter.y - offset.y,
                                y = circleCenter.x - offset.x
                            ) * (180f / PI).toFloat() - 90f).mod(360f)
                            val centerClicked = if (tapAngleInDegrees < 90) {
                                offset.x < circleCenter.x + innerRadius && offset.y < circleCenter.y + innerRadius
                            } else if (tapAngleInDegrees < 180) {
                                offset.x > circleCenter.x - innerRadius && offset.y < circleCenter.y + innerRadius
                            } else if (tapAngleInDegrees < 270) {
                                offset.x > circleCenter.x - innerRadius && offset.y > circleCenter.y - innerRadius
                            } else {
                                offset.x < circleCenter.x + innerRadius && offset.y > circleCenter.y - innerRadius
                            }

                            if (centerClicked) {
                                inputList = inputList.map {
                                    it.copy(isTapped = !isCenterTapped)
                                }
                                isCenterTapped = !isCenterTapped
                            } else {
                                val anglePerValue = 360f / input.sumOf {
                                    it.value
                                }
                                var currAngle = 0f
                                inputList.forEach { pieChartInput ->
                                    currAngle += pieChartInput.value * anglePerValue
                                    if (tapAngleInDegrees < currAngle) {
                                        val description = pieChartInput.description
                                        inputList = inputList.map {
                                            if (description == it.description) {
                                                it.copy(isTapped = !it.isTapped)
                                            } else {
                                                it.copy(isTapped = false)
                                            }
                                        }
                                        return@detectTapGestures
                                    }
                                }
                            }
                        }
                    )
                }
        ){
            val width = size.width
            val height = size.height
            circleCenter = Offset(x = width / 2f, y = height / 2f)

            val totalValue = input.sumOf {
                it.value
            }

            val anglePerValue = 360f / totalValue
            var currentStartAngle = 0f

            inputList.forEach { pieChartInput ->
                val scale = if (pieChartInput.isTapped) 1.1f else 1.0f
                val angleToDraw = pieChartInput.value * anglePerValue
                scale(scale){
                    drawArc(
                        color = pieChartInput.color,
                        startAngle = currentStartAngle,
                        sweepAngle = angleToDraw,
                        useCenter = true,
                        size = Size(
                            width = radius * 2f,
                            height = radius * 2f
                        ),
                        topLeft = Offset(
                            (width - radius *2f) / 2f,
                            (height - radius *2f) / 2f
                        )
                    )
                    currentStartAngle += angleToDraw
                }
                var rotateAngle = currentStartAngle - angleToDraw / 2f - 90f
                var factor = 1f
                if (rotateAngle > 90f){
                    rotateAngle = (rotateAngle + 180).mod(360f)
                    factor = -0.92f
                }

                val percentage = (pieChartInput.value / totalValue.toFloat() * 100).toInt()

                drawContext.canvas.nativeCanvas.apply {
                    if (percentage > 3) {
                        rotate(rotateAngle){
                            drawText(
                                "percentage %",
                                circleCenter.x,
                                circleCenter.y + (radius - (radius - innerRadius) / 2f) * factor,
                                Paint().apply {
                                    textSize = 13.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = LightGray.toArgb()
                                }
                            )
                        }
                    }
                }
                if (pieChartInput.isTapped){
                    val tabRotation = currentStartAngle - angleToDraw - 90f
                    rotate(tabRotation){
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, radius * 1.2f),
                            color = LightPurple,
                            cornerRadius = CornerRadius(15f, 15f)
                        )
                    }
                    rotate(tabRotation + angleToDraw){
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, radius + 1.2f),
                            color = LightOrange,
                            cornerRadius = CornerRadius(15f, 15f)
                        )
                    }
                    rotate(rotateAngle){
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                "${pieChartInput.description}: ${pieChartInput.value}",
                                circleCenter.x,
                                circleCenter.y + radius * 1.3f * factor,
                                Paint().apply {
                                    textSize = 22.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = LightGray.toArgb()
                                    isFakeBoldText = true
                                }
                            )
                        }
                    }
                }
            }
            if (inputList.first().isTapped){
                rotate(-90f){
                    drawRoundRect(
                        topLeft = circleCenter,
                        size = Size(12f, radius * 1.2f),
                        color = LightGray,
                        cornerRadius = CornerRadius(15f, 15f)
                    )
                }
            }
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    circleCenter.x,
                    circleCenter.y,
                    innerRadius,
                    Paint().apply {
                        color = LightGreen.copy(alpha = 0.6f).toArgb()
                        setShadowLayer(10f, 0f, 0f, LightGray.toArgb())
                    }
                )
            }
            drawCircle(
                color = LightPurple.copy(0.2f),
                radius = innerRadius + transparentWidth / 2f
            )
        }
        Text(
            centerText,
            modifier = Modifier
                .width(Dp(innerRadius / 1.5f))
                .padding(25.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PieChartScreenPreview() {
    DayLogTheme {
        PieChartScreen(navHostController = rememberNavController())
    }
}