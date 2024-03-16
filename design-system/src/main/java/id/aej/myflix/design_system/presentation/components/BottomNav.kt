package id.aej.myflix.design_system.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.roundToInt

/**s
 * Created by dinopriyano on 14/12/23.
 */

@Composable
fun RowScope.FlixBottomNavigationItem(
  selected: Boolean,
  onClick: () -> Unit,
  icon: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  label: @Composable (() -> Unit)? = null,
  alwaysShowLabel: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  selectedContentColor: Color = LocalContentColor.current,
  unselectedContentColor: Color = selectedContentColor
) {
  val styledLabel: @Composable (() -> Unit)? = label?.let {
    @Composable {
      val style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center)
      ProvideTextStyle(style, content = label)
    }
  }
  // The color of the Ripple should always the selected color, as we want to show the color
  // before the item is considered selected, and hence before the new contentColor is
  // provided by BottomNavigationTransition.
  val ripple = rememberRipple(bounded = false, color = selectedContentColor)

  Box(
    modifier
      .selectable(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        role = Role.Tab,
        interactionSource = interactionSource,
        indication = ripple
      ),
    contentAlignment = Alignment.Center
  ) {
    BottomNavigationTransition(
      selectedContentColor,
      unselectedContentColor,
      selected
    ) { progress ->
      val animationProgress = if (alwaysShowLabel) 1f else progress

      BottomNavigationItemBaselineLayout(
        icon = icon,
        label = styledLabel,
        iconPositionAnimationProgress = animationProgress
      )
    }
  }
}

private val BottomNavigationAnimationSpec = TweenSpec<Float>(
  durationMillis = 300,
  easing = FastOutSlowInEasing
)

@Composable
private fun BottomNavigationTransition(
  activeColor: Color,
  inactiveColor: Color,
  selected: Boolean,
  content: @Composable (animationProgress: Float) -> Unit
) {
  val animationProgress by animateFloatAsState(
    targetValue = if (selected) 1f else 0f,
    animationSpec = BottomNavigationAnimationSpec, label = ""
  )

  val color = lerp(inactiveColor, activeColor, animationProgress)

  CompositionLocalProvider(
    LocalContentColor provides color.copy(alpha = 1f),
  ) {
    content(animationProgress)
  }
}

@Composable
private fun BottomNavigationItemBaselineLayout(
  icon: @Composable () -> Unit,
  label: @Composable (() -> Unit)?,
  /*@FloatRange(from = 0.0, to = 1.0)*/
  iconPositionAnimationProgress: Float
) {
  Layout(
    {
      Box(Modifier.layoutId("icon")) { icon() }
      if (label != null) {
        Box(
          Modifier
            .layoutId("label")
            .alpha(iconPositionAnimationProgress)
            .padding(horizontal = BottomNavigationItemHorizontalPadding)
        ) { label() }
      }
    }
  ) { measurables, constraints ->
    val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)

    val labelPlaceable = label?.let {
      measurables.first { it.layoutId == "label" }.measure(
        // Measure with loose constraints for height as we don't want the label to take up more
        // space than it needs
        constraints.copy(minHeight = 0)
      )
    }

    // If there is no label, just place the icon.
    if (label == null) {
      placeIcon(iconPlaceable, constraints)
    } else {
      placeLabelAndIcon(
        labelPlaceable!!,
        iconPlaceable,
        constraints,
        iconPositionAnimationProgress
      )
    }
  }
}

private fun MeasureScope.placeIcon(
  iconPlaceable: Placeable,
  constraints: Constraints
): MeasureResult {
  val height = constraints.maxHeight
  val iconY = (height - iconPlaceable.height) / 2
  return layout(iconPlaceable.width, height) {
    iconPlaceable.placeRelative(0, iconY)
  }
}

private fun MeasureScope.placeLabelAndIcon(
  labelPlaceable: Placeable,
  iconPlaceable: Placeable,
  constraints: Constraints,
  /*@FloatRange(from = 0.0, to = 1.0)*/
  iconPositionAnimationProgress: Float
): MeasureResult {
  val height = constraints.maxHeight

  val firstBaseline = labelPlaceable[FirstBaseline]
  val baselineOffset = CombinedItemTextBaseline.roundToPx()
  val netBaselineAdjustment = baselineOffset - firstBaseline

  val contentHeight = iconPlaceable.height + labelPlaceable.height + netBaselineAdjustment
  val contentVerticalPadding = ((height - contentHeight) / 2).coerceAtLeast(0)

  val unselectedIconY = (height - iconPlaceable.height) / 2
  // Icon should be [contentVerticalPadding] from the top
  val selectedIconY = contentVerticalPadding

  // Label's first baseline should be [baselineOffset] below the icon
  val labelY = selectedIconY + iconPlaceable.height + netBaselineAdjustment

  val containerWidth = max(labelPlaceable.width, iconPlaceable.width)

  val labelX = (containerWidth - labelPlaceable.width) / 2
  val iconX = (containerWidth - iconPlaceable.width) / 2

  // How far the icon needs to move between unselected and selected states
  val iconDistance = unselectedIconY - selectedIconY

  // When selected the icon is above the unselected position, so we will animate moving
  // downwards from the selected state, so when progress is 1, the total distance is 0, and we
  // are at the selected state.
  val offset = (iconDistance * (1 - iconPositionAnimationProgress)).roundToInt()

  return layout(containerWidth, height) {
    if (iconPositionAnimationProgress != 0f) {
      labelPlaceable.placeRelative(labelX, labelY + offset)
    }
    iconPlaceable.placeRelative(iconX, selectedIconY + offset)
  }
}

private val BottomNavigationItemHorizontalPadding = 12.dp

private val CombinedItemTextBaseline = 12.dp
