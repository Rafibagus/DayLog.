package org.d3if0075.daylog.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "catatan")
data class Catatan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val catatan: String,
    var mood: Int
)


