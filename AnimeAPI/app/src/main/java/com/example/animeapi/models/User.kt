package com.example.animeapi.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    override fun toString(): String {
        val userStr = "Логин: " + login + "\n" +
                "E-mail: " + email + "\n"
        return userStr
    }
}