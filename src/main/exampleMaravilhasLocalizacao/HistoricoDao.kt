package com.example.maravilhaslocalizacao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoricoDao {
    @Insert
    suspend fun inserir(historico: Historico)

    @Query("SELECT * FROM Historico")
    suspend fun obterHistorico(): List<Historico>
}
