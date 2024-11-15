package tn.esprit.pdm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults


@Composable
fun HomePage(navController: NavController, userName: String) {
    // Sample data
    val projects = listOf("Project A", "Project B", "Project C")
    val tasks = listOf("Task 1: Deadline Tomorrow", "Task 2: In Progress", "Task 3: Not Started")
    val notifications = listOf("New comment on Task 1", "Project A due in 2 days", "Task 3 assigned to you")

    // Column layout for the home page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome message
        Text(
            text = "Welcome, $userName!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Recent Projects Section
        SectionTitle("Recent Projects")
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(projects) { project ->
                ProjectCard(project)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ongoing Tasks Section
        SectionTitle("Ongoing Tasks")
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(tasks) { task ->
                TaskCard(task)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications Section
        SectionTitle("Notifications")
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(notifications) { notification ->
                NotificationCard(notification)
            }
        }

        // Floating Action Button to add a new project
        FloatingActionButton(
            onClick = { navController.navigate("add_project") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Project")
        }
    }
}

// Title for each section
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

// Project card composable
@Composable
fun ProjectCard(projectName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Make sure this is properly imported
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = projectName, style = MaterialTheme.typography.bodyLarge)
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Navigate to project")
        }
    }
}

// Task card composable
@Composable
fun TaskCard(taskDescription: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Make sure this is properly imported
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = taskDescription, style = MaterialTheme.typography.bodyMedium)
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Navigate to task")
        }
    }
}

// Notification card composable
@Composable
fun NotificationCard(notification: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Make sure this is properly imported
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = notification, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
