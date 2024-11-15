package tn.esprit.pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.pdm.ui.screens.SignInScreen
import tn.esprit.pdm.ui.screens.RegisterScreen
import tn.esprit.pdm.ui.screens.WelcomeView
import tn.esprit.pdm.ui.theme.PDMTheme
import tn.esprit.pdm.ui.screens.HomePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PDMTheme {
                // Wrap your navigation setup inside a NavHost
                Surface(modifier = androidx.compose.ui.Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeView(navController = navController) // Pass navController to WelcomeView
                        }
                        composable("register") {
                            RegisterScreen(navController = navController) // Pass navController to RegisterScreen
                        }
                        composable("signin") {
                            SignInScreen(navController = navController) // Pass navController to SignInScreen
                        }
                        composable("homepage"){
                            HomePage(navController = navController , userName = "medkhalil")
                        }
                    }
                }
            }
        }
    }
}
