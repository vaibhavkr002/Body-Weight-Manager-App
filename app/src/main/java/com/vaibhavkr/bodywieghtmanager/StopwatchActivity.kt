package com.vaibhavkr.bodywieghtmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_workout.*

class StopwatchActivity : AppCompatActivity() {

    /**set btn*/
    private lateinit var playBtn: ImageView
    private lateinit var pauseBtn: ImageView
    private lateinit var chronomter: Chronometer
    var isPlay = false
    var pauseOffSet: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        arrow_left.setOnClickListener(){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        /**set find id*/
        playBtn = findViewById(R.id.playBtn)
        pauseBtn = findViewById(R.id.pauseBtn)
        chronomter = findViewById(R.id.chronoMterPlay)
        playBtn.setOnClickListener {
            /**set play*/
            if (!isPlay) {
                chronomter.base = SystemClock.elapsedRealtime() - pauseOffSet
                chronomter.start()
                pauseBtn.visibility = View.VISIBLE
                playBtn.setImageResource(R.drawable.ic_stop)
                textMsg("Chronomter is Start !!", this)
                isPlay = true

            } else {
                chronomter.base = SystemClock.elapsedRealtime()
                pauseOffSet = 0
                chronomter.stop()
                playBtn.setImageResource(R.drawable.ic_play)
                pauseBtn.visibility = View.GONE
                textMsg("Chronomter is Stop !!", this)
                isPlay = false
            }

        }
        pauseBtn.setOnClickListener {
            if (isPlay) {
                chronomter.stop()
                pauseOffSet = SystemClock.elapsedRealtime() - chronomter.base
                isPlay = false
                pauseBtn.setImageResource(R.drawable.ic_play)
                textMsg("Chronomter is Pause !!", this)
            } else {
                chronomter.base = SystemClock.elapsedRealtime() - pauseOffSet
                chronomter.start()
                pauseBtn.setImageResource(R.drawable.ic_pause)
                textMsg("Chronomter is Play !!", this)
                isPlay = true
            }
        }

    }

    fun textMsg(s: String, c: Context) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show()
    }
}