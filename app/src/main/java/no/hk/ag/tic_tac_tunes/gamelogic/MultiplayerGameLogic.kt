package no.hk.ag.tic_tac_tunes.gamelogic

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.logic_game_multiplayer.*
import no.hk.ag.tic_tac_tunes.mainactivity.MainActivity
import no.hk.ag.tic_tac_tunes.R
import no.hk.ag.tic_tac_tunes.database.HighScore
import no.hk.ag.tic_tac_tunes.database.HsModel
import java.util.*

class MultiplayerGameLogic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logic_game_multiplayer)

        gameTimer.start()
        getPlayers()

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

            val iHsModel = ViewModelProviders.of(this).get(HsModel::class.java)

            val hs = HighScore(null, playerOneView.text.toString(), scoreOne.text.toString().toInt())
            val hs2 = HighScore(null, playerTwoView.text.toString(), scoreTwo.text.toString().toInt())

            iHsModel.insert(hs)
            iHsModel.insert(hs2)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    private var player1points: Int = 0
    private var player2points: Int = 0

    private var playerTurn = 1

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
        if(playerTurn == 1) {
            btnSelector.setBackgroundResource(R.drawable.bugsbunny)
            player1.add(btnID)
            playerTurn = 2
        } else if(playerTurn == 2) {
            btnSelector.setBackgroundResource(R.drawable.daffyduckface)
            player2.add(btnID)
            playerTurn = 1
        }
        btnSelector.isEnabled = false

        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        // player 1 - winning
        // horizontal
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        // linear
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        // cross
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        // player 2 - winning
        // horizontal
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        // linear
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        // cross
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        // draw
        if(player1.size == 5 && player2.size == 4) {
            if(winner != 1) {
                Toast.makeText(this, "It looks like we have a draw", Toast.LENGTH_LONG).show()
                gameTimer.stop()
            }
        }

        // winning
        if(winner != -1) {
            if(winner == 1) {
                Toast.makeText(this, "Player 1 won the game", Toast.LENGTH_LONG).show()
                player1points += 1
                updatePoints()
                btnDisable()
                gameTimer.stop();
            } else {
                Toast.makeText(this, "Player 2 won the game", Toast.LENGTH_LONG).show()
                player2points += 1
                updatePoints()
                btnDisable()
                gameTimer.stop()
            }
        }
    }

    private fun updatePoints() {
        scoreOne.setText("" + player1points)
        scoreTwo.setText("" + player2points)
    }

    private fun resetBoard() {
        playerTurn = 1
        player1.clear()
        player2.clear()
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
        player1points = 0
        player2points = 0
        updatePoints()
        resetBoard()
    }

    private fun playAgain() {
        resetBoard()
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
        val playerTwo = mypref.getString("p2", "")

        playerOneView.setText(playerOne)
        playerTwoView.setText(playerTwo)
    }
}
