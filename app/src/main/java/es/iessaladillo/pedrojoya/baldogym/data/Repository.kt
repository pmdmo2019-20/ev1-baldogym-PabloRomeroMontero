package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

interface Repository {

    fun queryTrainers(): List<TrainingSession>

    fun queryTrainersofDayOfTheWeek(day: WeekDay): List<TrainingSession>

    fun markTrainingSessionAsJoined(trainingId: Long)

    fun markTrainingSessionAsQuit(trainingId: Long)

    fun queryTrainerSession(id: Long): TrainingSession?

}