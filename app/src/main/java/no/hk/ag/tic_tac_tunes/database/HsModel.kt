package no.hk.ag.tic_tac_tunes.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HsModel(application: Application) : AndroidViewModel(application) {

    private val iRepository: HsRepository = HsRepository(application)

    internal val liveHs : LiveData<List<HighScore>>

    init {
        liveHs = iRepository.allHs
    }

    fun insert(hs: HighScore) {
        iRepository.insert(hs)
    }
}