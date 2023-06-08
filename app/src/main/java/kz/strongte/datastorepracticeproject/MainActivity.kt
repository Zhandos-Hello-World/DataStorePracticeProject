package kz.strongte.datastorepracticeproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import kz.strongte.datastorepracticeproject.datastore.UserPreferencesKeys
import kz.strongte.datastorepracticeproject.ui.theme.DataStorePracticeProjectTheme

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userPrefDataStore = applicationContext.userPrefDataStore
        val factory = MainViewModelFactory(
            userPrefDataStore
        )
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        setContent {
            DataStorePracticeProjectTheme {
                val userName: String by viewModel.name.collectAsState(initial = "Unknown")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var currentUserName by remember { mutableStateOf("") }

                    Column(modifier = Modifier.fillMaxSize()) {
                        Column {
                            Row {
                                TextField(
                                    value = currentUserName,
                                    onValueChange = { currentUserName = it })

                                Button(onClick = {
                                    viewModel.setUserName(currentUserName)
                                    currentUserName = ""
                                }) {

                                    Text(text = "Save")
                                }
                            }
                            Text(text = userName)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataStorePracticeProjectTheme {
        Greeting("Android")
    }
}

val Context.userPrefDataStore: DataStore<Preferences> by preferencesDataStore(
    UserPreferencesKeys.KEY
)
