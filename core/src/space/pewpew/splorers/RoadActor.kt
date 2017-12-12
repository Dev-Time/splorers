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

class RoadActor(val city1: CityActor, val city2: CityActor, val roadWidth: Float, val relativeRoadLength: Float, val shapeRenderer: ShapeRenderer): Actor() {
    var shape: Polygon

    init {
        val xDiff = (city2.getX() - city1.getX())
        val yDiff = (city2.getY() - city1.getY())

        val hypotenuse =  sqrt((pow(xDiff.toDouble(),2.0) + pow(yDiff.toDouble(), 2.0)).toFloat())
        val angle = atan(yDiff / xDiff)

        val absoluteRoadLength = relativeRoadLength * hypotenuse

        val xOffset = 0.5f * hypotenuse * cos(angle)
        val yOffset = 0.5f * hypotenuse * sin(angle)

        x = city1.getX() + xOffset
        y = city1.getY() + yOffset

        //TODO find the poiiints of the edges of the road, currently have x,y set to center of road

        shape = Polygon()


    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        shapeRenderer.setColor(1f, 1f, 0f, 1f)
        shapeRenderer.rectLine(city1.center.x,  city1.center.y, city2.center.x, city2.center.y, roadWidth)
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