package id.aej.myflix.design_system.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.aej.myflix.design_system.R
import id.aej.myflix.design_system.utils.animatedScale

/**
 * Created by dinopriyano on 30/11/23.
 */

@Composable fun FlixButton(
  modifier: Modifier,
  isLoading: Boolean = false,
  enabled: Boolean = true,
  buttonColor: Color = MaterialTheme.colorScheme.primary,
  loadingIndicatorColor: Color = Color.White,
  contentColor: Color = Color.White,
  @StringRes buttonText: Int,
  onClick: () -> Unit
) {
  Button(
    modifier = modifier
      .height(56.dp)
      .animatedScale(enabled),
    onClick = onClick,
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
      contentColor = contentColor,
      containerColor = buttonColor,
      disabledContainerColor = MaterialTheme.colorScheme.secondary,
      disabledContentColor = Color.Black
    )
  ) {
    FlixButtonContent(
      buttonText = stringResource(id = buttonText),
      isLoading = isLoading,
      indicatorColor = loadingIndicatorColor
    )
  }
}

@Composable fun FlixButtonContent(
  buttonText: String,
  isLoading: Boolean,
  indicatorColor: Color
) {
  Layout(
    content = {
      Text(
        modifier = Modifier.layoutId("textButton"),
        text = buttonText,
        style = MaterialTheme.typography.labelLarge.copy(
          fontWeight = FontWeight.SemiBold
        )
      )
      CircularProgressIndicator(
        modifier = Modifier.size(28.dp).layoutId("loadingIndicator"),
        color = indicatorColor
      )
    }
  ) { measureables, constraints ->
    val textPlaceable = measureables.first { it.layoutId == "textButton" }.measure(constraints)
    val loadingIndicatorPlaceable = measureables.first { it.layoutId == "loadingIndicator" }.measure(constraints)

    val layoutWidth = textPlaceable.width.coerceAtLeast(loadingIndicatorPlaceable.width)
    val layoutHeight = textPlaceable.height.coerceAtLeast(loadingIndicatorPlaceable.height)

    layout(layoutWidth, layoutHeight) {
      if (isLoading) {
        val indicatorX = (layoutWidth - loadingIndicatorPlaceable.width) / 2
        val indicatorY = (layoutHeight - loadingIndicatorPlaceable.height) / 2
        loadingIndicatorPlaceable.placeRelative(x = indicatorX, y = indicatorY)
      } else {
        val textX = (layoutWidth - textPlaceable.width) / 2
        val textY = (layoutHeight - textPlaceable.height) / 2
        textPlaceable.placeRelative(x = textX, y = textY)
      }
    }
  }
}

@Preview @Composable fun FlixButtonPreview() {
  FlixButton(modifier = Modifier.width(200.dp), buttonText = R.string.email_txt) {
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
    modifier = modifier
      .clip(CircleShape)
      .background(buttonColor)
      .size(44.dp)
      .animatedScale(),
    onClick = onClick,
    colors = IconButtonDefaults.iconButtonColors(
      contentColor = contentColor,
      containerColor = buttonColor
    )
  ) {
    Icon(painter = painterResource(id = icon), contentDescription = null)
  }
}