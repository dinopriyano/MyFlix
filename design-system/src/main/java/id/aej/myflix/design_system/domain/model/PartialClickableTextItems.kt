package id.aej.myflix.design_system.domain.model

import androidx.compose.ui.graphics.Color

/**
 * Created by dinopriyano on 30/11/23.
 */

enum class PartialClickableTextType {
  NORMAL, CLICKABLE
}

data class PartialClickableTextItems(
  val text: String,
  val color: Color,
  val type: PartialClickableTextType = PartialClickableTextType.NORMAL,
  val onClick: (() -> Unit) = {}
)