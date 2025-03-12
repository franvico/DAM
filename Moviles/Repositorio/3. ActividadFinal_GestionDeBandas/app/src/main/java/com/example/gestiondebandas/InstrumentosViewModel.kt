package com.example.gestiondebandas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestiondebandas.database.Instrumento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class InstrumentosViewModel : ViewModel(){

    // CoroutineScope asociado al ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    // LiveData para los instrumentos
    private val _instrumentos = MutableLiveData<List<Instrumento>>()
    val instrumentos: LiveData<List<Instrumento>> get() = _instrumentos

    // Función que carga los instrumentos desde la base de datos
    fun obtenerInstrumentos() {
        viewModelScope.launch {
            try {
                // Realizamos la consulta en un hilo de IO para no bloquear el hilo principal
                val instrumentosLista = withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.instrumentoDao().getInstrumentos()
                }
                // Publicamos los datos cargados en el LiveData
                _instrumentos.postValue(instrumentosLista)
            } catch (e: CancellationException) {
                // Manejo de errores en caso de que se cancele la coroutine
                println("Coroutine was canceled")
            }
        }
    }

    // Función para registrar un instrumento. Le paso como parámetros también dos funciones que me ayudarán a notificar al usuario si el registro se ha completado o no
    fun registrarInstrumento(instrumento: Instrumento, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.instrumentoDao().addInstrumento(instrumento)
                }
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Coroutine was canceled")
            }
        }
    }

    // Método para actualizar el instrumento en la base de datos
    fun actualizarInstrumento(instrumento: Instrumento, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // la acción de la base de datos la hacemos en el hilo secundario
                val rowsUpdated = withContext(Dispatchers.IO) {
                    // Actualizamos el instrumento en la base de datos
                    GestionDeBandasApp.database.instrumentoDao().actualizarInstrumento(instrumento)
                }
                // la acción de mostrar el mensaje la hacemos en el hilo principal
                withContext(Dispatchers.Main) {
                    // Verificamos si se actualizó algún registro
                    if (rowsUpdated > 0) {
                        onSuccess()  // Llamamos a la función de éxito
                    } else {
                        onError("No se pudo actualizar el instrumento.")  // Llamamos a la función de error
                    }
                }

            } catch (e: Exception) {
                // En caso de error, capturamos la excepción y llamamos a onError
                onError("Error al actualizar el instrumento: ${e.message}")
            }
        }
    }

    // Método para eliminar el instrumento de la base de datos
    fun eliminarInstrumento(instrumento: Instrumento, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val rowsDeleted = withContext(Dispatchers.IO) {
                    // Eliminamos el instrumento de la base de datos
                    GestionDeBandasApp.database.instrumentoDao().eliminarInstrumento(instrumento)
                }
                withContext(Dispatchers.Main) {
                    // Verificamos si se eliminó algún registro
                    if (rowsDeleted > 0) {
                        onSuccess()  // Llamamos a la función de éxito
                    } else {
                        onError("No se pudo eliminar el instrumento.")  // Llamamos a la función de error
                    }
                }
            } catch (e: Exception) {
                // En caso de error, capturamos la excepción y llamamos a onError
                onError("Error al eliminar el instrumento: ${e.message}")
            }
        }
    }

    // Limpieza de corutinas cuando el ViewModel es destruido
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}