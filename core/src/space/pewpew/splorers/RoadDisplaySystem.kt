package space.pewpew.splorers

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class RoadDisplaySystem(private val shapeRenderer: ShapeRenderer): IteratingSystem(Family.all(RoadLocationComponent::class.java).get()) {
    private val lm = ComponentMapper.getFor(RoadLocationComponent::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val location = lm.get(entity)

        if (!location.set) {
            val cityX = HashSet<Float>()
            val cityY = HashSet<Float>()

            cityX.add(location.city1.x)
            cityX.add(location.city2.x)

            cityY.add(location.city1.y)
            cityY.add(location.city2.y)

            location.x = cityX.average().toFloat()
            location.y = cityY.average().toFloat()

            location.set = true
        }

        shapeRenderer.setColor(1f, 1f, 0f, 1f)
        shapeRenderer.rectLine(location.city1.x, location.city1.y, location.city2.x, location.city2.y, .5f)
    }
}