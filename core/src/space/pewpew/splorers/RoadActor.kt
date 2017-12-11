package space.pewpew.splorers

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor

class RoadActor(val city1: CityActor, val city2: CityActor, val roadWidth: Float, val shapeRenderer: ShapeRenderer): Actor() {
    override fun draw(batch: Batch?, parentAlpha: Float) {
        shapeRenderer.setColor(1f, 1f, 0f, 1f)
        shapeRenderer.rectLine(city1.center.x,  city1.center.y, city2.center.x, city2.center.y, roadWidth)
    }
}