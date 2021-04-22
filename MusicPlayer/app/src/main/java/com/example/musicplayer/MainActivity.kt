package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var isRunning = false
    private lateinit var mp : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mp = MediaPlayer.create(this, R.raw.ash_island_melody)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mp.seekTo(progress)
                }

                val min: Int = mp.duration/60000
                val sec: Int = (mp.duration-60000*min)/1000

                val min2 : Int = mp.currentPosition/60000
                val sec2 : Int = (mp.currentPosition-60000*min2)/1000

                current_tv.text = String.format("%01d:%02d", min2, sec2)
                end_tv.text = String.format("%01d:%02d", min, sec)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        fab.setOnClickListener{
            isRunning = !isRunning

            if(isRunning){
               start()
            }
            else{
                pause()
            }

        }
    }

    private fun start(){
        fab.setImageResource(R.drawable.ic_baseline_pause_24)
        mp.start()

        Thread(object: Runnable{
            override fun run() {
                while(mp.isPlaying){
                    try{
                        Thread.sleep(1000)
                    } catch (e : Exception){
                        e.printStackTrace()
                    }
                    seekBar.setProgress(mp.currentPosition)
                }
            }
        }).start()
    }

    private fun pause(){
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        mp.pause()
    }
}