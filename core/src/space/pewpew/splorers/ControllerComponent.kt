package space.pewpew.splorers

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Shape2D

class ControllerComponent: Component {
    var shape:Shape2D = Polygon()
    var controller = InputAdapter()
    var set = false
}