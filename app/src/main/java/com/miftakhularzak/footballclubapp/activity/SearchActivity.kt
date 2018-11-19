package com.miftakhularzak.footballclubapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.`interface`.MatchView
import com.miftakhularzak.footballclubapp.adapter.MatchAdapter
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.presenter.SearchMatchPresenter
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(),MatchView {

    private lateinit var adapter: MatchAdapter
    private lateinit var matchPresenter: SearchMatchPresenter
    var progressBar: ProgressBar? = null

    private var events: MutableList<Match> = mutableListOf()
    private lateinit var listEvents: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar = progress_search

        listEvents = search_result
        listEvents.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.search_menu, menu)
        //menuItem = menu
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        searchItem?.expandActionView()

        val searchView: SearchView? = searchItem?.actionView as SearchView?

        val request = ApiRepository()
        val gson = Gson()
        matchPresenter = SearchMatchPresenter(this, request, gson)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
               // toast("cek" + newText)
                if (newText != null) {
                    if(newText.isNotEmpty() && newText.length >2)
                        matchPresenter.searchMatchList(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun showLoading() {
       progressBar?.visible()
    }

    override fun hideLoading() {
       progressBar?.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        events.clear()
        events.addAll(data)
        adapter = MatchAdapter(events){
            val intent = Intent(this,DetailMatchActivity::class.java)
            intent.putExtra("eventid",data[adapter.clickedIndex].eventId)
            intent.putExtra("homeid",data[adapter.clickedIndex].homeId)
            intent.putExtra("awayid",data[adapter.clickedIndex].awayId)
            intent.putExtra("id",data[adapter.clickedIndex].eventId)
            startActivity(intent)
        }
        listEvents.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
