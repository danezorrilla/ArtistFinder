package com.example.artistfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artistfinder.model.ITunesResponse
import com.example.artistfinder.network.ITunesRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ITunesViewModel : ViewModel() {
    private val iTunesRetrofit: ITunesRetrofit = ITunesRetrofit()
    private val iTunesResponseMutableLiveData = MutableLiveData<ITunesResponse>()

    fun getITunesResponse(name: String?): MutableLiveData<ITunesResponse>? {
        iTunesRetrofit.getItunesArtistList(name)!!.enqueue(object : Callback<ITunesResponse?> {
            override fun onResponse(call: Call<ITunesResponse?>, response: Response<ITunesResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    iTunesResponseMutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ITunesResponse?>, t: Throwable) {}
        })
        return iTunesResponseMutableLiveData
    }

}