package org.d3if0075.daylog.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import org.d3if0075.daylog.database.UserDao
import org.d3if0075.daylog.model.User

class LoginViewModel(private val dao: UserDao): ViewModel() {
    suspend fun checkIsSignedIn(): Boolean {
        val users: List<User> = dao.getAllUser().single()
        return users.any { it.signedIn }
    }

    suspend fun login(email: String, password: String): Boolean {
        val user = dao.getUserByEmail(email)
        if (user != null && user.password == password) {
            dao.update(user.copy(signedIn = true)) // Assuming there's an update method
            return true
        }
        return false
    }

    suspend fun getCurrentUser(): Flow<User?> {
        return dao.getSignedInUser()
        }
}