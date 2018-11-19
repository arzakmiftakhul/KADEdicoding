package com.miftakhularzak.footballclubapp.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.model.FavoriteTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FavoriteTeamAdapter(private val favoriteTeam : List<FavoriteTeam>, private val listener : (FavoriteTeam)->Unit):
        RecyclerView.Adapter<FavoriteTeamViewHolder>(){
    var clickedIndex : Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(FavoriteTeamUI().createView(AnkoContext.create(parent.context,parent)))
    }

    override fun getItemCount(): Int = favoriteTeam.size

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position],listener)
        holder.itemView.setOnClickListener {
            clickedIndex = position
            listener(favoriteTeam[position])
        }
    }

}
class FavoriteTeamUI : AnkoComponent<ViewGroup> {
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
                    linearLayout {
                        lparams(width = matchParent, height = dip (50))
                        orientation = LinearLayout.VERTICAL

                        textView {
                            id = R.id.team_name
                            textSize = 16f
                        }.lparams{
                            leftMargin = dip(15)
                            topMargin = dip (8)
                        }

                        textView {
                            id = R.id.team_league
                            textSize = 10f
                            textColor = Color.BLACK
                        }.lparams{
                            leftMargin = dip(15)
                        }
                    }


                }

            }
        }
    }

}

class FavoriteTeamViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val teamName: TextView = view.find(R.id.team_name)
    private val teamLogo: ImageView = view.find(R.id.team_badge)
    private val teamLeague: TextView = view.find(R.id.team_league)
    fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit){
        teamName.text = favoriteTeam.teamName
        Picasso.get().load(favoriteTeam.teamBadge).into(teamLogo)
        teamLeague.text = favoriteTeam.teamLeague

    }
}