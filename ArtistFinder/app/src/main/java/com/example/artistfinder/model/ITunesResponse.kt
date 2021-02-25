package com.example.artistfinder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ITunesResponse {
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<ITunesResult>? = null

}