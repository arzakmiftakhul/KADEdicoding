package com.miftakhularzak.footballclubapp.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PlayerListAdapter(private val player: List<Player>, private val listenter: (Player)-> Unit):
        RecyclerView.Adapter<PlayerViewHolder>() {
    var clickedIndex : Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(player[position],listenter)
        holder.itemView.setOnClickListener {
            clickedIndex = position
            listenter(player[position])

        }
    }
}
class PlayerUI(): AnkoComponent<ViewGroup> {
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
                        id = R.id.player_photo
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    relativeLayout {
                        lparams(width = matchParent, height = dip(50))

                        textView {
                            id = R.id.player_name
                            textSize = 16f
                            textColor = Color.BLACK
                        }.lparams{
                            leftMargin = dip(15)
                            topMargin = dip(6)
                            bottomMargin = dip(6)
                            alignParentLeft()
                        }

                        textView {
                            id = R.id.player_position
                            textSize = 12f


                        }.lparams{
                            below(R.id.player_name)
                            alignParentRight()
                        }
                    }

                }
            }

        }
    }
}
class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view){
    private var playerName: TextView = view.find(R.id.player_name)
    private var playerPhoto: ImageView = view.find(R.id.player_photo)
    private var playerPosition: TextView = view.find(R.id.player_position)
    fun bindItem(player: Player, listener: (Player) -> Unit){
        Picasso.get().load(player.playerCutout).into(playerPhoto)
        playerName.text = player.playerName
        playerPosition.text = player.playerPosition

    }
}