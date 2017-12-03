package space.pewpew.splorers

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import java.util.*

class CityDisplaySystem(private val tileWidth: Float, private val tileHeight: Float, private val shapeRenderer: ShapeRenderer): IteratingSystem(Family.all(CityLocationComponent::class.java).get()) {
    private val cm = ComponentMapper.getFor(CityLocationComponent::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val location = cm.get(entity)

        if (!location.set) {

            val x = LinkedList<Float>()
            val y = LinkedList<Float>()

            val city1Converted = TileDisplaySystem.convertTileLocation(location.tile1.x, location.tile1.y, tileWidth, tileHeight)
            val city2Converted = TileDisplaySystem.convertTileLocation(location.tile2.x, location.tile2.y, tileWidth, tileHeight)
            val city3Converted = TileDisplaySystem.convertTileLocation(location.tile3.x, location.tile3.y, tileWidth, tileHeight)

            x.add(city1Converted.x)
            x.add(city2Converted.x)
            x.add(city3Converted.x)

            y.add(city1Converted.y)
            y.add(city2Converted.y)
            y.add(city3Converted.y)

            val cityX = x.average().toFloat() + tileWidth/2f
            val cityY = y.average().toFloat() + tileHeight/2f

            location.x = cityX
            location.y = cityY

            location.set = true
        }


        shapeRenderer.setColor(1f, 1f, 0f, 1f)
        shapeRenderer.circle(location.x, location.y, .5f, 10)
    }
}