package space.pewpew.splorers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable

class TileActor(texture: Texture, height: Float, width: Float, x: Float, y: Float, val gridLocation: HexGridLocation): Actor() {
    val region = TextureRegion(texture)
    var shape: Polygon
    val location = TileLocation(x, y)

    init {
        setX(x)
        setY(y)
        setOrigin(width/2f, height/2f)
        sizeBy(width, height)

        val circleRadius = height/2f

        val point1 = Coordinate(originX - circleRadius/2f, originY + circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
        val point2 = Coordinate(originX + circleRadius/2f,originY + circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
        val point3 = Coordinate(originX + circleRadius, originY)
        val point4 = Coordinate(originX + circleRadius/2f, originY - circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
        val point5 = Coordinate(originX - circleRadius/2f, originY - circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
        val point6 = Coordinate(originX - circleRadius, originY)

        val vertices = FloatArray(12)

        vertices[0] = point1.x
        vertices[1] = point1.y
        vertices[2] = point2.x
        vertices[3] = point2.y
        vertices[4] = point3.x
        vertices[5] = point3.y
        vertices[6] = point4.x
        vertices[7] = point4.y
        vertices[8] = point5.x
        vertices[9] = point5.y
        vertices[10] = point6.x
        vertices[11] = point6.y

        shape = Polygon(vertices)

        setBounds(x, y, width, height)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
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