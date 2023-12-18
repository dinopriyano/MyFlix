package id.aej.myflix.favorite.impl.presentation

/**
 * Created by dinopriyano on 07/12/23.
 */
sealed class FavoriteRoute(val route: String) {
  object Favorite: FavoriteRoute("favorite")

}