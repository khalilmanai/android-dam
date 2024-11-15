import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class SignUpRequest(val email: String, val username: String, val password: String)
data class LoginRequest(val email: String, val password: String)
data class AuthResponse(val accessToken: String)

interface AuthService {

    @POST("auth/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<AuthResponse>

    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>

}
