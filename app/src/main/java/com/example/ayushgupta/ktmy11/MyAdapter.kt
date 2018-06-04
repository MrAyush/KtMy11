package com.example.ayushgupta.ktmy11

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ViewHolder {
    var iv1: ImageView? = null
    var tv1: TextView? = null
    var tv2: TextView? = null
    var iv2: ImageView? = null
    var path: String? = null
}

class MyAdapter(private val songs: ArrayList<Songs>, private val mActivity: MainActivity) : BaseAdapter() {
    private var count = 1
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mActivity)
        val song = songs[position]
        var view: View? = convertView
        var holder = ViewHolder()
        if (view == null) {
            view = layoutInflater.inflate(R.layout.my_list, parent, false)
            holder.iv1 = view.findViewById(R.id.iv1)
            holder.tv1 = view.findViewById(R.id.tv1)
            holder.tv2 = view.findViewById(R.id.tv2)
            holder.iv2 = view.findViewById(R.id.iv2)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        holder.tv1?.text = song.name
        holder.tv2?.text = song.author
        holder.path = song.path
        val uri = Uri.parse("file:///${holder.path}")
        val media = MediaPlayer.create(mActivity, uri)
        holder.iv2?.setOnClickListener {
            if (media.isPlaying){
                media.pause()
            }else{
                media.start()
            }
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return songs[position]
    }

    override fun getItemId(position: Int): Long {
        return Long.MAX_VALUE
    }

    override fun getCount(): Int {
        return songs.size
    }

}