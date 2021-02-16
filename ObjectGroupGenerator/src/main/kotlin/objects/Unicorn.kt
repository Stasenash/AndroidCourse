package main.kotlin.objects

class Unicorn constructor(val name: String, val hasMagicHorn: Boolean, val hornLength: Double, val rainbowPower: Int){

    override fun toString(): String {
        val unicornStr = "Имя: " + name + "\n" +
                         "Магический рог: " + if (hasMagicHorn) "есть" else "отсутствует (обычный)" + "\n" +
                         "Длина рога: " + "%.2f".format(hornLength) + " см" + "\n" +
                         "Сила радуги: " + rainbowPower + " рогопоинтов"
        return unicornStr
    }
}