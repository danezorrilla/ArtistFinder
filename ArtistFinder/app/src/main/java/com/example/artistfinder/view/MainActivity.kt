package com.example.artistfinder.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artistfinder.R
import com.example.artistfinder.model.ITunesResponse
import com.example.artistfinder.view.adapter.ITunesAdapter
import com.example.artistfinder.viewmodel.ITunesViewModel

class MainActivity : AppCompatActivity() {
    private var iTunesViewModel: ITunesViewModel? = null
    private var enterArtistNameET: EditText? = null
    private lateinit var searchArtistNameBT: Button
    private var recyclerView: RecyclerView? = null
    private var listObserver: Observer<ITunesResponse>? = null
    private val progressBarFragment = ProgressBarFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iTunesViewModel = ViewModelProvider(this).get(ITunesViewModel::class.java)
        enterArtistNameET = findViewById(R.id.enter_artist_name)
        searchArtistNameBT = findViewById(R.id.search_artist_name)
        recyclerView = findViewById(R.id.itunes_result_list)
        listObserver = Observer { iTunesResponse: ITunesResponse -> displayArtistNameList(iTunesResponse)}

        searchArtistNameBT.setOnClickListener {
            displayArtistList()
        }
    }

    private fun displayArtistList() {
        val name = enterArtistNameET!!.text.toString()
        closeKeyboard()
        openFragment()
        val handler = Handler()
        handler.postDelayed({ closeFragment() }, 3000)
        listObserver?.let { iTunesViewModel!!.getITunesResponse(name)?.observe(this, it) }
        enterArtistNameET!!.setText("")
    }

    private fun displayArtistNameList(iTunesResponse: ITunesResponse) {
        for (i in iTunesResponse.results!!.indices) {
            recyclerView!!.layoutManager = LinearLayoutManager(this)
            recyclerView!!.adapter = ITunesAdapter(iTunesResponse)
        }
    }

    private fun closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        val view = this.currentFocus

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            val manager = getSystemService(
                    Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager
                    .hideSoftInputFromWindow(
                            view.windowToken, 0)
        }
    }

    private fun openFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.progress_frame, progressBarFragment)
                .commit()
    }

    private fun closeFragment() {
        supportFragmentManager
                .beginTransaction()
                .remove(progressBarFragment)
                .commit()
    }
}