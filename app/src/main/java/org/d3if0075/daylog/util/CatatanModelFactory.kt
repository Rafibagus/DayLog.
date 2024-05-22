package org.d3if0075.daylog.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0075.daylog.database.CatatanDao
import org.d3if0075.daylog.ui.screen.DetailViewModel
import org.d3if0075.daylog.ui.screen.MainViewModel

class CatatanModelFactory(private val catatanDao: CatatanDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(catatanDao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(catatanDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}