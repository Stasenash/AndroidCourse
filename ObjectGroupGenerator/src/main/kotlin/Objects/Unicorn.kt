package main.kotlin.Objects

class Unicorn constructor(name: String, hasMagicHorn: Boolean, hornLength: Double, rainbowPower: Int){
    var name = name
    var hasMagicHorn = hasMagicHorn
    var hornLength = hornLength
    var rainbowPower = rainbowPower

    override fun toString(): String {
        val unicornStr = "Имя: " + name + "\n" +
                         "Магический рог: " + if (hasMagicHorn) "есть" else "отсутствует (обычный)" + "\n" +
                         "Длина рога: " + "%.2f".format(hornLength) + " см" + "\n" +
                         "Сила радуги: " + rainbowPower + " рогопоинтов"
        return unicornStr
    }
}