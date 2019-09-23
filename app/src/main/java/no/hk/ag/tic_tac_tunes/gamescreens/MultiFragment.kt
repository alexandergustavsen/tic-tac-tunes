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
import no.hk.ag.tic_tac_tunes.gamelogic.MultiplayerGameLogic




class MultiFragment : androidx.fragment.app.Fragment() {

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private lateinit var insertPlayerOne : TextView
    private lateinit var insertPlayerTwo : TextView
    private lateinit var startGame : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fview = inflater.inflate(R.layout.fragment_multi, container, false)

        insertPlayerOne = fview.findViewById(R.id.playerOneName)
        insertPlayerTwo = fview.findViewById(R.id.playerTwoName)
        startGame = fview.findViewById(R.id.BtnStart)

        return fview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startGame.setOnClickListener {
            savePlayers()
            val intent = Intent(activity, MultiplayerGameLogic::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }

    private fun savePlayers() {
        if(insertPlayerOne.text.isEmpty()) {
            insertPlayerOne.error = "Please insert player 1!"
            return
        }

        if(insertPlayerTwo.text.isEmpty()) {
            insertPlayerTwo.error = "Please insert player 2!"
            return
        }
        (activity as MainActivity).savePlayers(insertPlayerOne, insertPlayerTwo)
    }

}
