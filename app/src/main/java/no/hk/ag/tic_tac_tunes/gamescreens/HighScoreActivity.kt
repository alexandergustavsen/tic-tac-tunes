package no.hk.ag.tic_tac_tunes.gamescreens

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import no.hk.ag.tic_tac_tunes.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.hk.ag.tic_tac_tunes.database.HsAdapter
import androidx.lifecycle.ViewModelProviders
import no.hk.ag.tic_tac_tunes.database.HsModel
import no.hk.ag.tic_tac_tunes.database.HighScore

class HighScoreActivity : AppCompatActivity() {

    private lateinit var btnMenu : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        val iHsModel = ViewModelProviders.of(this).get(HsModel::class.java)
        btnMenu = findViewById(R.id.btnMenu)

        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
        val adapter = HsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        iHsModel.liveHs.observe(this,
            Observer<List<HighScore>> { hs -> adapter.setHs(hs) })

        btnMenu.setOnClickListener {
            this.finish()
        }

    }
}
