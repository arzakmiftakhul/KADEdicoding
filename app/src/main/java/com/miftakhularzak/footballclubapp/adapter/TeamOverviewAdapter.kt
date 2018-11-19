package com.miftakhularzak.footballclubapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.model.Team
import org.jetbrains.anko.*

class TeamOverviewAdapter(private val teams: List<Team>): RecyclerView.Adapter<TeamOverviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamOverviewViewHolder {
        return TeamOverviewViewHolder(OverviewUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamOverviewViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

}
class OverviewUI(): AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL
                textView {
                    id = R.id.team_overview_adapter
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }
            }
        }
    }
}

class TeamOverviewViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val teamOverview: TextView = view.find(R.id.team_overview_adapter)

    fun bindItem(teams: Team){
        teamOverview.text = teams.teamOverview
    }
}