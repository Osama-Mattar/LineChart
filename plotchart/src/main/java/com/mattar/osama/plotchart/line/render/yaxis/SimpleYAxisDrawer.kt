package com.mattar.osama.plotchart.line.render.yaxis

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mattar.osama.plotchart.LabelFormatter
import com.mattar.osama.plotchart.toLegacyInt
import kotlin.math.roundToInt

class SimpleYAxisDrawer(
    val labelTextSize: TextUnit = 12.sp,
    val labelTextColor: Color = Color.Transparent,
    val drawLabelEvery: Int = 1,
    val labelValueFormatter: LabelFormatter = { value -> "%.1f".format(value) },
    val axisLineThickness: Dp = 1.dp,
    val axisLineColor: Color = Color.Transparent,
) : IYAxisDrawer {
    private val mAxisLinePaint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = axisLineColor
            style = PaintingStyle.Stroke
        }
    }

    private val mTextPaint by lazy {
        android.graphics.Paint().apply {
            isAntiAlias = true
            color = labelTextColor.toLegacyInt()
        }
    }

    private val mTextBounds = android.graphics.Rect()

    override fun drawAxisLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) =
        with(drawScope) {
            val lineThickness = axisLineThickness.toPx()
            val x = drawableArea.right - lineThickness / 2F
            canvas.drawLine(
                p1 = Offset(x = x, y = drawableArea.top),
                p2 = Offset(x = x, y = drawableArea.bottom),
                paint = mAxisLinePaint.apply { strokeWidth = lineThickness })
        }

    override fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    ) {
        with(drawScope) {
            val labelPaint = mTextPaint.apply {
                textSize = labelTextSize.toPx()
                textAlign = android.graphics.Paint.Align.RIGHT
            }

            val minLabelHeight = labelTextSize.toPx() * drawLabelEvery.toFloat()
            val totalHeight = drawableArea.height
            val labelCount = (drawableArea.height / minLabelHeight).roundToInt().coerceAtLeast(2)

            for (i in 0..labelCount) {
                val value = minValue + i * (maxValue - minValue) / labelCount
                val label = labelValueFormatter(value)
                val x = drawableArea.right - axisLineThickness.toPx() - labelTextSize.toPx() / 2F
                labelPaint.getTextBounds(label, 0, label.length, mTextBounds)
                val y =
                    drawableArea.bottom - i * (totalHeight / labelCount) - mTextBounds.height() / 2F
                canvas.nativeCanvas.drawText(label, x, y, labelPaint)
            }
        }
    }
}