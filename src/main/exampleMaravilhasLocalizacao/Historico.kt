package com.example.maravilhaslocalizacao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Historico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val latitude: Double,
    val longitude: Double
)

