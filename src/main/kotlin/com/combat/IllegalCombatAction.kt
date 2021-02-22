package com.combat

import java.lang.Exception

class IllegalCombatAction : Exception {
    constructor(): super("Illegal combat action. This action will be skipped.")
}
