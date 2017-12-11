package space.pewpew.splorers

data class CityGridLocation(val tile1: HexGridLocation, val tile2: HexGridLocation, val tile3: HexGridLocation) {
    var x = 0f
    var y = 0f

    override fun hashCode(): Int = tile1.hashCode() + tile2.hashCode() + tile3.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CityGridLocation

        if (!(tile1 == other.tile1 || tile1 == other.tile2 || tile1 == other.tile3)) return false
        if (!(tile2 == other.tile1 || tile2 == other.tile2 || tile2 == other.tile3)) return false
        if (!(tile3 == other.tile1 || tile3 == other.tile2 || tile3 == other.tile3)) return false

        return true
    }
}
