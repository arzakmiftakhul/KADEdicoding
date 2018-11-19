package com.miftakhularzak.footballclubapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    private var name: String = ""
    private var imageId: Int = R.drawable.img_acm
    private var desc: String = ""
    lateinit var imageClub: ImageView
    lateinit var nameClub: TextView
    lateinit var descClub : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout{
            padding = dip(16)

            imageClub = imageView().
                    lparams(width = dip(150), height = dip(150)){
                        padding = dip(20)
                        margin = dip(15)
                        gravity = Gravity.CENTER

            }
            nameClub = textView().
                    lparams(width= wrapContent){
                        gravity = Gravity.CENTER
                        margin = dip(15)


            }

            descClub = textView().
                    lparams(width = wrapContent,height = wrapContent){
                        gravity = Gravity.CENTER
                        margin = dip (15)
                    }

        }

        val intent = intent
        name = intent.getStringExtra("name")
        imageId = intent.getIntExtra("imageClub",0)
        desc = intent.getStringExtra("descClub")
        nameClub.text = name
        imageClub.setImageResource(imageId)
        descClub.text = desc

    }
}
