package com.example.eduguide.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eduguide.model.Domain

@Composable
fun ResultsScreen(
    engagementCounts: Map<Domain, Int>,
    onRestartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your Results", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        // Raw counts for now; Phase 4 turns these into percentages
        engagementCounts.forEach { (domain, count) ->
            Text("${domain.name}: $count")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRestartClick) {
            Text("Start Over")
        }
    }
}
