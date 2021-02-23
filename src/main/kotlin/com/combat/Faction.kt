package com.combat

class Faction(private val members: MutableList<Adventurer> = mutableListOf()) {
    fun join(adventurer: Adventurer) {
        if(members.contains(adventurer)) {
            return
        }
        members.add(adventurer)
    }

    fun remove(adventurer: Adventurer) {
        members.remove(adventurer)
    }

}
