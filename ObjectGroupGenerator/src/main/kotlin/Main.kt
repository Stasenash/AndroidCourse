package main.kotlin

import main.kotlin.Generators.UnicornGroupGenerator
import main.kotlin.Objects.Unicorn

fun main() {
    val unicornGroupGenerator = UnicornGroupGenerator(2, 7)
    val unicornGroup:MutableList<Unicorn> = unicornGroupGenerator.getUnicornGroup()

    for (i in 0 until unicornGroup.count()){
        println("Единорог №" + (i + 1))
        println("Имя: " + unicornGroup[i].name)
        println("Магический рог: " + if (unicornGroup[i].hasMagicHorn) "есть" else "отсутствует (обычный)")
        println("Длина рога: " + "%.2f".format(unicornGroup[i].hornLength) + " см")
        println("Сила радуги: " + unicornGroup[i].rainbowPower + " рогопоинтов")
        println()
    }
}
