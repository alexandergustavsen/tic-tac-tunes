package no.hk.ag.tic_tac_tunes.database

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.hk.ag.tic_tac_tunes.R

class HsAdapter internal constructor(context : Context) : RecyclerView.Adapter<HsAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var iHs: List<HighScore>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(iHs != null) {
            val current = iHs!![position]
            holder.textViewName.text = current.pName
            holder.textViewScore.text = current.pScore.toString()
        } else {
            holder.textViewName.text = "No Players"
            holder.textViewScore.text = "0"
        }
    }

    fun setHs(hs: List<HighScore>) {
        iHs = hs
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if(iHs != null) {
            return iHs!!.size
        }
        return 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        val textViewScore = itemView.findViewById(R.id.textViewScore) as TextView
    }

}