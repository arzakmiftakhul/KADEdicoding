package com.miftakhularzak.footballclubapp.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.activity.DetailTeamActivity
import com.miftakhularzak.footballclubapp.adapter.FavoriteTeamAdapter
import com.miftakhularzak.footballclubapp.helper.databaseteam
import com.miftakhularzak.footballclubapp.model.FavoriteTeam
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamFragment : Fragment() , AnkoComponent<Context> {

    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var listTeam : RecyclerView
    private var favorites : MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter : FavoriteTeamAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteTeamAdapter(favorites){
            ctx.startActivity<DetailTeamActivity>("id" to "${it.teamId}",
                    "id" to it.teamId,
                    "name" to it.teamName,
                    "logo" to it.teamBadge,
                    "formedyear" to it.teamFormedYear,
                    "overview" to it.teamOverview,
                    "stadium" to it.teamStadium,
                    "league" to it.teamLeague)
        }

        listTeam.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.databaseteam?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh_team
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listTeam = recyclerView {
                    id = R.id.list_fav_team
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }

    }

}