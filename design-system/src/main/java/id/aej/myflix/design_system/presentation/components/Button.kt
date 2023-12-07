package id.aej.myflix.design_system.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.aej.myflix.design_system.utils.animatedScale

/**
 * Created by dinopriyano on 30/11/23.
 */

@Composable fun FlixButton(
  modifier: Modifier,
  buttonColor: Color = MaterialTheme.colorScheme.primary,
  contentColor: Color = Color.White,
  @StringRes buttonText: Int,
  onClick: () -> Unit
) {
  Button(
    modifier = modifier.height(56.dp).animatedScale(),
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
      contentColor = contentColor,
      containerColor = buttonColor
    )
  ) {
    Text(
      text = stringResource(id = buttonText),
      style = MaterialTheme.typography.labelLarge.copy(
        fontWeight = FontWeight.SemiBold
      )
    )
  }
}

@Composable fun FlixIconButton(
  modifier: Modifier,
  buttonColor: Color = MaterialTheme.colorScheme.primary,
  contentColor: Color = Color.White,
  @DrawableRes icon: Int,
  onClick: () -> Unit
) {
  IconButton(
    modifier = modifier.clip(CircleShape).background(buttonColor).size(44.dp).animatedScale(),
    onClick = onClick,
    colors = IconButtonDefaults.iconButtonColors(
      contentColor = contentColor,
      containerColor = buttonColor
    )
  ) {
    Icon(painter = painterResource(id = icon), contentDescription = null)
  }
}