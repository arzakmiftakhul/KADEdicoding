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
import com.miftakhularzak.footballclubapp.R.color.colorAccent
import com.miftakhularzak.footballclubapp.R.id.list_fav_match
import com.miftakhularzak.footballclubapp.R.id.swipe_refresh
import com.miftakhularzak.footballclubapp.activity.DetailMatchActivity
import com.miftakhularzak.footballclubapp.adapter.FavoriteMatchAdapter
import com.miftakhularzak.footballclubapp.helper.database
import com.miftakhularzak.footballclubapp.model.Favorite
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
class FavoriteMatchFragment : Fragment(),AnkoComponent<Context> {

    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var listEvent : RecyclerView
    private var favorites : MutableList<Favorite> = mutableListOf()
    private lateinit var adapter : FavoriteMatchAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteMatchAdapter(favorites){
            ctx.startActivity<DetailMatchActivity>("id" to "${it.eventId}",
                    "eventid" to it.eventId,
                    "homeid" to it.homeId,
                    "awayid" to it.awayId)
        }

        listEvent.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
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
                id = swipe_refresh
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listEvent = recyclerView {
                    id = list_fav_match
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }

    }

}
