package space.pewpew.splorers

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import java.util.*

class CityActor(tile1: TileActor, tile2: TileActor, tile3: TileActor, val cityRadius: Float, val shapeRenderer: ShapeRenderer): Actor() {
    var center: Coordinate
    var shape: Circle

    init {
        val x = LinkedList<Float>()
        val y = LinkedList<Float>()

        x.add(tile1.x)
        x.add(tile2.x)
        x.add(tile3.x)

        y.add(tile1.y)
        y.add(tile2.y)
        y.add(tile3.y)

        val tileHeight = tile1.height
        val tileWidth = tile1.width

        val cityX = x.average().toFloat() + tileWidth/2f
        val cityY = y.average().toFloat() + tileHeight/2f

        center = Coordinate(cityX, cityY)

        shape = Circle(cityRadius, cityRadius, cityRadius)

        setBounds(cityX - cityRadius, cityY - cityRadius, cityRadius * 2f, cityRadius*2f)

        setX(cityX)
        setY(cityY)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        shapeRenderer.setColor(1f, 1f, 0f, 1f)
        shapeRenderer.circle(center.x,  center.y, cityRadius, 10)
    }

    override fun hit(x: Float, y: Float, touchable: Boolean): Actor? {
        return if (touchable && getTouchable() != Touchable.enabled) {
            null
        } else if (shape.contains(x, y)) {
            this
        } else {
            null
        }
    }
}