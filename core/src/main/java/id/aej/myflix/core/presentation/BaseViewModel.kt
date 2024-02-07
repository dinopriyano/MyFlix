package id.aej.myflix.core.presentation

import androidx.lifecycle.ViewModel
import id.aej.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by dinopriyano on 07/02/24.
 */

abstract class BaseViewModel: ViewModel() {

  suspend fun <T> Flow<Resource<T>>.runFlow(stateVariable: suspend (Resource<T>) -> Unit) {
    stateVariable.invoke(Resource.Loading)
    collect { result ->
      stateVariable.invoke(result)
    }
  }

}