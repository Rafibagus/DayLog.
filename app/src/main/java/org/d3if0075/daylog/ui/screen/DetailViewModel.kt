package org.d3if0075.daylog.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0075.daylog.model.Catatan

class DetailViewModel : ViewModel() {
    fun getCatatan(id: Long): Catatan {
        return Catatan(
            id,
            "Kuliah Mobpro $id Feb",
            "Asikk, hari ini belajar membuat aplikasi Android counter dan berhasil. hehe.. mudah mudahan modul selanjutnya juga lancar. Aamiin",
        )
    }
}