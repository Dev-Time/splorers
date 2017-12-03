package space.pewpew.splorers

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Polygon

class ControlSystem(val multiplexer: InputMultiplexer, val cam: OrthographicCamera): IteratingSystem(Family.all(ImageComponent::class.java, ControllerComponent::class.java).get()) {
    val im = ComponentMapper.getFor(ImageComponent::class.java)
    val cm = ComponentMapper.getFor(ControllerComponent::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val image = im.get(entity)
        val controller = cm.get(entity)

        val sprite = image.sprite

        if (image.set && !controller.set) {
            val circleRadius = sprite.height/2f

            val point1 = Coordinate(sprite.x + sprite.originX - circleRadius/2f, sprite.y + sprite.originY + circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
            val point2 = Coordinate(sprite.x + sprite.originX + circleRadius/2f, sprite.y + sprite.originY + circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
            val point3 = Coordinate(sprite.x + sprite.originX + circleRadius, sprite.y + sprite.originY)
            val point4 = Coordinate(sprite.x + sprite.originX + circleRadius/2f, sprite.y + sprite.originY - circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
            val point5 = Coordinate(sprite.x + sprite.originX - circleRadius/2f, sprite.y + sprite.originY - circleRadius * (Math.sqrt(3.0)).toFloat()/2f)
            val point6 = Coordinate(sprite.x + sprite.originX - circleRadius, sprite.y + sprite.originY)

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

            controller.shape = Polygon(vertices)

            controller.controller = MyInputAdapter(sprite, cam, controller.shape)
            multiplexer.addProcessor(controller.controller)

            controller.set = true
        }
    }
}