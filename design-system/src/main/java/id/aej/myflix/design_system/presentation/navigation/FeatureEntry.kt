package id.aej.myflix.design_system.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

/**
 * Created by dinopriyano on 27/11/23.
 */
interface FeatureEntry {

  fun registerGraph(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
    modifier: Modifier = Modifier
  )

}