package pl.jakubneukirch.wikiapp.data.model.dto

import android.text.Spanned

data class PageDTO(
        val pageId: Long,
        val title: String,
        val description: Spanned,
        val imageUrl: String
)