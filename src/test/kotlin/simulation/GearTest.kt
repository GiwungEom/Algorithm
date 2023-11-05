package simulation

import file.BOJIOReader
import file.Example
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class GearTest {

    private lateinit var gears: Array<Deque<Boolean>>
    private lateinit var example: Example

    @BeforeTest
    fun setUp() {
        example = BOJIOReader().example(name = "BOJ14891.txt")
        example.number = 0
        gears = getGears()
    }

    private fun getGears(): Array<Deque<Boolean>> {
        gears = Array(size = 4) { LinkedList() }
        val bufferedReader = BufferedReader(InputStreamReader(example.inputStream))
        bufferedReader.use {
            repeat(4) { row ->
                val line = it.readLine().toCharArray()
                repeat(8) { col ->
                    gears[row].addLast(line[col] == '1')
                }
            }
        }
        return gears
    }

    @Test
    fun gearRotateTest() {
        BOJ14891().rotateGear(gear = gears[0], way = true)
        assertEquals(false, gears[0].elementAt(0))
        assertEquals(false, gears[0].elementAt(2))
        assertEquals(false, gears[0].elementAt(6))

        BOJ14891().rotateGear(gear = gears[0], way = false)
        assertEquals(false, gears[0].elementAt(0))
        assertEquals(true, gears[0].elementAt(2))
        assertEquals(true, gears[0].elementAt(6))
    }

    @Test
    fun gearRotateConnectionTest() {
        example.number = 1
        gears = getGears()

        var way = false
        var gearNumber = 3 - 1

        with (BOJ14891()) {
            rotateGearConnection(gearNumber, gears, way)
            rotateGear(gear = gears[gearNumber], way = way)
        }

        way = true
        gearNumber = 1 - 1

        with (BOJ14891()) {
            rotateGearConnection(gearNumber, gears, way)
            rotateGear(gear = gears[gearNumber], way = way)
        }

        assertEquals(true, gears[0].elementAt(0))
        assertEquals(true, gears[1].elementAt(0))
        assertEquals(true, gears[2].elementAt(0))
        assertEquals(false, gears[3].elementAt(0))
    }

    @Test
    fun gearTest() {
        example.number = 2
        BOJ14891().gear(example.inputStream, example.outputStream)
        assertEquals(example.getOutput(), example.getResult())
    }
}




















