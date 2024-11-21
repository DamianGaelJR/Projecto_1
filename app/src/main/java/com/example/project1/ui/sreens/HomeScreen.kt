package com.example.project1.ui.sreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project1.R
import com.example.project1.data.controller.ServiceViewModel
import com.example.project1.data.model.ServiceModel
import com.example.project1.ui.components.ServiceCard
import com.example.project1.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ServiceViewModel = viewModel()) {
    var serviceDetail by remember { mutableStateOf<ServiceModel?>(null) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar("Password Manager", navController, false) },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White,
            ) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorResource(R.color.primary_button),
                contentColor = Color.Black,
                onClick = {
                    navController.navigate("manage-service/0")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        var services by remember { mutableStateOf<List<ServiceModel>>(emptyList()) }
        if (services.isEmpty()) {
            CircularProgressIndicator()
        }
        LaunchedEffect(Unit) {
            viewModel.getServices { response ->
                if (response.isSuccessful) {
                    services = response.body() ?: emptyList()
                } else {
                    println("failed to load posts")
                }
            }
        }

        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(R.color.black))
                .fillMaxSize(),
            state = listState
        ) {
            items(services) { service ->
                ServiceCard(service.id, service.name, service.username, service.imageURL,
                    onButtonClick = {
                        viewModel.showServices(service.id) { response ->
                            if (response.isSuccessful) {
                                serviceDetail = response.body()
                            }
                        }
                        showBottomSheet = true
                    }
                )
            }
        }
    }
}
