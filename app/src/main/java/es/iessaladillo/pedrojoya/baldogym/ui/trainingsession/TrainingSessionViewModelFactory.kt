package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class TrainingSessionViewModelFactory(val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TraininingsessionActivityViewModel::class.java)){
            return TraininingsessionActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Must provide TrainingsessionActivityViewModel class")
    }

}