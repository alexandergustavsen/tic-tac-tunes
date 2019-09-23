package no.hk.ag.tic_tac_tunes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hs : HighScore)

    @Query("DELETE FROM hs_table")
    fun deleteAll()

    @Query("SELECT * FROM hs_table ORDER BY p_score DESC")
    fun getAllHs() : List<HighScore>

    @Query("SELECT * FROM hs_table ORDER BY p_score DESC")
    fun getAllHsLive() : LiveData<List<HighScore>>

}