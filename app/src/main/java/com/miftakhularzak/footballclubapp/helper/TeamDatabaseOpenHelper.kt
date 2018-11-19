package com.miftakhularzak.footballclubapp.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.miftakhularzak.footballclubapp.model.Favorite
import com.miftakhularzak.footballclubapp.model.FavoriteTeam
import org.jetbrains.anko.db.*

class TeamDatabaseOpenHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){
    companion object {
        private var instance : TeamDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance (ctx: Context) : TeamDatabaseOpenHelper {
            if(instance == null){
                instance = TeamDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as TeamDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT,
                FavoriteTeam.TEAM_OVERVIEW to TEXT,
                FavoriteTeam.TEAM_FORMED_YEAR to TEXT,
                FavoriteTeam.TEAM_STADIUM to TEXT,
                FavoriteTeam.TEAM_LEAGUE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM,true)
    }
}
//Access property for Context
val Context.databaseteam : TeamDatabaseOpenHelper
    get() = TeamDatabaseOpenHelper.getInstance(applicationContext)