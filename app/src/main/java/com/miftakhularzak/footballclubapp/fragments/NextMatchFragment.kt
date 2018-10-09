package com.miftakhularzak.footballclubapp.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.`interface`.MatchView
import com.miftakhularzak.footballclubapp.activity.DetailMatchActivity
import com.miftakhularzak.footballclubapp.adapter.MainAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.presenter.MatchPresenter
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MatchView {

    private lateinit var adapter: MainAdapter
    private lateinit var presenter: MatchPresenter
    var progressBar: ProgressBar? = null

    private var events: MutableList<Match> = mutableListOf()
    private lateinit var listEvents: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_next_match, container, false)

        listEvents = rootView.findViewById(R.id.list_next_match) as RecyclerView

        listEvents.layoutManager = LinearLayoutManager(activity)


        return rootView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        presenter.getMatchList("4328", "eventsnextleague.php?")

    }

    override fun showLoading() {
        progressBar?.visible()

    }

    override fun showMatchList(data: List<Match>) {
        Log.d("CEKDATA", "SHOWMATCH")

        events.clear()
        events.addAll(data)
        adapter = MainAdapter(events){
            val intent = Intent(this.activity,DetailMatchActivity::class.java)
            intent.putExtra("eventid",data[adapter.clickedIndex].eventId)
            intent.putExtra("homeid",data[adapter.clickedIndex].homeId)
            intent.putExtra("awayid",data[adapter.clickedIndex].awayId)
            intent.putExtra("id",data[adapter.clickedIndex].eventId)
            startActivity(intent)
        }
        listEvents.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        progressBar?.invisible()
        Log.d("CEKDATA", "hIDE LOADING")

    }


}
