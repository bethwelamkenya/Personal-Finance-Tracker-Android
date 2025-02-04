package org.example.personalfinancetracker.viewModels

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.personalfinancetracker.AuthRepository
import org.example.personalfinancetracker.utils.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun setEmail(value: String) {
        email.value = value
        validateEmail()
    }

    fun setPassword(value: String) {
        password.value = value
        validatePassword()
    }

    fun login() {
        if (validateForm()) {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            viewModelScope.launch {
                when (val result = authRepository.login(email.value, password.value)) {
                    is Result.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoginSuccessful = true
                        )
                    }

                    is Result.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            error = result.exception.message ?: "Login failed"
                        )
                    }
                }
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    private fun validateForm(): Boolean {
        return validateEmail() && validatePassword()
    }

    private fun validateEmail(): Boolean {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
        _uiState.value = _uiState.value.copy(
            emailError = if (isValid) null else "Invalid email format"
        )
        return isValid
    }

    private fun validatePassword(): Boolean {
        val isValid = password.value.length >= 6
        _uiState.value = _uiState.value.copy(
            passwordError = if (isValid) null else "Password must be at least 6 characters"
        )
        return isValid
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val error: String? = null
)