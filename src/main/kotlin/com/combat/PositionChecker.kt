package com.combat

class PositionChecker(private val distanceCalculator: DistanceCalculator) {
    fun areInRange(firstAdventurer: Adventurer, secondAdventurer: Adventurer): Boolean {
        val distanceBetweenCharacters = distanceCalculator.computeDistance(firstAdventurer.position, secondAdventurer.position)
        return firstAdventurer.range.value >= distanceBetweenCharacters
    }

}
