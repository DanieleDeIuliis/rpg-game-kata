package com.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class DistanceCalculatorTest {

    @Test
    fun `the distance between the same point is zero`() {
        val position = Position(1,1)

        val distance = DistanceCalculator().computeDistance(position, position)

        assertThat(distance).isEqualTo(0.00)
    }

    @Test
    fun `computes the correct distance between two points in a two dimensional space`() {
        val firstPosition = Position(1,1)
        val secondPosition = Position(2,3)

        val distance = DistanceCalculator().computeDistance(firstPosition, secondPosition)

        assertThat(distance).isEqualTo(2.24)
    }
}