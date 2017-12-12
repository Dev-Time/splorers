package space.pewpew.splorers

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import java.lang.Math.abs
import java.lang.Math.pow
import kotlin.math.atan
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.sqrt

class RoadActor(val city1: CityActor, val city2: CityActor, val roadWidth: Float, val relativeRoadLength: Float, val shapeRenderer: ShapeRenderer) : Actor() {
    var shape: Polygon

    init {
        val xDiff = (city2.getX() - city1.getX())
        val yDiff = (city2.getY() - city1.getY())

        val hypotenuse = sqrt((pow(xDiff.toDouble(), 2.0) + pow(yDiff.toDouble(), 2.0)).toFloat())
        val angle = atan(yDiff / xDiff)

        val absoluteRoadLength = relativeRoadLength * hypotenuse

        val xOffset = 0.5f * hypotenuse * cos(angle)
        val yOffset = 0.5f * hypotenuse * sin(angle)

        x = city1.getX() + xOffset
        y = city1.getY() + yOffset

        val longXOffset = 0.5f * absoluteRoadLength * cos(angle)
        val longYOffset = 0.5f * absoluteRoadLength * sin(angle)

        val shortXOffset = 0.5f * roadWidth * sin(angle)
        val shortYOffset = 0.5f * roadWidth * cos(angle)

        val point1 = Coordinate(x - longXOffset - shortXOffset, y - longYOffset - shortYOffset)
        val point2 = Coordinate(x + longXOffset - shortXOffset, y + longYOffset + shortYOffset)
        val point3 = Coordinate(x + longXOffset + shortXOffset, y + longYOffset - shortYOffset)
        val point4 = Coordinate(x - longXOffset + shortXOffset, y - longYOffset - shortYOffset)

        val vertices = FloatArray(4 * 2)

        vertices[0] = point1.x
        vertices[1] = point1.y
        vertices[2] = point2.x
        vertices[3] = point2.y
        vertices[4] = point3.x
        vertices[5] = point3.y
        vertices[6] = point4.x
        vertices[7] = point4.y

        shape = Polygon(vertices)

        setBounds(x, y, 2 * (longXOffset + shortXOffset), 2 * (longYOffset + shortYOffset) )

    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        shapeRenderer.setColor(1f, 1f, 0f, 1f)
        shapeRenderer.rectLine(city1.center.x, city1.center.y, city2.center.x, city2.center.y, roadWidth)
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