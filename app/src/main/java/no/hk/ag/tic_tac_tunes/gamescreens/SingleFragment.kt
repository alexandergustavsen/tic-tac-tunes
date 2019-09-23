package no.hk.ag.tic_tac_tunes.gamescreens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import no.hk.ag.tic_tac_tunes.mainactivity.MainActivity
import no.hk.ag.tic_tac_tunes.R
import no.hk.ag.tic_tac_tunes.gamelogic.SingleplayerGameLogic

class SingleFragment : androidx.fragment.app.Fragment() {

    private lateinit var insertPlayerOne : TextView
    private lateinit var startGame : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fview = inflater.inflate(R.layout.fragment_single, container, false)

        insertPlayerOne = fview.findViewById(R.id.playerOneName)
        startGame = fview.findViewById(R.id.BtnStart)

        return fview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startGame.setOnClickListener {
            savePlayer()
            val intent = Intent(activity, SingleplayerGameLogic::class.java)
            startActivity(intent)
        }
    }

    private fun savePlayer() {
        if(insertPlayerOne.text.isEmpty()) {
            insertPlayerOne.error = "Please insert player 1!"
            return
        }
        (activity as MainActivity).savePlayer(insertPlayerOne)
    }

}
