package no.hk.ag.tic_tac_tunes.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [HighScore::class], version = 1, exportSchema = false)
abstract class HsDatabase : RoomDatabase() {

    abstract fun hsDao(): HsDao

    companion object {

        @Volatile
        private var INSTANCE : HsDatabase? = null

        fun getDb(context: Context): HsDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HsDatabase::class.java,
                    "hs_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}