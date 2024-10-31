package com.example.project1.ui.sreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun LoginScreen(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        LoginForm(navController)
    }
}

//@Preview(showBackground = true)
@Composable
fun LoginForm (navController: NavController) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        ),
        modifier = Modifier
            .padding(40.dp, 0.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(20.dp)
        ) {
            /*
            AsyncImage(
                model = "https://logosmarcas.net/wp-content/uploads/2020/12/GitHub-Logo-650x366.png",
                contentDescription = "Github logo",
                contentScale = ContentScale.Fit
            )*/
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = user,
                maxLines = 1,
                onValueChange = { user = it },
                label = { Text("User") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                maxLines = 1,
                onValueChange = { user = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = { navController.navigate("home") }
            ) {
                Text("LOG IN")
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {}
            ) {
                Text("CREATE AN ACCOUNT")
            }
        }
    }
}