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
import com.miftakhularzak.footballclubapp.activity.DetailMatchActivity
import com.miftakhularzak.footballclubapp.adapter.MainAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.MainPresenter
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.Team
import com.miftakhularzak.footballclubapp.util.MainView
import com.miftakhularzak.footballclubapp.util.invisible


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MainView {

    private lateinit var adapter: MainAdapter
    private lateinit var presenter: MainPresenter
    lateinit var progressBar: ProgressBar

    private var events: MutableList<Match> = mutableListOf()
    private lateinit var listEvents: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_next_match, container, false)
        progressBar = rootView.findViewById(R.id.progressbar_next_match) as ProgressBar
        listEvents = rootView.findViewById(R.id.list_next_match) as RecyclerView

        listEvents.layoutManager = LinearLayoutManager(activity)


        return rootView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getEventsList("4328", "eventsnextleague.php")


    }

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }

    override fun showEventList(data: List<Match>) {
        events.clear()
        events.addAll(data)

        adapter = MainAdapter(events) {
            val intent = Intent(this.activity, DetailMatchActivity::class.java)
            val bundle = Bundle()
            val clickedIndex: Int = adapter.clickedIndex

            bundle.putParcelable("match_detail", data.get(clickedIndex))
            intent.putExtra("match_detail_bundle", bundle)
            startActivity(intent)
        }
        listEvents.adapter = adapter
        adapter.notifyDataSetChanged()
        Log.d("DATA EVENT", "FRAGMENT" + data[1])
    }

    override fun showLoading() {
        Log.d("CEKDATA", "SHOWLOADING")

    }

    override fun hideLoading() {
        progressBar.invisible()
        Log.d("CEKDATA", "hIDE LOADING")

    }

    override fun showTeamHome(data: List<Team>, data2: List<Team>) {
        Log.d("afa", "afsf")
    }

}
