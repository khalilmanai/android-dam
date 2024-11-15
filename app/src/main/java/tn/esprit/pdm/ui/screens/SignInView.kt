package tn.esprit.pdm.ui.screens

import android.os.Bundle
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tn.esprit.pdm.api.AuthViewModel

@Composable
fun SignInScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Validation functions
    fun validateEmail() {
        emailError = if (!email.contains("@") || !email.contains(".")) {
            "Please enter a valid email address."
        } else {
            ""
        }
    }

    fun validatePassword() {
        passwordError = if (password.length < 8) {
            "Password must be at least 8 characters."
        } else {
            ""
        }
    }

    // Observe access token and error message from the ViewModel
    val accessToken by authViewModel.accessToken
    val errorMessage by authViewModel.errorMessage

    // Handle successful login
    LaunchedEffect(accessToken) {
        accessToken?.let {
            // Save the token or navigate to another screen
            navController.navigate("home") // Replace with your home screen route
        }
    }

    // Handle error message
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            // Show a toast or alert with the error
            println("Error: $it") // Example for debugging
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign In", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Please sign in to continue", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(40.dp))

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
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        if (passwordError.isNotEmpty()) {
            Text(text = passwordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Button
        Button(
            onClick = {
                validateEmail()
                validatePassword()
                if (emailError.isEmpty() && passwordError.isEmpty()) {
                    authViewModel.login(email, password)
                navController.navigate("HomePage")// Trigger login
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link to Register screen
        Text(
            text = "Don't have an account?",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Register Button, navigates to RegisterScreen
        TextButton(onClick = { navController.navigate("register") }) {
            Text(text = "Register", style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    // Call this preview with the navController if needed
}
