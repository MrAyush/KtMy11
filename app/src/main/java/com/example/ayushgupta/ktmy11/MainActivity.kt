package com.example.ayushgupta.ktmy11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private var songs: ArrayList<Songs>? = null
    private var lview: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        songs = ArrayList()
        getSong()
        lview = findViewById(R.id.lview)
        lview?.adapter = MyAdapter(songs!!, this)
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
