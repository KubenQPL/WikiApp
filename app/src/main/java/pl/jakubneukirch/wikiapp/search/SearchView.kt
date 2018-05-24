package pl.jakubneukirch.wikiapp.search

import android.database.Cursor
import pl.jakubneukirch.wikiapp.base.MvpView

interface SearchView: MvpView {
    fun setup()
    fun showSearchResults(cursor: Cursor)
}