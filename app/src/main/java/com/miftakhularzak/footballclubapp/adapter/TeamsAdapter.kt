package com.miftakhularzak.footballclubapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.R.id.team_badge
import com.miftakhularzak.footballclubapp.R.id.team_name
import com.miftakhularzak.footballclubapp.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamsAdapter(private val teams: List<Team>, private val listener: (Team)->Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    var clickedIndex : Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
       holder.bindItem(teams[position],listener)
        holder.itemView.setOnClickListener {
            clickedIndex = position
            listener(teams[position])
        }
    }

}

class TeamUI(): AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent)
                cardElevation = 3f
                radius = 3f
                useCompatPadding = true

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }
                }
            }

        }
    }
}

class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(teams: Team,listener: (Team) -> Unit){
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName
    }
}