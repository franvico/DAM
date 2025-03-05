package com.example.gestiondebandas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestiondebandas.database.Musico
import com.example.gestiondebandas.database.MusicoConInstrumento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class MusicosViewModel : ViewModel() {

    // CoroutineScope asociado al ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    // LiveData para los musicos
    private val _musicos = MutableLiveData<List<Musico>>()
    private val _musicosConMusicos = MutableLiveData<List<MusicoConInstrumento>>()
    val musicos: LiveData<List<Musico>> get() = _musicos
    val musicosConMusicos: LiveData<List<MusicoConInstrumento>> get() = _musicosConMusicos

    // Función que carga los musicos desde la base de datos
    fun obtenerMusicos() {
        viewModelScope.launch {
            try {
                // Realizamos la consulta en un hilo de IO para no bloquear el hilo principal
                val musicosLista = withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.musicoDao().getMusicos()
                }
                // Publicamos los datos cargados en el LiveData
                _musicos.postValue(musicosLista)
            } catch (e: CancellationException) {
                // Manejo de errores en caso de que se cancele la coroutine
                println("Coroutine was canceled")
            }
        }
    }

    // Función para registrar un musico. Le paso como parámetros también dos funciones que me ayudarán a notificar al usuario si el registro se ha completado o no
    fun registrarMusico(musico: Musico, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.musicoDao().addMusico(musico)
                }
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Coroutine was canceled")
            }
        }
    }

    // Método para actualizar el musico en la base de datos
    fun actualizarMusico(musico: Musico, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // la acción de la base de datos la hacemos en el hilo secundario
                val rowsUpdated = withContext(Dispatchers.IO) {
                    // Actualizamos el musico en la base de datos
                    GestionDeBandasApp.database.musicoDao().actualizarMusico(musico)
                }
                // la acción de mostrar el mensaje la hacemos en el hilo principal
                withContext(Dispatchers.Main) {
                    // Verificamos si se actualizó algún registro
                    if (rowsUpdated > 0) {
                        onSuccess()  // Llamamos a la función de éxito
                    } else {
                        onError("No se pudo actualizar el musico.")  // Llamamos a la función de error
                    }
                }

            } catch (e: Exception) {
                // En caso de error, capturamos la excepción y llamamos a onError
                onError("Error al actualizar el musico: ${e.message}")
            }
        }
    }

    // Método para eliminar el musico de la base de datos
    fun eliminarMusico(musico: Musico, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val rowsDeleted = withContext(Dispatchers.IO) {
                    // Eliminamos el musico de la base de datos
                    GestionDeBandasApp.database.musicoDao().eliminarMusico(musico)
                }
                withContext(Dispatchers.Main) {
                    // Verificamos si se eliminó algún registro
                    if (rowsDeleted > 0) {
                        onSuccess()  // Llamamos a la función de éxito
                    } else {
                        onError("No se pudo eliminar el musico.")  // Llamamos a la función de error
                    }
                }
            } catch (e: Exception) {
                // En caso de error, capturamos la excepción y llamamos a onError
                onError("Error al eliminar el musico: ${e.message}")
            }
        }
    }

    fun obtenerMusicosConInstrumento() {
        viewModelScope.launch {
            try {
                // Realizamos la consulta en un hilo de IO para no bloquear el hilo principal
                val musicosConMusicosLista = withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.musicoDao().getMusicosConInstrumento()
                }
                // Publicamos los datos cargados en el LiveData
                _musicosConMusicos.postValue(musicosConMusicosLista)
            } catch (e: CancellationException) {
                // Manejo de errores en caso de que se cancele la coroutine
                println("Coroutine was canceled")
            }
        }
    }

    // Limpieza de corutinas cuando el ViewModel es destruido
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}