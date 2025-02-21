package com.example.bd1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bd1.Database.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class MainViewModel : ViewModel() {

    // CoroutineScope associated with the ViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun anyadirTarea(task:TaskEntity){
        // Launch a coroutine within the ViewModel scope
        viewModelScope.launch {
            try {
                
                // task.dni = obtenerdni() + 1
                // Perform database operations on IO dispatcher
                val id = withContext(Dispatchers.IO) {
                    Bd1App.database.taskDao().anadeAutor(task)

                }

                // Update LiveData on the main thread
                //_tarea.postValue(recoveryTask)

            } catch (e: CancellationException) {
                // Handle cancellation if needed
                println("Coroutine was canceled")
            }
        }

    }
    suspend fun obtenerdni():Int{
        var dato:Int
        withContext(Dispatchers.IO) { // Ejecutar en segundo plano
            dato = Bd1App.database.taskDao().getDni() // Obtener dato antes
            if (dato != null) {
                Log.d("msg","dato integrado")
            }
        }
        return dato
    }
    override fun onCleared() {
        super.onCleared()
        // Cancel all coroutines when the ViewModel is cleared (e.g., when the associated UI component is destroyed)
        viewModelScope.cancel()
    }
}
