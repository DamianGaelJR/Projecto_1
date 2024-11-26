package com.example.project1

import android.graphics.Picture
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.project1.ui.theme.Project1Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project1.data.database.AppDatabase
import com.example.project1.data.database.DatabaseProvider
import com.example.project1.ui.sreens.Components
import com.example.project1.ui.sreens.HomeScreen
import com.example.project1.ui.sreens.LoginScreen
import com.example.project1.ui.sreens.ManageServiceScreen
import com.example.project1.ui.sreens.MenuScreen

class MainActivity : ComponentActivity() {
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            database = DatabaseProvider.getDatabase(this)
            Log.d("DB", "Database loaded succesfully")
        } catch (exception: Exception) {
            Log.d("DB", "error $exception")
        }
        enableEdgeToEdge()
        setContent {
            ComposeMultiScreenApp()
        }
    }
}

@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    Surface(color = Color.White) {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login"){
        composable("menu") { MenuScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("componentes") { Components(navController)}
        composable("login") { LoginScreen(navController)}
        composable("manage-service/{serviceId}") { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            ManageServiceScreen(navController, serviceId = serviceId)
        }
    }
}