package space.pewpew.splorers

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class TileDisplaySystem(private val batch: SpriteBatch): IteratingSystem(Family.all(ImageComponent::class.java, TileLocationComponent::class.java, SizeComponent::class.java).get()) {
    private val im = ComponentMapper.getFor(ImageComponent::class.java)
    private val tl = ComponentMapper.getFor(TileLocationComponent::class.java)
    private val sm = ComponentMapper.getFor(SizeComponent::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val image = im.get(entity)
        val position = tl.get(entity)
        val size = sm.get(entity)

        if (image.sprite.height != size.height || image.sprite.width != size.width) {
            image.sprite.setSize(size.width, size.height)
        }

        if (!image.set) {
            val converted = Companion.convertTileLocation(position.x, position.y, size.width, size.height)

            image.sprite.x = converted.x
            image.sprite.y = converted.y

            image.sprite.setOriginCenter()

            image.set = true
        }

        image.sprite.draw(batch)
    }

    companion object {
        fun convertTileLocation(x: Float, y: Float, width: Float, height: Float): Coordinate {
            val newX = (x * width) * (3f/4f)
            val newY = (y * height) - (.5f * height * x)

            return Coordinate(newX, newY)
        }
    }
}