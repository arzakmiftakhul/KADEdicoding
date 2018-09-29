package com.miftakhularzak.footballclubapp.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.adapter.MainAdapter
import com.miftakhularzak.footballclubapp.adapter.MatchAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.MainPresenter
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.Team
import com.miftakhularzak.footballclubapp.util.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    //private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = tab_layout
        val viewPager: ViewPager = view_pager
        val adapter = MatchAdapter(supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
    }

    override fun showLoading() {
        Log.d("CEKDATA", "SHOWLOADING")

    }

    override fun hideLoading() {
        Log.d("CEKDATA", "hIDE LOADING")

    }

    override fun showTeamHome(data: List<Team>, data2: List<Team>) {
       // swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        Log.d("CEKDATA", "SHOW CLUB LIST" + teams[1].teamName)

    }

    override fun showEventList(data: List<Match>) {
        Log.d("DATA EVENT", "SHOW EVENT LIST" + data[1])
    }


}








