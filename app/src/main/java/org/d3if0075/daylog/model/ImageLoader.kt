package org.d3if0075.daylog.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

@Composable
fun loadImage(resourceId: Int): ImageBitmap {
    return ImageBitmap.Companion.imageResource(id = resourceId)
}