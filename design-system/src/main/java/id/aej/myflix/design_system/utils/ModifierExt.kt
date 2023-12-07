package id.aej.myflix.design_system.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Created by dinopriyano on 30/11/23.
 */

fun Modifier.animatedScale() = composed {
  var selected by remember {
    mutableStateOf(false)
  }
  val scale by animateFloatAsState(if (selected) 0.8f else 1f, label = "")

  this.let {
    this.scale(scale)
      .pointerInput(selected) {
        awaitPointerEventScope {
          selected = if (selected) {
            waitForUpOrCancellation()
            false
          } else {
            awaitFirstDown(false)
            true
          }
        }
      }
  }
}