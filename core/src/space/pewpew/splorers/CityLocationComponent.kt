package space.pewpew.splorers

import com.badlogic.ashley.core.Component
import java.util.*

data class CityLocationComponent(val tile1: TileLocationComponent, val tile2: TileLocationComponent, val tile3: TileLocationComponent): Component {
    var x = 0f
    var y = 0f
    var set = false

    override fun hashCode(): Int = tile1.hashCode() + tile2.hashCode() + tile3.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CityLocationComponent

        if (!(tile1 == other.tile1 || tile1 == other.tile2 || tile1 == other.tile3)) return false
        if (!(tile2 == other.tile1 || tile2 == other.tile2 || tile2 == other.tile3)) return false
        if (!(tile3 == other.tile1 || tile3 == other.tile2 || tile3 == other.tile3)) return false

        return true
    }
}