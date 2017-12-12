package space.pewpew.splorers

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.kotcrab.vis.ui.VisUI

class Splorers : ApplicationAdapter() {
    private lateinit var shapeRenderer: ShapeRenderer
    private lateinit var hexTexture: Texture
    private lateinit var stage: Stage
    private lateinit var tiles: HashMap<HexGridLocation, TileActor>
    private lateinit var cities: HashMap<CityGridLocation, CityActor>
    private lateinit var roads: HashMap<RoadGridLocation, RoadActor>

    private val gridHeight = 2
    private val gridWidth = 2
    private val hexHeight = 100f
    private val hexWidth = 100f

    override fun create() {

        shapeRenderer = ShapeRenderer()
        hexTexture = Texture("hex.png")
        VisUI.load()
        tiles = HashMap()
        cities = HashMap()
        roads = HashMap()

        stage = Stage(ScreenViewport())
        Gdx.input.inputProcessor = stage

//        stage.addListener(TileInputListener())
//        val table = Table()
//        table.setFillParent(true)
//        stage.addActor(table)

        for (i in  -gridWidth..gridWidth) {
            for (j in -gridHeight..gridHeight) {
                val coord = convertTileLocation(i.toFloat(), j.toFloat(), hexWidth, hexHeight)
                val location = HexGridLocation(i, j)
                val tile = (TileActor(hexTexture, hexHeight, hexWidth, coord.x+stage.width/2f, coord.y+stage.height/2f, location))
                tile.addListener(TileInputListener())
                if (i == -gridWidth || i == gridWidth || j == -gridWidth || j == gridWidth) {
                    tile.isVisible = false
                } else {
                    tile.touchable = Touchable.enabled
                }

                tiles[location] = tile

                stage.addActor(tile)
            }
        }

        for (i in  -gridWidth..gridWidth) {
            for (j in -gridHeight..gridHeight) {
                val location1 = HexGridLocation(i, j)
                val location2 = HexGridLocation(i + 1, j)
                val location3 = HexGridLocation(i + 1, j + 1)

                if (!tiles.containsKey(location1)) {
                    continue
                }
                if (!tiles.containsKey(location2)) {
                    continue
                }
                if (!tiles.containsKey(location3)) {
                    continue
                }

                val tile1 = tiles[location1]!!
                val tile2 = tiles[location2]!!
                val tile3 = tiles[location3]!!

                if (!cities.containsKey(CityGridLocation(location1, location2, location3))) {
                    val city = CityActor(tile1, tile2, tile3, 10f, shapeRenderer)
                    city.addListener(CityInputListener())
                    city.touchable = Touchable.enabled
                    stage.addActor(city)

                    cities[CityGridLocation(location1, location2, location3)] = city
                }
            }
        }

        for (i in  -gridWidth..gridWidth) {
            for (j in -gridHeight..gridHeight) {
                val location1 = HexGridLocation(i, j)
                val location2 = HexGridLocation(i + 1, j)
                val location3 = HexGridLocation(i, j - 1)

                if (!tiles.containsKey(location1)) {
                    continue
                }
                if (!tiles.containsKey(location2)) {
                    continue
                }
                if (!tiles.containsKey(location3)) {
                    continue
                }

                val tile1 = tiles[location1]!!
                val tile2 = tiles[location2]!!
                val tile3 = tiles[location3]!!

                if (!cities.containsKey(CityGridLocation(location1, location2, location3))) {
                    val city = CityActor(tile1, tile2, tile3, 10f, shapeRenderer)
                    city.addListener(CityInputListener())
                    city.touchable = Touchable.enabled
                    stage.addActor(city)

                    cities[CityGridLocation(location1, location2, location3)] = city
                }
            }
        }

        for (i in  -(gridWidth-1)..(gridWidth-1)) {
            for (j in -(gridHeight-1)..(gridHeight-1)) {
                val location1 = HexGridLocation(i, j)
                val location2 = HexGridLocation(i - 1, j)
                val location3 = HexGridLocation(i, j + 1)
                val location4 = HexGridLocation(i + 1, j + 1)

                if (!tiles.containsKey(location1)) {
                    continue
                }
                if (!tiles.containsKey(location2)) {
                    continue
                }
                if (!tiles.containsKey(location3)) {
                    continue
                }
                if (!tiles.containsKey(location4)) {
                    continue
                }

                val cityLocation1 = CityGridLocation(location1, location2, location3)
                val cityLocation2 = CityGridLocation(location1, location4, location3)

                if (!cities.containsKey(cityLocation1)) {
                    continue
                }
                if (!cities.containsKey(cityLocation2)) {
                    continue
                }

                val city1 = cities[cityLocation1]!!
                val city2 = cities[cityLocation2]!!

                val road = RoadActor(city1, city2, 10f, shapeRenderer)
                road.addListener(CityInputListener())
                road.touchable = Touchable.enabled
                stage.addActor(road)

                roads[RoadGridLocation(cityLocation1, cityLocation2)] = road
            }
        }

//        cam.position.set(0f, 0f, 0f)
//
//        val city0 = Entity()
//        city0.add(CityGridLocation(TileLocationComponent(0f, 0f), TileLocationComponent(1f, 0f), TileLocationComponent(1f, 1f)))
//
//        val city1 = Entity()
//        city1.add(CityGridLocation(TileLocationComponent(-1f, -1f), TileLocationComponent(0f, 0f), TileLocationComponent(0f, -1f)))
//
//        val city2 = Entity()
//        city2.add(CityGridLocation(TileLocationComponent(-1f, -0f), TileLocationComponent(0f, 0f), TileLocationComponent(0f, 1f)))
//
//        val city3 = Entity()
//        city3.add(CityGridLocation(TileLocationComponent(-1f, 0f), TileLocationComponent(-1f, 1f), TileLocationComponent(0f, 1f)))
//
//        val city4 = Entity()
//        city4.add(CityGridLocation(TileLocationComponent(1f, -1f), TileLocationComponent(0f, -1f), TileLocationComponent(1f, 0f)))
//
//        val city5 = Entity()
//        city5.add(CityGridLocation(TileLocationComponent(0f, 0f), TileLocationComponent(-1f, 0f), TileLocationComponent(-1f, -1f)))
//
//        val city6 = Entity()
//        city6.add(CityGridLocation(TileLocationComponent(0f, 0f), TileLocationComponent(0f, 1f), TileLocationComponent(1f, 1f)))
//
//        val city7 = Entity()
//        city7.add(CityGridLocation(TileLocationComponent(0f, 0f), TileLocationComponent(1f, 0f), TileLocationComponent(0f, -1f)))
//
//        engine.addEntity(city0)
//        engine.addEntity(city1)
//        engine.addEntity(city2)
//        engine.addEntity(city3)
//        engine.addEntity(city4)
//        engine.addEntity(city5)
//        engine.addEntity(city6)
//        engine.addEntity(city7)
//
//        val road1 = Entity()
//        road1.add(RoadLocationComponent(city1.getComponent(CityGridLocation::class.java), city5.getComponent(CityGridLocation::class.java)))
//
//        engine.addEntity(road1)
//
//        engine.addSystem(RoadDisplaySystem(shapeRenderer))
//        engine.addSystem(TileDisplaySystem(batch))
//        engine.addSystem(CityDisplaySystem(hexWidth, hexHeight, shapeRenderer))
//        engine.addSystem(TileControlSystem(multiplexer, cam))
//
//        Gdx.input.inputProcessor = multiplexer
//
//        shapeRenderer.setAutoShapeType(true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, .5f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
        shapeRenderer.end()

    }

    override fun dispose() {
        shapeRenderer.dispose()
        hexTexture.dispose()
        stage.dispose()
    }

    fun convertTileLocation(x: Float, y: Float, width: Float, height: Float): Coordinate {
        val newX = (x * width) * (3f/4f)
        val newY = (y * height) - (.5f * height * x)

        return Coordinate(newX, newY)
    }
}
