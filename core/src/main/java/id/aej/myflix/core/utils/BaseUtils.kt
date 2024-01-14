package id.aej.myflix.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by dinopriyano on 11/01/24.
 */

fun <T>execute(
  context: CoroutineContext,
  block: suspend () -> T
): Flow<T> {
  return flow {
    val out = block.invoke()
    emit(out)
  }.flowOn(context)
}