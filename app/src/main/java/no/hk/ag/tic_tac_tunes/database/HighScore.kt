package no.hk.ag.tic_tac_tunes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName ="hs_table")
data class HighScore(@PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    var id : Long?,

    @ColumnInfo(name = "p_name")
    var pName : String?,

    @ColumnInfo(name = "p_score")
    var pScore : Int?
)