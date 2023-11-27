package id.aej.myflix.auth.impl.presentation.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Created by dinopriyano on 27/11/23.
 */

@Composable fun LoginScreen(
  onToRegister: () -> Unit
) { 
  Column {
    Text(text = "Login Screen")
    Button(onClick = { onToRegister() }) {    
      Text(text = "Go to register screen")
    }
  }
}