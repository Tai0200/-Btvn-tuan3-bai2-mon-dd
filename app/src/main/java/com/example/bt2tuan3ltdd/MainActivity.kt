package com.example.bt2tuan3ltdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("screen1") { IntroScreen1(navController) }
        composable("screen2") { IntroScreen2(navController) }
        composable("screen3") { IntroScreen3(navController) }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2000) // Hiển thị màn hình Splash trong 2 giây
        navController.navigate("screen1") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "UTH SmartTasks",
                fontSize = 32.sp,
                color = Color(0xFF006EE9),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun IntroScreen1(navController: NavHostController) {
    IntroScreen(
        title = "Easy Time Management",
        description = "Management based on priority and locality, will keep track of the tasks you must do on time.",
        imageResId = R.drawable.bro,
        navController = navController,
        showBackButton = false
    )
}

@Composable
fun IntroScreen2(navController: NavHostController) {
    IntroScreen(
        title = "Increase Work Effectiveness",
        description = "Time management and the determination of more important tasks will help you improve.",
        imageResId = R.drawable.bro1,
        navController = navController,
        showBackButton = true,
        backScreen = "screen1"
    )
}

@Composable
fun IntroScreen3(navController: NavHostController) {
    IntroScreen(
        title = "Reminder Notification",
        description = "The advantage of this application is that it also provides reminders for your assignments.",
        imageResId = R.drawable.bro3,
        navController = navController,
        showBackButton = true,
        backScreen = "screen2"
    )
}

@Composable
fun IntroScreen(
    title: String,
    description: String,
    imageResId: Int,
    navController: NavHostController,
    showBackButton: Boolean,
    backScreen: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp)
                    .padding(top = 250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showBackButton) {
                IconButton(
                    onClick = { navController.navigate(backScreen) },
                    modifier = Modifier.size(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.nut),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(150.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }

            Button(
                onClick = {
                    when (title) {
                        "Easy Time Management" -> navController.navigate("screen2")
                        "Increase Work Effectiveness" -> navController.navigate("screen3")
                        "Reminder Notification" -> {
                            navController.navigate("splash") {
                                popUpTo("screen1") { inclusive = true }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .then(if (!showBackButton) Modifier.fillMaxWidth() else Modifier),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))

            ) {
                Text(
                    text = if (title == "Reminder Notification") "Get Started" else "Next",
                    color = Color.White
                )
            }
        }
    }
}
