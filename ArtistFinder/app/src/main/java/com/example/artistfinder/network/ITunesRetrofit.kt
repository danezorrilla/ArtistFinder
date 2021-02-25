package com.example.artistfinder.network

import com.example.artistfinder.model.ITunesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ITunesRetrofit {
    private val iTunesAPI: ITunesAPI
    private fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createITunesAPI(retrofit: Retrofit): ITunesAPI {
        return retrofit.create(ITunesAPI::class.java)
    }

    fun getItunesArtistList(searchQuery: String?): Call<ITunesResponse?>? {
        return iTunesAPI.searchITunesArtist(searchQuery)
    }

    init {
        iTunesAPI = createITunesAPI(createRetrofitInstance())
    }
}