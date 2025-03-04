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

    // Limpieza de corutinas cuando el ViewModel es destruido
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}