package id.aej.myflix.home.impl.data

import id.aej.myflix.home.impl.R
import kotlin.random.Random

/**
 * Created by dinopriyano on 16/03/24.
 */

val person = listOf(
    R.drawable.person_one,
    R.drawable.person_two,
    R.drawable.person_three,
    R.drawable.person_four,
    R.drawable.person_five
)

fun randomPersonImage() = person[Random.nextInt(0, 4)]