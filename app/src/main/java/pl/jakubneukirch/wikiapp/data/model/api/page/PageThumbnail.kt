package pl.jakubneukirch.wikiapp.data.model.api.page

import com.google.gson.annotations.SerializedName

data class PageThumbnail(
        @SerializedName("source") val url: String
)