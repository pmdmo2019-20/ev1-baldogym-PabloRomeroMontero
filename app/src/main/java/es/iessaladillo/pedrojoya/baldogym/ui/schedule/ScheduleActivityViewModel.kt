package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import es.iessaladillo.pedrojoya.baldogym.utils.Event

class ScheduleActivityViewModel(
    repository: Repository,
    app: Application
): ViewModel() {

    private val localRepository = repository
    private val application = app

    private val _currentDay: MutableLiveData<WeekDay> = MutableLiveData(getCurrentWeekDay())
    val currentDay: LiveData<WeekDay>
        get() {
            return _currentDay
        }

    private val _trainersSessions: MutableLiveData<List<TrainingSession>> = MutableLiveData(
        emptyList())
    val trainersSession: LiveData<List<TrainingSession>>
    get() {
        return _trainersSessions
    }



    private val _onStartActivity: MutableLiveData<Event<Intent>> = MutableLiveData()
    val onStartActivity: LiveData<Event<Intent>>
        get() = _onStartActivity

    fun updateLiveData(){
        _trainersSessions.value = localRepository.queryTrainersofDayOfTheWeek(currentDay.value!!)
    }


    fun selectDayOfTrainning(weekDay: WeekDay?){
        _currentDay.value = weekDay
        updateLiveData()
    }


    fun updateTrainingSessionJoinedState(trainingSession: TrainingSession, isJoined: Boolean) {
        if (isJoined){
            localRepository.markTrainingSessionAsQuit(trainingSession.id)
        }else{
            localRepository.markTrainingSessionAsJoined(trainingSession.id)
        }
        updateLiveData()
    }
}