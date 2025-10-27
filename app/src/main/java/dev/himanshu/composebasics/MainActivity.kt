package dev.himanshu.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.himanshu.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExampleScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Immutable
data class UiState(
    val counter: Int,
    val user: User
)

@Immutable
data class User(
    val name: String,
    val age: Int
)


data object UiStateSaver : Saver<UiState, Any> {
    override fun restore(value: Any): UiState? {
        val list = value as List<*>
        return UiState(
            counter = list.first() as Int,
            user = User(
                name = list[1] as String,
                age = list[2] as Int
            )
        )
    }

    override fun SaverScope.save(value: UiState): Any? {
        return listOf(
            value.counter,
            value.user.name,
            value.user.age
        )
    }
}

@Composable
fun ExampleScreen(modifier: Modifier = Modifier) {

    var counter by rememberSaveable(stateSaver = UiStateSaver) {
        mutableStateOf(
            UiState(
                0,
                User("Himanhsu", age = 25)
            )
        )
    }

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(counter.toString())
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            counter = counter.copy(counter = counter.counter.plus(1))
        }) {
            Text("Increase counter")
        }

    }


}