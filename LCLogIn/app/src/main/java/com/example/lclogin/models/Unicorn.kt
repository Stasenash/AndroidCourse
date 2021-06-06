package com.example.lclogin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unicorn")
data class Unicorn(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "hasMagicHorn") val hasMagicHorn: Boolean,
    @ColumnInfo(name = "hornLength") val hornLength: Double,
    @ColumnInfo(name = "rainbowPower") val rainbowPower: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    override fun toString(): String {
        val magicHornText = if (hasMagicHorn) "есть" else "отсутствует (обычный)"
        val unicornStr = "Имя: " + name + "\n" +
                "Магический рог: " + magicHornText + "\n" +
                "Длина рога: " + "%.2f".format(hornLength) + " см" + "\n" +
                "Сила радуги: " + rainbowPower + " рогопоинтов"
        return unicornStr
    }
}