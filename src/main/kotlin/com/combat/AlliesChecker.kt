package com.combat

class AlliesChecker {
    fun areAllies(firstAdventurer: Adventurer, secondAdventurer: TargetableEntity): Boolean {
        firstAdventurer.getFactions().forEach {
            if(it.hasMember(firstAdventurer) && it.hasMember(secondAdventurer)) {
                return@areAllies true
            }
        }
        return false
    }

}
