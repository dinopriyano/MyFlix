package id.aej.myflix.presentation.navigation

import id.aej.myflix.design_system.R
import id.aej.myflix.design_system.domain.model.Tab

/**
 * Created by dinopriyano on 14/12/23.
 */

val TabDestinations = listOf(
  Tab(
    route = "home",
    selectedIcon = R.drawable.ic_home_filled,
    unselectedIcon = R.drawable.ic_home_outlined
  ),
  Tab(
    route = "bookmark",
    selectedIcon = R.drawable.ic_bookmark_filled,
    unselectedIcon = R.drawable.ic_bookmark_outlined
  ),
  Tab(
    route = "profile",
    selectedIcon = R.drawable.ic_profile_filled,
    unselectedIcon = R.drawable.ic_profile_outlined
  )
)