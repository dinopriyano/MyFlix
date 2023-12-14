package id.aej.myflix.design_system.domain.model

import androidx.annotation.DrawableRes

data class Tab(
  val route: String,
  @DrawableRes val selectedIcon: Int,
  @DrawableRes val unselectedIcon: Int
)