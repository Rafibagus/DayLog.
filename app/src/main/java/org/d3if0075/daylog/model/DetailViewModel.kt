package org.d3if0075.daylog.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0075.daylog.database.CatatanDao
import org.d3if0075.daylog.model.Catatan

class DetailViewModel(private val dao: CatatanDao) : ViewModel() {

    fun insert(judul: String, catatan: String, mood: Int){
        val catatan = Catatan(
            judul = judul,
            catatan = catatan,
            mood = mood
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(catatan)
        }
    }

    suspend fun getCatatan(id: Long): Catatan? {
        return dao.getCatatanById(id)
//            id,
//            "Kuliah Mobpro $id Feb",
//            "Asikk, hari ini belajar membuat aplikasi Android counter dan berhasil. hehe.. mudah mudahan modul selanjutnya juga lancar. Aamiin",
//        )
    }

    fun update(id: Long, judul: String, catatan: String, mood: Int){
        val catatan = Catatan(
            id = id,
            judul = judul,
            catatan = catatan,
            mood = mood
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(catatan)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}