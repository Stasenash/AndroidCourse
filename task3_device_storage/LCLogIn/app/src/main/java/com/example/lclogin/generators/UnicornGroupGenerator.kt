package main.kotlin.generators

import com.example.lclogin.models.Unicorn
import kotlin.random.Random

class UnicornGroupGenerator constructor(minUnicornCount: Int, maxUnicornCount: Int){
    private val unicornCountRandom = (minUnicornCount..maxUnicornCount).random()

    private fun randomName(): String = List(8) {
        (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
    }.joinToString("")

    private fun randomLength(): Double {
        return Random.nextDouble(0.0, 50.0)
    }

    private fun randomBool(): Boolean {
        return Random.nextBoolean()
    }

    private fun randomPower(): Int {
        return Random.nextInt(0, 100)
    }

    fun getUnicornGroup(): MutableList<Unicorn> {
        val unicorns: MutableList<Unicorn> = mutableListOf()
        for (i in 1..unicornCountRandom){
            unicorns.add(Unicorn(randomName(), randomBool(), randomLength(), randomPower()))
        }
        return unicorns
    }
}