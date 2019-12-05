package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.app.Application
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession


class TraininingsessionActivityViewModel(
    repository: Repository
) : ViewModel() {
    val localRepository = repository
    var cambiado: Boolean = false

    fun getTrainingToRepository(id: Long) {
        trainingSession = localRepository.queryTrainerSession(id)!!
    }

    fun updateTrainingSessionJoinedState(trainingSession: TrainingSession, userJoined: Boolean) {
        if (userJoined) {
            localRepository.markTrainingSessionAsQuit(trainingSession.id)
        } else {
            localRepository.markTrainingSessionAsJoined(trainingSession.id)
        }

    }

    lateinit var trainingSession: TrainingSession
}