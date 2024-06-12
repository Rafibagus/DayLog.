package org.d3if0075.daylog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0075.daylog.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user ORDER BY userName")
    fun getAllUser(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): User?

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("UPDATE user SET password = :newPassword WHERE email = :email")
    suspend fun updatePasswordByEmail(email: String, newPassword:String)

    @Query("SELECT * FROM user WHERE signedIn = 1 LIMIT 1")
    fun getSignedInUser():Flow<User?>

}

