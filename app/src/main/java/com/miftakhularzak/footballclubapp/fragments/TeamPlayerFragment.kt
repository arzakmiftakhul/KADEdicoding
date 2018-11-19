package com.miftakhularzak.footballclubapp.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.`interface`.PlayerView
import com.miftakhularzak.footballclubapp.activity.DetailPlayerActivity
import com.miftakhularzak.footballclubapp.adapter.PlayerListAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.Player
import com.miftakhularzak.footballclubapp.presenter.PlayerPresenter
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamPlayerFragment : Fragment(),PlayerView {
    private var player: MutableList<Player> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var listPlayer : RecyclerView
    private lateinit var adapter: PlayerListAdapter
    private lateinit var presenter: PlayerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_team_player, container, false)
        listPlayer = rootView.findViewById(R.id.player_rv) as RecyclerView
        progressBar = rootView.findViewById(R.id.player_pb) as ProgressBar
        listPlayer.layoutManager = LinearLayoutManager(activity)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent = activity?.intent

        val teamId = intent?.getStringExtra("id")
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerList(teamId)
    }
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<Player>) {
        player.clear()
        player.addAll(data)
        adapter = PlayerListAdapter(player){
            val intent = Intent(this.activity, DetailPlayerActivity::class.java)
            intent.putExtra("id", data[adapter.clickedIndex].playerId)
            intent.putExtra("photo", data[adapter.clickedIndex].playerPhoto)
            intent.putExtra("name", data[adapter.clickedIndex].playerName)
            intent.putExtra("height", data[adapter.clickedIndex].playerHeight)
            intent.putExtra("weight", data[adapter.clickedIndex].playerWeight)
            intent.putExtra("position", data[adapter.clickedIndex].playerPosition)
            intent.putExtra("overview", data[adapter.clickedIndex].playerDesc)
            startActivity(intent)
        }
        listPlayer.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}
