package id.aej.myflix.design_system.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import id.aej.myflix.design_system.presentation.theme.Gray
import id.aej.myflix.design_system.presentation.theme.Gray15

/**
 * Created by dinopriyano on 27/11/23.
 */

@Composable fun FlixTextField(
  modifier: Modifier,
  input: InputWrapper<String>,
  keyboardOptions: KeyboardOptions,
  visualTransformation: VisualTransformation,
  trailingIcon: @Composable () -> Unit = {},
  @StringRes label: Int,
  @StringRes placeholder: Int,
  onValueChange: (String) -> Unit
) {
  Column (modifier = modifier) {
    // text field label
    Text(
      text = stringResource(id = label),
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.secondary
    )

    //text field
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp),
      value = input.value,
      placeholder = {
        Text(
          text = stringResource(id = placeholder),
          style = MaterialTheme.typography.labelMedium,
          color = Gray
        )
      },
      shape = RoundedCornerShape(10.dp),
      singleLine = true,
      keyboardOptions = keyboardOptions,
      textStyle = MaterialTheme.typography.labelMedium,
      trailingIcon = trailingIcon,
      visualTransformation = visualTransformation,
      colors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
        focusedContainerColor = Gray15,
        unfocusedContainerColor = Gray15,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      ),
      onValueChange = onValueChange
    )

    // error label
    if (input.error != null && input.value.isNotEmpty()) {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = input.error,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyMedium
      )
    }
  }
}