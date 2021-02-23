package com.combat

import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.sqrt

class DistanceCalculator {
    fun computeDistance(firstPosition: Position, secondPosition: Position): Double {
        val firstSquareElement = (secondPosition.colIndex - firstPosition.colIndex).toDouble().pow(2)
        val secondSquareElement = (secondPosition.rowIndex - firstPosition.rowIndex).toDouble().pow(2)
        val distance = sqrt(firstSquareElement + secondSquareElement)
        return formattedDistance(distance)
    }

    private fun formattedDistance(distance: Double): Double {
        return distance.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
    }

}
