package org.d3if0075.daylog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val userName: String,
    val email: String,
    val password: String,
    val confirmation: String,
    val signedIn:Boolean,
)

