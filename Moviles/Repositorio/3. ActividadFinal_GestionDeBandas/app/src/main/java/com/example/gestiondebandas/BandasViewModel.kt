package com.example.gestiondebandas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestiondebandas.database.Banda
import com.example.gestiondebandas.database.BandaConMusicos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class BandasViewModel : ViewModel() {

    // CoroutineScope asociado al ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    // LiveData para los bandas
    private val _bandas = MutableLiveData<List<Banda>>()
    private val _bandasConMusicos = MutableLiveData<List<BandaConMusicos>>()
    val bandas: LiveData<List<Banda>> get() = _bandas
    val bandasConMusicos: LiveData<List<BandaConMusicos>> get() = _bandasConMusicos

    // Función que carga las bandas desde la base de datos
    fun obtenerBandas() {
        viewModelScope.launch {
            try {
                // Realizamos la consulta en un hilo de IO para no bloquear el hilo principal
                val bandasLista = withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.bandaDao().getBandas()
                }
                // Publicamos los datos cargados en el LiveData
                _bandas.postValue(bandasLista)
            } catch (e: CancellationException) {
                // Manejo de errores en caso de que se cancele la coroutine
                println("Coroutine was canceled")
            }
        }
    }

    // Función para registrar un banda. Le paso como parámetros también dos funciones que me ayudarán a notificar al usuario si el registro se ha completado o no
    fun registrarBanda(banda: Banda, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.bandaDao().addBanda(banda)
                }
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Coroutine was canceled")
            }
        }
    }

    // Método para actualizar el banda en la base de datos
    fun actualizarBanda(banda: Banda, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // la acción de la base de datos la hacemos en el hilo secundario
                val rowsUpdated = withContext(Dispatchers.IO) {
                    // Actualizamos el banda en la base de datos
                    GestionDeBandasApp.database.bandaDao().actualizarBanda(banda)
                }
                // la acción de mostrar el mensaje la hacemos en el hilo principal
                withContext(Dispatchers.Main) {
                    // Verificamos si se actualizó algún registro
                    if (rowsUpdated > 0) {
                        onSuccess()  // Llamamos a la función de éxito
                    } else {
                        onError("No se pudo actualizar el banda.")  // Llamamos a la función de error
                    }
                }

            } catch (e: Exception) {
                // En caso de error, capturamos la excepción y llamamos a onError
                onError("Error al actualizar el banda: ${e.message}")
            }
        }
    }

    // Método para eliminar el banda de la base de datos
    fun eliminarBanda(banda: Banda, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val rowsDeleted = withContext(Dispatchers.IO) {
                    // Eliminamos el banda de la base de datos
                    GestionDeBandasApp.database.bandaDao().eliminarBanda(banda)
                }
                withContext(Dispatchers.Main) {
                    // Verificamos si se eliminó algún registro
                    if (rowsDeleted > 0) {
                        onSuccess()  // Llamamos a la función de éxito
                    } else {
                        onError("No se pudo eliminar el banda.")  // Llamamos a la función de error
                    }
                }
            } catch (e: Exception) {
                // En caso de error, capturamos la excepción y llamamos a onError
                onError("Error al eliminar el banda: ${e.message}")
            }
        }
    }

    fun obtenerBandasConMusicos() {
        viewModelScope.launch {
            try {
                // Realizamos la consulta en un hilo de IO para no bloquear el hilo principal
                val bandasConMusicosLista = withContext(Dispatchers.IO) {
                    GestionDeBandasApp.database.bandaDao().getBandasConMusicos()
                }
                // Publicamos los datos cargados en el LiveData
                _bandasConMusicos.postValue(bandasConMusicosLista)
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