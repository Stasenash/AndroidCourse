package main.kotlin

import main.kotlin.Generators.UnicornGroupGenerator
import main.kotlin.Objects.Unicorn

fun main() {
    val unicornGroupGenerator = UnicornGroupGenerator(2, 7)
    val unicornGroup:MutableList<Unicorn> = unicornGroupGenerator.getUnicornGroup()

    for (i in 0 until unicornGroup.count()){
        println("Единорог №" + (i + 1))
        println(unicornGroup[i])
        println()
    }
}
