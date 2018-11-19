package com.miftakhularzak.footballclubapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.adapter.TeamOverviewAdapter
import com.miftakhularzak.footballclubapp.model.Team


/**
 * A simple [Fragment] subclass.
 *
 */
class TeamOverviewFragment : Fragment() {
    private lateinit var adapter: TeamOverviewAdapter
    private lateinit var overviewTextRv: RecyclerView
    private var teams: MutableList<Team> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val rootView = inflater.inflate(R.layout.fragment_team_overview, container, false)
        overviewTextRv = rootView.findViewById(R.id.team_overview_rv) as RecyclerView
        overviewTextRv.layoutManager = LinearLayoutManager(activity)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent = activity?.intent
        val overViewList : List<Team> = mutableListOf(Team("",
                "","",
                "","",
                "",""))
        overViewList[0].teamOverview = intent?.getStringExtra("overview")
        teams.clear()
        teams.addAll(overViewList)
        adapter = TeamOverviewAdapter(teams)
        overviewTextRv.adapter = adapter
        adapter.notifyDataSetChanged()

    }

}

