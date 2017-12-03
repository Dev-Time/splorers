package space.pewpew.splorers

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Splorers : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var engine: Engine
    private lateinit var cam: OrthographicCamera
    private lateinit var shapeRenderer: ShapeRenderer
    private lateinit var hexTexture: Texture

    private val gridHeight = 1
    private val gridWidth = 1
    private val hexHeight = 5f
    private val hexWidth = 5f

    override fun create() {
        batch = SpriteBatch()
        engine = Engine()
        shapeRenderer = ShapeRenderer()
        hexTexture = Texture("hex.png")

        val w = Gdx.graphics.width.toFloat()
        val h = Gdx.graphics.height.toFloat()

        val multiplexer = InputMultiplexer()

        cam = OrthographicCamera(30f, 30 * (h / w))

        for (i in  -gridWidth..gridWidth) {
            for (j in -gridHeight..gridHeight) {
                val hex = Entity()
                val image = ImageComponent(hexTexture)
                hex.add(image)
                hex.add(TileLocationComponent(i.toFloat(),j.toFloat()))
                hex.add(SizeComponent(hexHeight, hexWidth))
                hex.add(ControllerComponent())
                engine.addEntity(hex)
            }
        }

        cam.position.set(0f, 0f, 0f)

        val city0 = Entity()
        city0.add(CityLocationComponent(TileLocationComponent(0f, 0f), TileLocationComponent(1f, 0f), TileLocationComponent(1f, 1f)))

        val city1 = Entity()
        city1.add(CityLocationComponent(TileLocationComponent(-1f, -1f), TileLocationComponent(0f, 0f), TileLocationComponent(0f, -1f)))

        val city2 = Entity()
        city2.add(CityLocationComponent(TileLocationComponent(-1f, -0f), TileLocationComponent(0f, 0f), TileLocationComponent(0f, 1f)))

        val city3 = Entity()
        city3.add(CityLocationComponent(TileLocationComponent(-1f, 0f), TileLocationComponent(-1f, 1f), TileLocationComponent(0f, 1f)))

        val city4 = Entity()
        city4.add(CityLocationComponent(TileLocationComponent(1f, -1f), TileLocationComponent(0f, -1f), TileLocationComponent(1f, 0f)))

        val city5 = Entity()
        city5.add(CityLocationComponent(TileLocationComponent(0f, 0f), TileLocationComponent(-1f, 0f), TileLocationComponent(-1f, -1f)))

        val city6 = Entity()
        city6.add(CityLocationComponent(TileLocationComponent(0f, 0f), TileLocationComponent(0f, 1f), TileLocationComponent(1f, 1f)))

        val city7 = Entity()
        city7.add(CityLocationComponent(TileLocationComponent(0f, 0f), TileLocationComponent(1f, 0f), TileLocationComponent(0f, -1f)))

        engine.addEntity(city0)
        engine.addEntity(city1)
        engine.addEntity(city2)
        engine.addEntity(city3)
        engine.addEntity(city4)
        engine.addEntity(city5)
        engine.addEntity(city6)
        engine.addEntity(city7)

        val road1 = Entity()
        road1.add(RoadLocationComponent(city1.getComponent(CityLocationComponent::class.java), city5.getComponent(CityLocationComponent::class.java)))

        engine.addEntity(road1)

        engine.addSystem(RoadDisplaySystem(shapeRenderer))
        engine.addSystem(TileDisplaySystem(batch))
        engine.addSystem(CityDisplaySystem(hexWidth, hexHeight, shapeRenderer))
        engine.addSystem(ControlSystem(multiplexer, cam))

        Gdx.input.inputProcessor = multiplexer

        shapeRenderer.setAutoShapeType(true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, .5f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()

        batch.projectionMatrix = cam.combined
        shapeRenderer.projectionMatrix = cam.combined

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        batch.begin()
        engine.update(Gdx.graphics.deltaTime)
        batch.end()
        shapeRenderer.end()

    }

    override fun dispose() {
        batch.dispose()
        shapeRenderer.dispose()
        hexTexture.dispose()
    }
}
