package com.example.pc012025.presentation.auth


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc012025.viewmodel.AuthViewModel


@Composable
fun LoginScreen(onLogged: () -> Unit, vm: AuthViewModel = viewModel()) {
    val state by vm.state.collectAsState()
    LaunchedEffect(state.isLoggedIn) { if (state.isLoggedIn) onLogged() }

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Inicio de sesión", style = MaterialTheme.typography.headlineSmall)
            OutlinedTextField(email, { email = it }, label = { Text("Correo") }, singleLine = true)
            OutlinedTextField(pass, { pass = it }, label = { Text("Contraseña") },
                singleLine = true, visualTransformation = PasswordVisualTransformation())
            Button(onClick = { vm.login(email.trim(), pass) }, enabled = !state.isLoading, modifier = Modifier.fillMaxWidth()) {
                Text("Iniciar sesión")
            }
            TextButton(onClick = { vm.register(email.trim(), pass) }, enabled = !state.isLoading) {
                Text("¿No tienes cuenta? Registrarte")
            }
            state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
}
