package pl.jakubneukirch.wikiapp.random

import android.database.Cursor
import pl.jakubneukirch.wikiapp.base.MvpView
import pl.jakubneukirch.wikiapp.data.model.dto.PageDTO

interface RandomView : MvpView {
    fun setup()
    fun setPagesData(list: List<PageDTO>)
    fun showSearchResults(cursor: Cursor)
    fun openPageScreen(pageId: Long)
}