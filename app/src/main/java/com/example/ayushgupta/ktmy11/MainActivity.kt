package com.example.ayushgupta.ktmy11

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var songs: ArrayList<Songs>? = null
    private var lview: ListView? = null
    private var rl1: RelativeLayout? = null
    private var media: MediaPlayer? = null
    private var song: Songs? = null
    private var iv4: ImageView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rl1 = findViewById(R.id.rl1)
        iv4 = findViewById(R.id.iv4)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)

        songs = ArrayList()
        getSong()

        lview = findViewById(R.id.lview)
        val adapter = MyAdapter(songs!!, this)
        lview?.adapter = adapter
        lview?.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            song = adapter.getItem(position) as Songs
            val uri = Uri.parse("file:///${song!!.path}")
            iv4?.setImageResource(R.drawable.ic_pause_black_24dp)
            rl1?.visibility = View.VISIBLE
            tv3?.text = song?.name
            tv4?.text = song?.author
            if (media == null)
                media = MediaPlayer.create(this@MainActivity, uri)
            if (media?.isPlaying!!) {
                media?.stop()
                media = MediaPlayer.create(this@MainActivity, uri)
            }
            media?.start()
            media?.setOnCompletionListener {
                media?.stop()
                media?.release()
                media = null
            }
        }
        iv4?.setOnClickListener {
            if (media!!.isPlaying) {
                media?.pause()
                iv4?.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            } else {
                media?.start()
                iv4?.setImageResource(R.drawable.ic_pause_black_24dp)
            }
        }
    }

    private fun getSong() {
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val order = MediaStore.Audio.Media.TITLE + " ASC"
        val cr = contentResolver.query(uri, null, selection, null, order)

        if (cr != null) {
            while (cr.moveToNext()) {
                songs!!.add(Songs(cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.TITLE)), cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.DATA))))
            }
        }

        cr.close()
    }
}
