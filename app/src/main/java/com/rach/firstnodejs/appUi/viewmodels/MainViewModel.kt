package com.rach.firstnodejs.appUi.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rach.firstnodejs.api.User
import com.rach.firstnodejs.appUi.repository.ResponseUi
import com.rach.firstnodejs.appUi.repository.UserRepository
import com.rach.firstnodejs.models.HomeUiEvent
import com.rach.firstnodejs.models.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var state by mutableStateOf(HomeUiState())
        private set

    fun onEvent(event: HomeUiEvent) {

        when (event) {
            is HomeUiEvent.EnterUserNameChanged -> {
                state = state.copy(enteredName = event.value)
            }

            is HomeUiEvent.EnterEmailChanged -> {
                state = state.copy(enteredEmail = event.value)
            }

            is HomeUiEvent.EnterRoleChanged -> {
                state = state.copy(
                    enterRole = event.value
                )
            }
        }

    }

    fun getAllUser() {

        viewModelScope.launch {
           state = state.copy(isLoading = true)
            val result = userRepository.getAllUsers()

            handleResult(result){userList ->
                state = state.copy(
                    users = userList,
                    isLoading = false,
                    errorMessage = null
                )

            }
        }

    }

    fun createUser() {
        val user = User(
            name = state.enteredName,
            role = state.enterRole,
            email = state.enteredEmail
        )
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = userRepository.createUser(user)

            handleResult(result){

            }
        }

    }


    private inline fun <T> handleResult(
        result: ResponseUi<T>,
        onSuccess: (T) -> Unit
    ) {
        state = when (result) {
            is ResponseUi.Success -> {
                onSuccess(result.data!!)
                state.copy(isLoading = false, errorMessage = null)
            }

            is ResponseUi.Error -> {

                state.copy(isLoading = false, errorMessage = result.message)

            }

            is ResponseUi.Loading -> {
                state.copy(isLoading = true)
            }

        }
    }


}