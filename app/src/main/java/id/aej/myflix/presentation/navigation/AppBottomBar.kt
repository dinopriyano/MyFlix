package id.aej.myflix.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import id.aej.myflix.design_system.presentation.components.FlixBottomNavigationItem
import id.aej.myflix.design_system.presentation.theme.LightTransparent
import id.aej.myflix.design_system.presentation.theme.SecondaryDark
import id.aej.myflix.design_system.utils.drawCircleIndicator
import id.aej.myflix.home.api.HomeFeature

/**
 * Created by dinopriyano on 14/12/23.
 */

@Composable fun AppBottomBar(
  modifier: Modifier,
  navController: NavController,
  homeFeature: HomeFeature
) {

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val destinations = listOf(
    homeFeature.homeRoute
    // TODO: add another screen
  )
  var xIndicatorOffset by remember {
    mutableFloatStateOf(Float.NaN)
  }
  val xOffsetAnimated by animateFloatAsState(targetValue = xIndicatorOffset, label = "")

  AnimatedVisibility(
    visible = destinations.any { it == currentDestination?.route },
    enter = slideInVertically(initialOffsetY = { it }),
    exit = slideOutVertically(targetOffsetY = { it })
  ) {
    Row(
      modifier = modifier
        .shadow(4.dp, CircleShape)
        .clip(CircleShape)
        .background(SecondaryDark)
        .padding(5.dp)
        .selectableGroup()
        .drawCircleIndicator(xOffsetAnimated),
      horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
      TabDestinations.forEach {
        val isSelected = currentDestination?.route == it.route
        val (color, icon, iconSize) = if (isSelected) {
          Triple(
            MaterialTheme.colorScheme.background,
            it.selectedIcon,
            32.dp
          )
        } else {
          Triple(
            Color.White,
            it.unselectedIcon,
            24.dp
          )
        }

        val animatedIconSize by animateDpAsState(
          targetValue = iconSize,
          animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioHighBouncy
          ), label = ""
        )
        FlixBottomNavigationItem(
          modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(LightTransparent)
            .onGloballyPositioned { layoutCoordinates ->
              if (isSelected) {
                val parentLayoutCoordinates = layoutCoordinates.parentLayoutCoordinates!!
                val parentPosition = parentLayoutCoordinates.positionInRoot()
                val itemCenterX =
                  layoutCoordinates.positionInRoot().x - parentPosition.x + layoutCoordinates.size.width / 2
                xIndicatorOffset = itemCenterX
              }
            }
          ,
          selected = isSelected,
          onClick = { navController.navigate(it.route) },
          icon = {
            Icon(
              painter = painterResource(id = icon),
              tint = color,
              contentDescription = null,
              modifier = Modifier.size(animatedIconSize)
            )
          }
        )
      }
    }
  }
}