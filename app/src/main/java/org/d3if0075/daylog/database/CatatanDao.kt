package org.d3if0075.daylog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0075.daylog.model.Catatan

@Dao
interface CatatanDao {
    @Insert
    suspend fun insert(catatan: Catatan)

    @Update
    suspend fun update(catatan: Catatan)

    @Query("SELECT * FROM catatan ORDER BY id DESC")
    fun getCatatan(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan WHERE id = :id")
    suspend fun getCatatanById(id:Long): Catatan?

    @Query("DELETE FROM catatan WHERE id = :id")
    suspend fun deleteById(id:Long)

    @Query("SELECT * FROM catatan WHERE judul LIKE :query OR catatan LIKE :query ORDER BY id DESC")
    fun searchNotes(query: String): Flow<List<Catatan>>
}