package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {
    private val RC_ID_SELECTION: Int = 1


    private val viewModel: ScheduleActivityViewModel by lazy {
        ViewModelProvider(this, ScheduleActivityViewModelFactory(LocalRepository))
            .get(ScheduleActivityViewModel::class.java)
    }
    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().apply {
        setOnItemListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateToTrainerActivity(getItem(position).id)
            }

            override fun onItemClickBottom(position: Int) {
                viewModel.updateTrainingSessionJoinedState(
                    getItem(position),
                    getItem(position).userJoined
                )
            }

        })
    }

    private fun navigateToTrainerActivity(id: Long) {
        val intent = TrainingSessionActivity.newIntent(this, id)
        startActivityForResult(intent, RC_ID_SELECTION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        observeTraining()
    }

    private fun observeTraining() {
        viewModel.trainersSession.observe(this) { showList(it) }
    }


    private fun setupViews() {
        changeList(viewModel.currentDay.value!!)
        dayOfTheWeekLbl.text = viewModel.currentDay.value?.nameResId?.let { getString(it) }
        setupRecyclerView()
        lblMonday.setOnClickListener { changeList(WeekDay.MONDAY) }
        lblTuesday.setOnClickListener { changeList(WeekDay.TUESDAY) }
        lblWednesday.setOnClickListener { changeList(WeekDay.WEDNESDAY) }
        lblThursday.setOnClickListener { changeList(WeekDay.THURSDAY) }
        lblFriday.setOnClickListener { changeList(WeekDay.FRIDAY) }
        lblSaturday.setOnClickListener { changeList(WeekDay.SATURDAY) }
        lblSunday.setOnClickListener { changeList(WeekDay.SUNDAY) }

    }

    private fun changeList(dayOfWeek: WeekDay) {
        pintarDiaDeLaSemana(dayOfWeek)
        viewModel.selectDayOfTrainning(dayOfWeek)
    }

    private fun pintarDiaDeLaSemana(dayOfWeek: WeekDay) {
        dayOfTheWeekLbl.text = getString(dayOfWeek.nameResId)
        when (dayOfWeek) {
            WeekDay.MONDAY -> {
                lblMonday.setTextColor(Color.parseColor("#FFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#AAFFFFFF"))
            }
            WeekDay.TUESDAY -> {
                lblMonday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#FFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#AAFFFFFF"))
            }
            WeekDay.WEDNESDAY -> {
                lblMonday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#FFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#AAFFFFFF"))
            }
            WeekDay.THURSDAY -> {
                lblMonday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#FFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#AAFFFFFF"))
            }
            WeekDay.FRIDAY -> {
                lblMonday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#FFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#AAFFFFFF"))
            }
            WeekDay.SATURDAY -> {
                lblMonday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#FFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#AAFFFFFF"))
            }
            WeekDay.SUNDAY -> {
                lblMonday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblTuesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblWednesday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblThursday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblFriday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSaturday.setTextColor(Color.parseColor("#AAFFFFFF"))
                lblSunday.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    private fun setupRecyclerView() {
        lstTraining.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ScheduleActivity)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@ScheduleActivity, RecyclerView.VERTICAL))
            adapter = listAdapter
        }

    }

    private fun showList(trainingSesions: List<TrainingSession>) {
        lstTraining.post {
            listAdapter.submitList(trainingSesions)
            if (trainingSesions.isEmpty()) {
                lblEmptyView.visibility = View.VISIBLE
            } else {
                lblEmptyView.visibility = View.INVISIBLE
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_ID_SELECTION && intent != null) {
            viewModel.selectDayOfTrainning(viewModel.currentDay.value)
        }
    }


}

