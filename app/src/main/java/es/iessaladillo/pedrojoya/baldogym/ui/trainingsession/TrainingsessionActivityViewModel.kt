package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.app.Application
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession


class TraininingsessionActivityViewModel(
    repository: Repository,
    app: Application
): ViewModel() {
    val localRepository = repository
    fun getTrainingToRepository(id: Long) {
       trainingSession = localRepository.queryTrainerSession(id)!!
    }
    lateinit var trainingSession: TrainingSession
}