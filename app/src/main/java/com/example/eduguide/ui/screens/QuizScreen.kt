package com.example.eduguide.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eduguide.model.Question

@Composable
fun QuizScreen(
    question: Question?,
    onEngageClick: () -> Unit,
    onSkipClick: () -> Unit,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (question != null) {
            Text(question.text, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onEngageClick) {
                Text("I'm interested")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onSkipClick) {
                Text("Skip")
            }
        } else {
            Text("All done!", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            Button(onClick = onFinishClick) {
                Text("Finish Quiz")
            }
        }
    }
}
