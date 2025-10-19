package com.example.pc012025.presentation.conversion


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc012025.viewmodel.AuthViewModel
import com.example.pc012025.viewmodel.ConversionViewModel
import com.example.pc012025.viewmodel.RatesViewModel


@Composable
fun ConversionScreen(
    onHistory: () -> Unit,
    auth: AuthViewModel = viewModel(),
    ratesVm: RatesViewModel = viewModel(),
    vm: ConversionViewModel = viewModel()
) {
    val rates by ratesVm.rates.collectAsState()
    val ui by vm.ui.collectAsState()
    val uid = auth.uid ?: return
    val currencies = remember(rates) { rates.keys.sorted() }

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Conversor de Divisas", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(ui.amountText, vm::updateAmount, label = { Text("Monto") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        CurrencyDropdown("De", currencies, ui.from, vm::updateFrom)
        CurrencyDropdown("A", currencies, ui.to, vm::updateTo)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.convertAndSave(uid, rates) }, enabled = currencies.isNotEmpty()) { Text("Convertir") }
            OutlinedButton(onClick = onHistory) { Text("Historial") }
            OutlinedButton(onClick = { auth.logout() }) { Text("Salir") }
        }
        if (ui.resultText.isNotBlank()) { Divider(); Text(ui.resultText, style = MaterialTheme.typography.bodyLarge) }
    }
}

@Composable
private fun CurrencyDropdown(label:String, options:List<String>, selected:String, onSelect:(String)->Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text(label, style = MaterialTheme.typography.labelLarge)
        OutlinedTextField(
            value = selected, onValueChange = {}, readOnly = true,
            modifier = Modifier.fillMaxWidth().clickable { expanded = true }, label = { Text(label) }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { c -> DropdownMenuItem(text = { Text(c) }, onClick = { onSelect(c); expanded = false }) }
        }
    }
}
