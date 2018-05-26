package pl.jakubneukirch.wikiapp.data.model.api.page

import com.google.gson.annotations.SerializedName

data class PageObject(
        @SerializedName("pageid") val pageId: Long,
        val title: String,
        val extract: String,
        val thumbnail: PageThumbnail?
)