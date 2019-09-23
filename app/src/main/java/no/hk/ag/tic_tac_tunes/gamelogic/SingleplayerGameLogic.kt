package no.hk.ag.tic_tac_tunes.gamelogic

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.logic_game_singleplayer.backBtn
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn1
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn2
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn3
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn4
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn5
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn6
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn7
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn8
import kotlinx.android.synthetic.main.logic_game_singleplayer.btn9
import kotlinx.android.synthetic.main.logic_game_singleplayer.gameTimer
import kotlinx.android.synthetic.main.logic_game_singleplayer.playBtn
import kotlinx.android.synthetic.main.logic_game_singleplayer.playerOneView
import kotlinx.android.synthetic.main.logic_game_singleplayer.playerTwoView
import kotlinx.android.synthetic.main.logic_game_singleplayer.resetBtn
import kotlinx.android.synthetic.main.logic_game_singleplayer.scoreOne
import kotlinx.android.synthetic.main.logic_game_singleplayer.scoreTwo
import no.hk.ag.tic_tac_tunes.mainactivity.MainActivity
import no.hk.ag.tic_tac_tunes.R
import java.util.*
import kotlin.collections.ArrayList

class SingleplayerGameLogic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logic_game_singleplayer)

        gameTimer.start()
        getPlayers()
        playerTwoView.setText("TTTBot")

        resetBtn.setOnClickListener {
            resetGame()
            gameTimer.base = SystemClock.elapsedRealtime()
            gameTimer.start()
        }
        playBtn.setOnClickListener {
            playAgain()
            gameTimer.base = SystemClock.elapsedRealtime()
            gameTimer.start()
        }
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private var p1 = ArrayList<Int>()
    private var p2 = ArrayList<Int>()

    private var p1Pts: Int = 0
    private var p2Pts: Int = 0

    private var pTurn = 1

    private var win : Int = -1

    fun btnClick (view: View) {
        val btnSelector = view as Button
        var btnID = 0
        when(btnSelector.id) {
            R.id.btn1 -> btnID = 1
            R.id.btn2 -> btnID = 2
            R.id.btn3 -> btnID = 3
            R.id.btn4 -> btnID = 4
            R.id.btn5 -> btnID = 5
            R.id.btn6 -> btnID = 6
            R.id.btn7 -> btnID = 7
            R.id.btn8 -> btnID = 8
            R.id.btn9 -> btnID = 9
        }
        playGame(btnID, btnSelector)
    }

    private fun playGame(btnID: Int, btnSelector: Button) {
        if(pTurn == 1) {
            btnSelector.setBackgroundResource(R.drawable.bugsbunny)
            p1.add(btnID)
            pTurn = 2
            Handler().postDelayed({
                if(win == 1 || (p1.size == 5 && p2.size == 4)) {
                    //Stopper bot ved seier til en av partene eller uavgjort.
                } else {
                    botLogic()
                }
            }, 1000)
        } else {
            btnSelector.setBackgroundResource(R.drawable.daffyduckface)
            p2.add(btnID)
            pTurn = 1
        }
        btnSelector.isEnabled = false
        checkWinner()
    }

    private fun checkWinner() {
        win = -1
        // player 1 - winning
        // horizontal
        if(p1.contains(1) && p1.contains(2) && p1.contains(3)) {
            win = 1
        }
        if(p1.contains(4) && p1.contains(5) && p1.contains(6)) {
            win = 1
        }
        if(p1.contains(7) && p1.contains(8) && p1.contains(9)) {
            win = 1
        }
        // linear
        if(p1.contains(1) && p1.contains(4) && p1.contains(7)) {
            win = 1
        }
        if(p1.contains(2) && p1.contains(5) && p1.contains(8)) {
            win = 1
        }
        if(p1.contains(3) && p1.contains(6) && p1.contains(9)) {
            win = 1
        }
        // cross
        if(p1.contains(1) && p1.contains(5) && p1.contains(9)) {
            win = 1
        }
        if(p1.contains(3) && p1.contains(5) && p1.contains(7)) {
            win = 1
        }

        // player 2 - winning
        // horizontal
        if(p2.contains(1) && p2.contains(2) && p2.contains(3)) {
            win = 2
        }
        if(p2.contains(4) && p2.contains(5) && p2.contains(6)) {
            win = 2
        }
        if(p2.contains(7) && p2.contains(8) && p2.contains(9)) {
            win = 2
        }
        // linear
        if(p2.contains(1) && p2.contains(4) && p2.contains(7)) {
            win = 2
        }
        if(p2.contains(2) && p2.contains(5) && p2.contains(8)) {
            win = 2
        }
        if(p2.contains(3) && p2.contains(6) && p2.contains(9)) {
            win = 2
        }
        // cross
        if(p2.contains(1) && p2.contains(5) && p2.contains(9)) {
            win = 2
        }
        if(p2.contains(3) && p2.contains(5) && p2.contains(7)) {
            win = 2
        }

        // draw
        if(p1.size == 5 && p2.size == 4) {
            if(win != 1) {
                Toast.makeText(this, "It looks like we have a draw", Toast.LENGTH_LONG).show()
                btnDisable()
                gameTimer.stop()
            }
        }

        // winning
        if(win != -1) {
            if(win == 1) {
                Toast.makeText(this, "Player 1 won the game", Toast.LENGTH_LONG).show()
                p1Pts += 1
                updatePoints()
                btnDisable()
                gameTimer.stop()
            } else {
                Toast.makeText(this, "Player 2 won the game", Toast.LENGTH_LONG).show()
                p2Pts += 1
                updatePoints()
                btnDisable()
                gameTimer.stop()
            }
        }
    }

    private fun updatePoints() {
        scoreOne.setText("" + p1Pts)
        scoreTwo.setText("" + p2Pts)
    }

    private fun resetBoard() {
        pTurn = 1
        p1.clear()
        p2.clear()
        btn1.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn2.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn3.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn4.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn5.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn6.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn7.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn8.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btn9.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btnEnable()
    }

    private fun resetGame() {
        p1Pts = 0
        p2Pts = 0
        updatePoints()
        resetBoard()
    }

    private fun playAgain() {
        resetBoard()
    }

    private fun botLogic() {
        val freeBtn = ArrayList<Int>()
        for(btnID in 1..9) {
            if(!(p1.contains(btnID) || p2.contains(btnID))) {
                freeBtn.add(btnID)
            }
        }
        val random = Random()
        val rIndex = random.nextInt(freeBtn.size-0)+0
        val btnID = freeBtn[rIndex]

        val btnSelector: Button
        when(btnID) {
            1->btnSelector = btn1
            2->btnSelector = btn2
            3->btnSelector = btn3
            4->btnSelector = btn4
            5->btnSelector = btn5
            6->btnSelector = btn6
            7->btnSelector = btn7
            8->btnSelector = btn8
            9->btnSelector = btn9
            else -> btnSelector = btn1
        }
        playGame(btnID, btnSelector)
    }

    private fun btnEnable() {
        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
        btn4.isEnabled = true
        btn5.isEnabled = true
        btn6.isEnabled = true
        btn7.isEnabled = true
        btn8.isEnabled = true
        btn9.isEnabled = true
    }

    private fun btnDisable() {
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false
        btn5.isEnabled = false
        btn6.isEnabled = false
        btn7.isEnabled = false
        btn8.isEnabled = false
        btn9.isEnabled = false
    }

    private fun getPlayers() {
        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val playerOne = mypref.getString("p1", "")
        playerOneView.setText(playerOne)
    }
}
