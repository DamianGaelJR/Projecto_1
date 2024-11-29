package com.example.project1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel: ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = RetrofitClient.api.loginUser(username, password)

                if (response.isSuccessful) {
                    val loginResponse = response.body()

                    if (loginResponse != null) {
                        _loginState.value = LoginState.Success(loginResponse) // Actualizamos el estado a éxito
                    } else {
                        _loginState.value = LoginState.Error("Invalid response from server") // Si la respuesta está vacía
                    }
                } else {
                    _loginState.value = LoginState.Error("Login failed: ${response.message()}")
                }
            } catch (e: HttpException) {
                _loginState.value = LoginState.Error("Login failed: ${e.response()?.errorBody()?.string() ?: "Unknown error"}")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Network error occurred")
            }
        }
    }


    fun resetState() {
        _loginState.value = LoginState.Initial
    }
}

sealed class LoginState {
    object Initial : LoginState()
    object Loading : LoginState()
    data class Success(val response: Any) : LoginState()
    data class Error(val message: String) : LoginState()
}
