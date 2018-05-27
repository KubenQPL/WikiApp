package pl.jakubneukirch.wikiapp.data.model.dto

import android.text.Spanned
import pl.jakubneukirch.wikiapp.common.spannedFromHtml
import pl.jakubneukirch.wikiapp.data.model.api.page.PageObject

data class PageDTO(
        val pageId: Long,
        val title: String,
        val description: Spanned,
        val imageUrl: String
)

fun mapPageDTO(pageObject: PageObject) : PageDTO {
    return PageDTO(
            pageId = pageObject.pageId,
            title = pageObject.title,
            description = spannedFromHtml(pageObject.extract),
            imageUrl = pageObject.thumbnail?.url ?: ""
    )
}