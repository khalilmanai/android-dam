package tn.esprit.pdm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tn.esprit.pdm.api.AuthViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    // Validation functions
    fun validateEmail() {
        emailError = if (!email.contains("@") || !email.contains(".")) {
            "Please enter a valid email address."
        } else {
            ""
        }
    }

    fun validatePassword() {
        passwordError = when {
            password.length < 8 -> "Password must be at least 8 characters."
            !password.any { it.isUpperCase() } -> "Password must contain at least one uppercase letter."
            else -> ""
        }
    }

    fun validateConfirmPassword() {
        confirmPasswordError = if (confirmPassword != password) {
            "Passwords do not match."
        } else {
            ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "We help you manage your project efficiently", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(40.dp))

        // Full Name Input
        TextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Enter your full name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Email Input
        TextField(
            value = email,
            onValueChange = {
                email = it
                validateEmail()
            },
            label = { Text("Enter your email") },
            isError = emailError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        if (emailError.isNotEmpty()) {
            Text(text = emailError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Password Input
        TextField(
            value = password,
            onValueChange = {
                password = it
                validatePassword()
            },
            label = { Text("Enter password") },
            isError = passwordError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
        if (passwordError.isNotEmpty()) {
            Text(text = passwordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Confirm Password Input
        TextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                validateConfirmPassword()
            },
            label = { Text("Confirm password") },
            isError = confirmPasswordError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        if (confirmPasswordError.isNotEmpty()) {
            Text(text = confirmPasswordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Register Button
        Button(
            onClick = {
                validateEmail()
                validatePassword()
                validateConfirmPassword()

                if (emailError.isEmpty() && passwordError.isEmpty() && confirmPasswordError.isEmpty()) {
                    authViewModel.signUp(email, fullName, password)  // Trigger registration
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link to Sign In screen
        Text(
            text = "Already have an account?",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Sign In Button, navigates to SignInScreen
        TextButton(onClick = { navController.navigate("signin") }) {
            Text(text = "Sign In", style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary))
        }
    }

    // Observe login result
    val accessToken = authViewModel.accessToken.value
    val errorMessage = authViewModel.errorMessage.value

    // Handle login success or error
    LaunchedEffect(accessToken) {
        if (accessToken != null) {
            // Navigate to homepage on successful login
            navController.navigate("homePage")  // Replace with your desired route
        }
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage?.isNotEmpty() == true) {
            // Handle error (e.g., show a Toast or Snackbar)
            println("Error: $errorMessage")  // Replace with actual error handling
        }
    }
}
