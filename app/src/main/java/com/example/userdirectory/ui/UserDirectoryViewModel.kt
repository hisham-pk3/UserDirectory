package com.example.userdirectory.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.data.UserRepository
import com.example.userdirectory.data.local.User
import com.example.userdirectory.data.local.UserDatabase
import com.example.userdirectory.data.remote.RetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserDirectoryUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val users: List<User> = emptyList(),
    val searchQuery: String = ""
)

class UserDirectoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    private val _uiState = MutableStateFlow(UserDirectoryUiState())
    val uiState: StateFlow<UserDirectoryUiState> = _uiState.asStateFlow()

    private var usersJob: Job? = null

    init {
        val db = UserDatabase.getInstance(application)
        val api = RetrofitInstance.api
        repository = UserRepository(api, db.userDao())

        observeUsers()
        refreshUsers()
    }

    private fun observeUsers(query: String = "") {
        usersJob?.cancel()
        usersJob = viewModelScope.launch {
            val flow = repository.searchUsers(query)
            flow.collect { users ->
                _uiState.update { it.copy(users = users) }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        observeUsers(query)
    }

    fun refreshUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                repository.refreshUsers()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = "Failed to refresh users (offline?): ${e.message}"
                    )
                }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
