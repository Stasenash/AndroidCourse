package main.kotlin.objects

class Unicorn constructor(val name: String, val hasMagicHorn: Boolean, val hornLength: Double, val rainbowPower: Int){

    override fun toString(): String {
        val magicHornText = if (hasMagicHorn) "есть" else "отсутствует (обычный)"
        val unicornStr = "Имя: " + name + "\n" +
                         "Магический рог: " + magicHornText + "\n" +
                         "Длина рога: " + "%.2f".format(hornLength) + " см" + "\n" +
                         "Сила радуги: " + rainbowPower + " рогопоинтов"
        return unicornStr
    }
}