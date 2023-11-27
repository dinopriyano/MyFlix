package id.aej.myflix.design_system.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import id.aej.myflix.design_system.domain.model.InputWrapper
import id.aej.myflix.design_system.presentation.theme.Gray15

/**
 * Created by dinopriyano on 27/11/23.
 */

@OptIn(ExperimentalMaterial3Api::class) @Composable fun FlixTextField(
  modifier: Modifier,
  input: InputWrapper<String>,
  keyboardOptions: KeyboardOptions,
  visualTransformation: VisualTransformation,
  @StringRes label: Int
) {
  Column (modifier = modifier) {
    // text field label
    Text(
      text = stringResource(id = label),
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.secondary
    )
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp),
      value = input.value,
      shape = RoundedCornerShape(10.dp),
      singleLine = true,
      keyboardOptions = keyboardOptions,
      visualTransformation = visualTransformation,
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.White,
        placeholderColor = MaterialTheme.colorScheme.secondary,
        containerColor = Gray15
      ),
      onValueChange = {

      }
    )
  }
}