package com.example.artistfinder.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.artistfinder.R
import com.example.artistfinder.model.ITunesResponse
import com.example.artistfinder.view.adapter.ITunesAdapter.ITunesViewHolder

class ITunesAdapter(private val iTunesResponse: ITunesResponse) : RecyclerView.Adapter<ITunesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITunesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itunes_result_layout,
                parent, false)
        return ITunesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ITunesViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(iTunesResponse.results!![position].artworkUrl100)
                .into(holder.itunesArtistImage)
        holder.itunesArtistName.text = iTunesResponse.results!![position].artistName
        holder.itunesTrackName.text = iTunesResponse.results!![position].trackName
        holder.itunesTrackPrice.text = iTunesResponse.results!![position].trackPrice.toString()
        holder.itunesreleaseDate.text = iTunesResponse.results!![position].releaseDate
        holder.itunesprimaryGenreName.text = iTunesResponse.results!![position].primaryGenreName
    }

    override fun getItemCount(): Int {
        return iTunesResponse.results!!.size
    }

    class ITunesViewHolder(itemView: View) : ViewHolder(itemView) {
        var itunesArtistImage: ImageView = itemView.findViewById(R.id.artist_image)
        var itunesArtistName: TextView = itemView.findViewById(R.id.itunes_artist_name)
        var itunesTrackName: TextView = itemView.findViewById(R.id.itunes_track_name)
        var itunesTrackPrice: TextView = itemView.findViewById(R.id.itunes_track_price)
        var itunesreleaseDate: TextView = itemView.findViewById(R.id.itunes_release_date)
        var itunesprimaryGenreName: TextView = itemView.findViewById(R.id.itunes_primary_genre_name)

    }

}