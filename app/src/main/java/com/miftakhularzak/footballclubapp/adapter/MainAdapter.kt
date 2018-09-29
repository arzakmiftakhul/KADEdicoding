package com.miftakhularzak.footballclubapp.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R.id.*
import com.miftakhularzak.footballclubapp.model.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter(private val events: List<Match>, private val listener: (Match) -> Unit) : RecyclerView.Adapter<ClubViewHolder>() {

    var clickedIndex: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }


    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
        holder.itemView.setOnClickListener {
            clickedIndex = position
            listener(events[position])
        }

    }

    override fun getItemCount(): Int = events.size
}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {

        return with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent)
                cardElevation = 3f
                radius = 3f
                useCompatPadding = true
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = event_date
                        textSize = 14f
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = dip(6)
                    }
                    relativeLayout {

                        textView {
                            id = home_team_name
                            textSize = 20f
                            maxLines = 1
                            ellipsize = TextUtils.TruncateAt.END

                        }.lparams {
                            leftOf(home_score)
                        }
                        textView {
                            id = home_score
                            textSize = 20f
                        }.lparams {
                            leftOf(vs)
                            horizontalMargin = dip(20)
                        }
                        textView {
                            id = vs
                            textSize = 10f
                            text = "VS"
                            gravity = Gravity.CENTER_HORIZONTAL


                        }.lparams {
                            centerHorizontally()

                        }
                        textView {
                            id = away_score
                            textSize = 20f
                        }.lparams {
                            rightOf(vs)
                            horizontalMargin = dip(20)
                        }

                        textView {
                            id = away_team_name
                            textSize = 20f
                            maxLines = 1
                            ellipsize = TextUtils.TruncateAt.END
                        }.lparams {
                            rightOf(away_score)
                        }
                    }
                    textView {
                        id = event_time
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

class ClubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val homeTeamName: TextView = view.find(home_team_name)
    private val awayTeamName: TextView = view.find(away_team_name)
    private val eventDate: TextView = view.find(event_date)
    private val eventTime: TextView = view.find(event_time)
    private val homeScore: TextView = view.find(home_score)
    private val awayScore: TextView = view.find(away_score)


    fun bindItem(events: Match, listener: (Match) -> Unit) {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(events.dateEvent)
        val dateFormatter = SimpleDateFormat("E , dd MMM yyyy").format(date)

        val oldTimeFormat = SimpleDateFormat("HH:mm:ss")
        oldTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timeEvent = oldTimeFormat.parse(events.timeEvent)
        val newTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeEvent)

        homeTeamName.text = events.homeTeam
        awayTeamName.text = events.awayTeam
        eventDate.text = dateFormatter
        eventTime.text = newTimeFormat
        homeScore.text = events.homeScore
        awayScore.text = events.awayScore

    }


}