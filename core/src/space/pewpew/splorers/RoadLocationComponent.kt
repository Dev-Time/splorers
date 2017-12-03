package space.pewpew.splorers

import com.badlogic.ashley.core.Component

data class RoadLocationComponent(val city1: CityLocationComponent, val city2: CityLocationComponent): Component {
    var x = 0f
    var y = 0f
    var rotation = 0f
    var set = false

    override fun hashCode(): Int = city1.hashCode() + city2.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoadLocationComponent

        if (!(city1 == city1 || city1 == city2)) return false
        if (!(city2 == city1 || city2 == city2)) return false

        return true
    }


}