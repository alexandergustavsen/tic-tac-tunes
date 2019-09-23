package no.hk.ag.tic_tac_tunes.database

import android.app.Application
import androidx.lifecycle.LiveData
import android.os.AsyncTask

class HsRepository internal constructor(application: Application) {

    private val iHsDao: HsDao
    internal val allHs: LiveData<List<HighScore>>

    init {
        val db = HsDatabase.getDb(application)
        iHsDao = db.hsDao()
        allHs = iHsDao.getAllHsLive()
    }

    fun insert(hs: HighScore) {
        InsertAsyncTask(iHsDao).execute(hs)
    }

    class InsertAsyncTask internal constructor(private val AsyncTaskDao: HsDao) :

        AsyncTask<HighScore, Void, Void>() {
        override fun doInBackground(vararg params: HighScore): Void? {
            AsyncTaskDao.insert(params[0])
            return null
        }
    }
}