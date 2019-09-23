package no.hk.ag.tic_tac_tunes.gamescreens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.logic_game_multiplayer.*
import no.hk.ag.tic_tac_tunes.mainactivity.MainActivity
import no.hk.ag.tic_tac_tunes.R

class MenuFragment : androidx.fragment.app.Fragment() {

    private lateinit var singleplayer : Button
    private lateinit var multiplayer : Button
    private lateinit var highScore : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fview = inflater.inflate(R.layout.fragment_menu, container, false)

        singleplayer = fview.findViewById(R.id.Btn1Player)
        multiplayer = fview.findViewById(R.id.Btn2Player)
        highScore = fview.findViewById(R.id.BtnHighScore)

        return fview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singleplayer.setOnClickListener {
            val singleFrag = SingleFragment()
            (activity as MainActivity).replaceFragment(R.id.fragment_container, singleFrag)
        }
        multiplayer.setOnClickListener {
            val multiFrag = MultiFragment()
            (activity as MainActivity).replaceFragment(R.id.fragment_container, multiFrag)
        }
        highScore.setOnClickListener {
            val intent = Intent(context, HighScoreActivity::class.java)
            startActivity(intent)
        }


    }
}
