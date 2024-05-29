package org.d3if0075.daylog.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import org.d3if0075.daylog.database.CatatanDao
import org.d3if0075.daylog.model.Catatan

class MainViewModel(private val dao: CatatanDao): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val data: StateFlow<List<Catatan>> = searchQuery
        .debounce(300) // Add a debounce to limit the number of searches performed
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                dao.getCatatan().stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000L),
                    initialValue = emptyList()
                )
            } else {
                dao.searchNotes("%$query%").stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000L),
                    initialValue = emptyList()
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun searchNotes(query: String) {
        _searchQuery.value = query
    }
}
