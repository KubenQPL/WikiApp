package pl.jakubneukirch.wikiapp.random

import android.database.Cursor
import pl.jakubneukirch.wikiapp.base.MvpView
import pl.jakubneukirch.wikiapp.data.model.api.PageObject

interface RandomView: MvpView {
    fun setPagesData(list: List<PageObject>)
    fun showSearchResults(cursor: Cursor)
}