package pl.jakubneukirch.wikiapp.data.model.api

import com.google.gson.annotations.SerializedName

data class PageObject(
        @SerializedName("pageid") val pageId: Long,
        val title: String
)