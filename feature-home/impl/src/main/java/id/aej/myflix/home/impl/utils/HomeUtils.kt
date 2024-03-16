package id.aej.myflix.home.impl.utils

/**
 * Created by dinopriyano on 16/03/24.
 */

fun String.getYearByDate() : String {
    return this.split("-").first()
}