package no.hk.ag.tic_tac_tunes.mainactivity

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import no.hk.ag.tic_tac_tunes.R
import no.hk.ag.tic_tac_tunes.gamescreens.MenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMenu()
    }

    private fun showMenu() {
        val mediaPlayer : MediaPlayer = MediaPlayer.create(this, R.raw.looneytunesaudio)
        mediaPlayer.start()

        val transaction: androidx.fragment.app.FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MenuFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(containerID: Int, fragment: androidx.fragment.app.Fragment) {
        val transaction: androidx.fragment.app.FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerID, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun savePlayer(playerOne : TextView) {
        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val editor = mypref.edit()

        editor.putString("p1", playerOne.text.toString())
        editor.apply()

        Toast.makeText(this, "Player saved", Toast.LENGTH_LONG).show()
    }

    fun savePlayers(playerOne : TextView, playerTwo : TextView) {
        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val editor = mypref.edit()

        editor.putString("p1", playerOne.text.toString())
        editor.putString("p2", playerTwo.text.toString())
        editor.apply()

        Toast.makeText(this, "Players saved", Toast.LENGTH_LONG).show()
    }
}
