package pl.jakubneukirch.wikiapp.data.model.api.search

import com.google.gson.annotations.SerializedName

data class SearchItem(
        val title: String,
        @SerializedName("pageid") val pageId: Long,
        val snippet: String
)