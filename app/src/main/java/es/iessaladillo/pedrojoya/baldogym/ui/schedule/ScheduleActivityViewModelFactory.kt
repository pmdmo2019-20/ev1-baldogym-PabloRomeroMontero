package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ScheduleActivityViewModelFactory(val repository: Repository, val app: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleActivityViewModel::class.java)){
            return ScheduleActivityViewModel(repository,app) as T
        }
        throw IllegalArgumentException("Must provide ScheduleActivityViewModel class")
    }

}