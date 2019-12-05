package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivityViewModel
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivityViewModelFactory
import kotlinx.android.synthetic.main.training_session_activity.*

class TrainingSessionActivity : AppCompatActivity() {

    private var id: Long = 0

    private val viewModelActivity: TraininingsessionActivityViewModel by lazy {
        ViewModelProvider(this, TrainingSessionViewModelFactory(LocalRepository, application))
            .get(TraininingsessionActivityViewModel::class.java)
    }
    private val viewModelSchedule: ScheduleActivityViewModel by lazy {
        ViewModelProvider(this, ScheduleActivityViewModelFactory(LocalRepository, application))
            .get(ScheduleActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        getIntentData()
        setupViews()
    }

    private fun setupViews() {
        imageView2.setImageResource(viewModelActivity.trainingSession.photoResId)
        lblName.text =viewModelActivity.trainingSession.name
        lblRoom.text = viewModelActivity.trainingSession.room
        lblTrainer.text = viewModelActivity.trainingSession.trainer
        lblDescripcion.text=viewModelActivity.trainingSession.description
        lblDayofWeek.text= viewModelActivity.trainingSession.weekDay.toString()
        lblTime.text = viewModelActivity.trainingSession.time
        lblParticipantes_activity.text =String.format("Participantes %d", viewModelActivity.trainingSession.participants)

        if (viewModelActivity.trainingSession.userJoined)
            btn_Activity.text = getString(R.string.schedule_item_quit)
        else
            btn_Activity.text = getString(R.string.schedule_item_join)


        btn_Activity.setOnClickListener { changeTraining(id) }
    }

    private fun changeTraining(id: Long) {
        viewModelSchedule.updateTrainingSessionJoinedState(viewModelActivity.trainingSession,viewModelActivity.trainingSession.userJoined)
        viewModelActivity.getTrainingToRepository(id)
        setupViews()
    }

    private fun getIntentData() {
        if (intent == null) {
            throw RuntimeException(
                "TrainingSessionsActivity necesita recibir un id"
            )
        }
        id = intent.getLongExtra(EXTRA_ID, 0)
        viewModelActivity.getTrainingToRepository(id)
    }


    companion object {
        const val EXTRA_ID = "EXTRA_DAY"
        fun newIntent(context: Context, id: Long): Intent =
            Intent(context, TrainingSessionActivity::class.java)
                .putExtras(bundleOf(EXTRA_ID to id))
    }
}

