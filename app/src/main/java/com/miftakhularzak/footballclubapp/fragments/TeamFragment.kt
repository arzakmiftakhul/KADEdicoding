package com.miftakhularzak.footballclubapp.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.R.array.league
import com.miftakhularzak.footballclubapp.R.color.colorAccent
import com.miftakhularzak.footballclubapp.`interface`.TeamView
import com.miftakhularzak.footballclubapp.activity.DetailTeamActivity
import com.miftakhularzak.footballclubapp.adapter.TeamsAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.Team
import com.miftakhularzak.footballclubapp.presenter.TeamPresenter
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamFragment : Fragment(),TeamView {
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String
    private lateinit var listTeamFrag : RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return createView(AnkoContext.create(ctx))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx,
                android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        swipeRefreshLayout.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView?

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if(newText.isNotEmpty() && newText.length >2)
                        presenter.searchTeamList(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu,inflater)


    }


    private fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeamFrag = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {  }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter = TeamsAdapter(data){
            val intent = Intent(this.activity, DetailTeamActivity::class.java)
            intent.putExtra("id",data[adapter.clickedIndex].teamId)
            intent.putExtra("logo",data[adapter.clickedIndex].teamBadge)
            intent.putExtra("name",data[adapter.clickedIndex].teamName)
            intent.putExtra("formedyear",data[adapter.clickedIndex].teamFormedYear)
            intent.putExtra("stadium",data[adapter.clickedIndex].teamStadium)
            intent.putExtra("overview",data[adapter.clickedIndex].teamOverview)
            intent.putExtra("league", data[adapter.clickedIndex].teamLeague)
            startActivity(intent)
        }
        listTeamFrag.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
