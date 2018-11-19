package com.miftakhularzak.footballclubapp.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.`interface`.MatchView
import com.miftakhularzak.footballclubapp.activity.DetailMatchActivity
import com.miftakhularzak.footballclubapp.adapter.MatchAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.presenter.MatchPresenter
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.ctx


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MatchView {

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    var progressBar: ProgressBar? = null

    private var events: MutableList<Match> = mutableListOf()
    private lateinit var listEvents: RecyclerView
    private lateinit var spinner : Spinner


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_next_match, container, false)

        listEvents = rootView.findViewById(R.id.list_next_match) as RecyclerView

        listEvents.layoutManager = LinearLayoutManager(activity)


        return rootView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressBar = progressbar_next_match
        spinner = spinner_next_match as Spinner
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx,
                android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        val request = ApiRepository()
        val gson = Gson()

        val leagueId = arrayOf("4328","4329","4331","4332","4334","4335")

        presenter = MatchPresenter(this, request, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getMatchList(leagueId[spinner.selectedItemPosition], "eventsnextleague.php?")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }


    override fun showLoading() {
        progressBar?.visible()

    }

    override fun showMatchList(data: List<Match>) {
        events.clear()
        events.addAll(data)
        adapter = MatchAdapter(events){
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

    }
    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }


}
