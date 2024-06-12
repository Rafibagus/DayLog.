package org.d3if0075.daylog.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.d3if0075.daylog.database.UserDao

class UserViewModel(private val dao: UserDao): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _signedInUser = dao.getSignedInUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
    )
    val signedInUser: StateFlow<User?> = _signedInUser

    val data: StateFlow<List<User>> = searchQuery
        .debounce(300) // Add a debounce to limit the number of searches performed
        .distinctUntilChanged()
        .flatMapLatest { query ->
            dao.getAllUser().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = emptyList()
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


}