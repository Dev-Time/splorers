package space.pewpew.splorers

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class ImageComponent(texture: Texture): Component {
    val sprite = Sprite(texture)
    var set = false
}