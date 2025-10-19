package com.example.pc012025.presentation.history


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc012025.viewmodel.AuthViewModel
import com.example.pc012025.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(onBack: () -> Unit, auth: AuthViewModel = viewModel(), vm: HistoryViewModel = viewModel()) {
    val uid = auth.uid ?: return
    val items by vm.items.collectAsState()
    LaunchedEffect(uid) { vm.load(uid) }

    Scaffold(topBar = { TopAppBar(title = { Text("Historial") }, navigationIcon = { TextButton(onClick = onBack){ Text("Atrás") } }) }) { p ->
        LazyColumn(Modifier.padding(p).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { c ->
                ElevatedCard(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("${c.amount} ${c.from} → ${c.result} ${c.to}", style = MaterialTheme.typography.titleMedium)
                        Text(vm.fmt(c.timestamp), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
