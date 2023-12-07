package id.aej.myflix.design_system.presentation.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import id.aej.myflix.design_system.domain.model.PartialClickableTextItems
import id.aej.myflix.design_system.domain.model.PartialClickableTextType

/**
 * Created by dinopriyano on 30/11/23.
 */

@Composable fun FlixPartialClickableText(
  modifier: Modifier,
  textStyle: TextStyle,
  items: List<PartialClickableTextItems>
) {
  val annotatedString = buildAnnotatedString {
    items.forEachIndexed { index, item ->
      pushStringAnnotation(tag = item.text, annotation = item.text)
      withStyle(
        style = if (item.type == PartialClickableTextType.NORMAL) {
          SpanStyle(color = item.color)
        } else {
          SpanStyle(
            color = item.color, fontWeight = FontWeight.SemiBold
          )
        }
      ) {
        append(item.text)
      }
      pop()
      if (index != items.size -1) {
        append(" ")
      }
    }
  }

  ClickableText(
    modifier = modifier,
    text = annotatedString,
    style = textStyle,
    onClick = { offset ->
      annotatedString.getStringAnnotations(offset, offset)
        .firstOrNull()?.let { span ->
          val textPart = items.find { it.text == span.item }
          textPart?.onClick?.invoke()
        }
    }
  )
}