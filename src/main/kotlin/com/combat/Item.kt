package com.combat

class Item(
    override var health: Int,
    override val position: Position = Position(0, 0)
): TargetableEntity {
    override var level = 1
    fun isDestroyed(): Boolean {
        return health == 0
    }
}