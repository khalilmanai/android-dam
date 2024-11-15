package tn.esprit.pdm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import tn.esprit.pdm.R

// WelcomeView.kt
@Composable
fun WelcomeView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray background for a modern look
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image section with appropriate padding to center the elements
        Image(
            painter = painterResource(id = R.drawable.firstscreen), // Use the correct drawable resource
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 24.dp) // Space below the image
        )

        // Title Text with larger spacing above and below
        Text(
            text = "Smart Track",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Subtitle Text with spacing adjustments
        Text(
            text = "Manage, Plan, achieve",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Flexible space between text and button

        // Get Started Button with custom corner radius
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth(0.7f) // 70% width of the screen for a balanced look
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A7ADB)),
            shape = RoundedCornerShape(5.dp) // Set custom corner radius to 5dp
        ) {
            Text(text = "Get Started", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(32.dp)) // Bottom padding
    }
}
