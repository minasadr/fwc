package io.sadr.fwc

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_main)
        val playButton = findViewById(R.id.button_play) as Button
        playButton.setOnClickListener {
            play()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        exit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_exit -> exit()
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun play() {
        val intent = Intent(this@MainActivity, CountryActivity::class.java)
        this@MainActivity.startActivity(intent)
    }

    private fun exit(): Boolean {
        AlertDialog.Builder(this)
                .setIcon(R.drawable.close_octagon)
                .setTitle("Closing Application!")
                .setMessage("Are you sure you want to close the application?")
                .setPositiveButton("Yes") { dialog, which -> finish() }
                .setNegativeButton("No", null)
                .show()
        return true
    }
}