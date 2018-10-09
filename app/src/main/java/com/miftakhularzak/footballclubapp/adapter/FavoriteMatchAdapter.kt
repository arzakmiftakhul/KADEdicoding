package com.miftakhularzak.footballclubapp.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.model.Favorite
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FavoriteMatchAdapter (private val favorite : List<Favorite>, private val listener : (Favorite)->Unit):
        RecyclerView.Adapter<FavoriteViewHolder>(){
    var clickedIndex : Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(MatchUI().createView(AnkoContext.create(parent.context,parent)))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position],listener)
        holder.itemView.setOnClickListener {
            clickedIndex = position
            listener(favorite[position])
        }
    }

}
class MatchUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent)
                cardElevation = 3f
                radius = 3f
                useCompatPadding = true
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(8)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = R.id.fav_date
                        textSize = 14f
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = dip(6)
                    }
                    relativeLayout {

                        textView {
                            id = R.id.fav_home_team
                            textSize = 20f
                            maxLines = 1
                            ellipsize = TextUtils.TruncateAt.END

                        }.lparams {
                            leftOf(R.id.fav_home_score)
                        }
                        textView {
                            id = R.id.fav_home_score
                            textSize = 20f
                        }.lparams {
                            leftOf(R.id.fav_vs)
                            horizontalMargin = dip(20)
                        }
                        textView {
                            id = R.id.fav_vs
                            textSize = 10f
                            text = "VS"
                            gravity = Gravity.CENTER_HORIZONTAL


                        }.lparams {
                            centerHorizontally()

                        }
                        textView {
                            id = R.id.fav_away_score
                            textSize = 20f
                        }.lparams {
                            rightOf(R.id.fav_vs)
                            horizontalMargin = dip(20)
                        }

                        textView {
                            id = R.id.fav_away_team
                            textSize = 20f
                            maxLines = 1
                            ellipsize = TextUtils.TruncateAt.END
                        }.lparams {
                            rightOf(R.id.fav_away_score)
                        }
                    }
                    textView {
                        id = R.id.fav_time
                        textSize = 14f
                        gravity = Gravity.CENTER_HORIZONTAL
                        text = "abcd"
                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = dip(6)
                    }


                }

            }
        }
    }

}

class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val homeTeamName: TextView = view.find(R.id.fav_home_team)
    private val awayTeamName: TextView = view.find(R.id.fav_away_team)
    private val eventDate: TextView = view.find(R.id.fav_date)
    private val eventTime: TextView = view.find(R.id.fav_time)
    private val homeScore: TextView = view.find(R.id.fav_home_score)
    private val awayScore: TextView = view.find(R.id.fav_away_score)
    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit){
        homeTeamName.text = favorite.homeTeam
        awayTeamName.text = favorite.awayTeam
        eventDate.text = favorite.dateEvent
        eventTime.text = favorite.timeEvent
        homeScore.text = favorite.homeScore
        awayScore.text = favorite.awayScore

    }
}
