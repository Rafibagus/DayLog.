package org.d3if0075.daylog.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class PieChartInput(
    val color: Color,
    val value:Int,
    val description:String,
    val isTapped:Boolean = false
)
