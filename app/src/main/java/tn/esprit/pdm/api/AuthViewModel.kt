package tn.esprit.pdm.api

import AuthResponse
import LoginRequest
import SignUpRequest
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {

    var accessToken = mutableStateOf<String?>(null)
    var errorMessage = mutableStateOf<String?>(null)
    var isLoading = mutableStateOf(false)

    fun signUp(email: String, username: String, password: String) {
        val signUpRequest = SignUpRequest(email, username, password)

        isLoading.value = true
        RetrofitClient.authService.signUp(signUpRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                isLoading.value = false
                if (response.isSuccessful) {
                    accessToken.value = response.body()?.accessToken
                } else {
                    errorMessage.value = "Sign-up failed. Please try again."
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                isLoading.value = false
                errorMessage.value = "Failed to connect to server. Please check your internet connection."
            }
        })
    }

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        isLoading.value = true
        RetrofitClient.authService.login(loginRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                isLoading.value = false
                if (response.isSuccessful) {
                    accessToken.value = response.body()?.accessToken

                } else {
                    errorMessage.value = "Invalid credentials. Please try again."
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                isLoading.value = false
                errorMessage.value = "Failed to connect to server. Please check your internet connection."
            }
        })
    }
}
