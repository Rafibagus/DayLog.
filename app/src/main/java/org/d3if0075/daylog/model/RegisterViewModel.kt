package org.d3if0075.daylog.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0075.daylog.database.UserDao

class RegisterViewModel(private val dao: UserDao): ViewModel() {
    suspend fun register(userName:String, email: String, password: String): Boolean {
        val user = dao.getUserByEmail(email)
        if (user == null){
            val user = User(
                userName = userName,
                email = email,
                password = password,
                signedIn = false
            )
            viewModelScope.launch(Dispatchers.IO) {
                dao.insert(user)
            }
            return true
        }
        return false
    }
}