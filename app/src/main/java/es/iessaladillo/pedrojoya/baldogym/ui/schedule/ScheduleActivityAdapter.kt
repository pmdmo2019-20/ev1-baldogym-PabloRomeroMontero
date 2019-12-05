package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivityAdapter.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity_item.*

class ScheduleActivityAdapter : RecyclerView.Adapter<ViewHolder> () {

    private var data: List<TrainingSession> = emptyList()

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemId(position: Int): Long = data[position].id


    fun getItem(position: Int): TrainingSession = data[position]

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun submitList(newData: List<TrainingSession>){
        data = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {


        init {
            containerView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
            btnJoin_item.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClickBottom(position)
                }
            }
        }

        fun bind(item: TrainingSession){
            lblNameTraining_item.text = item.name
            lblTrainer_item.text = item.trainer
            lblTime_item.text = item.time
            lblParticipantes_item.text = String.format("Participantes %d",item.participants)
            imageView_item.setImageResource(item.photoResId)

            if(item.userJoined){
                btnJoin_item.setText(R.string.schedule_item_quit)
                btnJoin_item.setBackgroundColor(Color.parseColor("#000000"))
                btnJoin_item.setTextColor( Color.parseColor("#FFFFFF"))
            }else{
                btnJoin_item.setText(R.string.schedule_item_join)
                btnJoin_item.setTextColor(Color.parseColor("#000000"))
                btnJoin_item.setBackgroundColor( Color.parseColor("#FFFFFF"))

            }
        }

    }
}