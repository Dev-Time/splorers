package space.pewpew.splorers

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Shape2D
import com.badlogic.gdx.math.Vector3

class MyInputAdapter(val sprite: Sprite, val cam: OrthographicCamera, val poly: Shape2D): InputAdapter() {

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val unprojected = cam.unproject(Vector3(screenX.toFloat(), screenY.toFloat(), 0f))

        return if (poly.contains(unprojected.x, unprojected.y)) {
            print(sprite.x)
            print(" ")
            println(sprite.y)
            true
        } else {
            false
        }
    }
}