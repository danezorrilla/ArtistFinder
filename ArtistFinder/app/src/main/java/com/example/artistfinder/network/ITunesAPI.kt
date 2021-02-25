package com.example.artistfinder.network

import com.example.artistfinder.model.ITunesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesAPI {
    @GET("search")
    fun searchITunesArtist(@Query("term") searchQuery: String?): Call<ITunesResponse?>?
}