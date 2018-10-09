package com.miftakhularzak.footballclubapp.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.miftakhularzak.footballclubapp.model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper (ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){
    companion object {
        private var instance : MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance (ctx: Context) : MyDatabaseOpenHelper{
            if(instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                            Favorite.EVENT_ID to TEXT,
                            Favorite.HOME_ID to TEXT,
                            Favorite.AWAY_ID to TEXT,
                            Favorite.HOME_TEAM to TEXT,
                            Favorite.AWAY_TEAM to TEXT,
                            Favorite.HOME_SCORE to TEXT,
                            Favorite.AWAY_SCORE to TEXT,
                            Favorite.DATE_EVENT to TEXT,
                            Favorite.TIME_EVENT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}
//Access property for Context
val Context.database : MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)