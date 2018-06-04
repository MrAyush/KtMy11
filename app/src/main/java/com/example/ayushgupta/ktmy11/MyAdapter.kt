package com.example.ayushgupta.ktmy11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ViewHolder {
    var iv1: ImageView? = null
    var tv1: TextView? = null
    var tv2: TextView? = null
    var path: String? = null
}

class MyAdapter(private val songs: ArrayList<Songs>, private val mActivity: MainActivity) : BaseAdapter() {
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
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        holder.tv1?.text = song.name
        holder.tv2?.text = song.author
        holder.path = song.path
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