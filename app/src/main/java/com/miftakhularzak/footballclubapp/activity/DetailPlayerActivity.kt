package com.miftakhularzak.footballclubapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var playerPhoto: ImageView
    private lateinit var playerPosition: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerDesc: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val intent = intent
        supportActionBar?.title = intent.getStringExtra("name")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        playerPhoto = player_photo
        playerPosition = p_position
        playerHeight = height_value
        playerWeight = weight_value
        playerDesc = player_desc


        Picasso.get().load(intent.getStringExtra("photo")).into(playerPhoto)
        playerPosition.text = intent.getStringExtra("position")
        playerHeight.text = intent.getStringExtra("height")
        playerWeight.text = intent.getStringExtra("weight")
        playerDesc.text = intent.getStringExtra("overview")
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
}
