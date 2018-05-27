package pl.jakubneukirch.wikiapp.common

import android.os.Build
import android.text.Html
import android.text.Spanned

fun stringFromHtml(html: String): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
    } else {
        return Html.fromHtml(html).toString()
    }
}

fun spannedFromHtml(html: String): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        return Html.fromHtml(html)
    }
}