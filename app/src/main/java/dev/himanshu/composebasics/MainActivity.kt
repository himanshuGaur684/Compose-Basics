package dev.himanshu.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.himanshu.composebasics.ui.theme.ComposeBasicsTheme

val LocalUserSession = compositionLocalOf<UserSession> { UserSession("", false) }


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                CompositionLocalProvider(LocalUserSession provides UserSession("Himanshu", true)) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        DashboardScreen(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }
                }

            }
        }
    }
}


data class UserSession(
    val name: String,
    val isLoggedIn: Boolean
)

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Greeting(modifier)
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val userSession = LocalUserSession.current
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("User Session -> ${userSession.name}  ${userSession.isLoggedIn}")
    }
}



